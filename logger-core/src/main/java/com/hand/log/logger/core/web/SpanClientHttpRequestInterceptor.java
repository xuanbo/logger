package com.hand.log.logger.core.web;

import com.hand.log.logger.core.Span;
import com.hand.log.logger.core.SpanSerializable;
import com.hand.log.logger.core.SpanSerialization;
import com.hand.log.logger.core.SpanThreadContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 客户端发送请求时设置Span到请求头的拦截器
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class SpanClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final SpanSerializable SERIALIZABLE = new SpanSerialization();

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution)
            throws IOException {
        HttpHeaders headers = request.getHeaders();
        // 传递span参数
        headers.set(SpanServletRequestListener.SPAN_HEADER, serializeSpan());
        return execution.execute(request, body);
    }

    private String serializeSpan() {
        Span span = SpanThreadContextHolder.get();
        return SERIALIZABLE.serialize(span);
    }

}
