package com.hand.log.logger.core.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.hand.log.logger.core.Span;
import com.hand.log.logger.core.SpanThreadContextHolder;

/**
 * span converter
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class SpanConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        Span span = SpanThreadContextHolder.get();
        if (span == null) {
            return "NULL";
        }
        return span.getId() + "-" + span.getUserId();
    }

}
