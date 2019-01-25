package com.hand.log.logger.core.web;

import com.hand.log.logger.core.Span;
import com.hand.log.logger.core.web.SpanEvent;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认的SpanEvent，创建一个新的span，仅仅包含id，并没有userId信息
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class DefaultSpanEvent implements SpanEvent {

    @Override
    public Span onCreate(HttpServletRequest request) {
        return Span.create();
    }

}
