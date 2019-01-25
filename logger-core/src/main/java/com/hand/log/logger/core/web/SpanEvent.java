package com.hand.log.logger.core.web;

import com.hand.log.logger.core.Span;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建Span
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public interface SpanEvent {

    /**
     * 当请求中没有span信息，则根据request自定义创建span
     *
     * @param request HttpServletRequest
     * @return Span
     */
    Span onCreate(HttpServletRequest request);

}
