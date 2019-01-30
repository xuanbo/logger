package com.hand.log.logger.core.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.hand.log.logger.core.Span;
import com.hand.log.logger.core.SpanSerializable;
import com.hand.log.logger.core.SpanSerialization;
import com.hand.log.logger.core.SpanThreadContextHolder;

/**
 * span converter
 * 先从上下文中获取，获取不到再从MDC中获取
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class SpanConverter extends ClassicConverter {

    private static final SpanSerializable SERIALIZABLE = new SpanSerialization();

    private static final String EMPTY = "NULL";

    @Override
    public String convert(ILoggingEvent event) {
        Span span = SpanThreadContextHolder.get();
        if (span == null) {
            String value = null;
            if (event instanceof LoggingEventWrapper) {
                LoggingEventWrapper eventWrapper = (LoggingEventWrapper) event;
                value = eventWrapper.getPropertyMap().get(BasicAsyncAppender.KEY);
            }
            if (value == null) {
                return EMPTY;
            }
            span = SERIALIZABLE.deserialize(value);
            if (span == null) {
                return EMPTY;
            }
        }
        return span.getId() + "-" + span.getUserId();
    }

}
