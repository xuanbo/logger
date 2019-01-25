package com.hand.log.logger.core;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * Span上下文
 * 通过TransmittableThreadLocal解决线程池复用问题：https://github.com/alibaba/transmittable-thread-local
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public final class SpanThreadContextHolder {

    private static final TransmittableThreadLocal<Span> SPAN_THREAD_LOCAL = new TransmittableThreadLocal<>();

    private SpanThreadContextHolder() {
    }

    public static void setup(Span span) {
        SPAN_THREAD_LOCAL.set(span);
    }

    public static Span get() {
        return SPAN_THREAD_LOCAL.get();
    }

    public static void cleanup() {
        SPAN_THREAD_LOCAL.remove();
    }

}
