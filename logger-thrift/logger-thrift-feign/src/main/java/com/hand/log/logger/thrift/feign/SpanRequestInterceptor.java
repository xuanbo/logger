package com.hand.log.logger.thrift.feign;

import com.hand.log.logger.core.Span;
import com.hand.log.logger.core.SpanSerializable;
import com.hand.log.logger.core.SpanSerialization;
import com.hand.log.logger.core.SpanThreadContextHolder;
import com.hand.log.logger.core.web.SpanServletRequestListener;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * feign span拦截器
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class SpanRequestInterceptor implements RequestInterceptor {

    private static final SpanSerializable SERIALIZABLE = new SpanSerialization();

    @Override
    public void apply(RequestTemplate template) {
        template.header(SpanServletRequestListener.SPAN_HEADER, serializeSpan());
    }

    private String serializeSpan() {
        Span span = SpanThreadContextHolder.get();
        return SERIALIZABLE.serialize(span);
    }

}
