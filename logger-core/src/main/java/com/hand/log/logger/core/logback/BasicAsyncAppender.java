package com.hand.log.logger.core.logback;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.hand.log.logger.core.Span;
import com.hand.log.logger.core.SpanSerializable;
import com.hand.log.logger.core.SpanSerialization;
import com.hand.log.logger.core.SpanThreadContextHolder;

/**
 * 异步打印日志时传递span到event，并设置原始线程名称
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class BasicAsyncAppender extends AsyncAppender {

    private static final SpanSerializable SERIALIZABLE = new SpanSerialization();

    public static final String KEY = "log-span";

    @Override
    protected void append(ILoggingEvent event) {
        super.append(new LoggingEventWrapper(event));
    }

    @Override
    protected void preprocess(ILoggingEvent event) {
        Span span = SpanThreadContextHolder.get();
        if (span != null) {
            if (event instanceof LoggingEventWrapper) {
                LoggingEventWrapper eventWrapper = (LoggingEventWrapper) event;
                // 设置原始线程名称
                eventWrapper.setThreadName(Thread.currentThread().getName());
                // 传递span
                eventWrapper.getPropertyMap().put(KEY, SERIALIZABLE.serialize(span));
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

}
