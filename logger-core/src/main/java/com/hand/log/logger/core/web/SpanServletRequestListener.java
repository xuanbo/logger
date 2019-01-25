package com.hand.log.logger.core.web;

import com.hand.log.logger.core.Span;
import com.hand.log.logger.core.SpanThreadContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 初始化Request时，创建或者获取span
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class SpanServletRequestListener implements ServletRequestListener {

    private static final Logger LOG = LoggerFactory.getLogger(SpanServletRequestListener.class);

    public static final String SPAN_HEADER = "Log-Span";

    private final SpanEvent spanEvent;

    public SpanServletRequestListener(SpanEvent spanEvent) {
        this.spanEvent = spanEvent;
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        String value = request.getHeader(SPAN_HEADER);
        if (StringUtils.isEmpty(value)) {
            // 请求头中如果获取不到span信息，则通过spanEvent创建
            Span span = spanEvent.onCreate(request);
            SpanThreadContextHolder.setup(span);
            LOG.debug("No span header found, create a new span");
        } else {
            String[] pair = StringUtils.split(value, ":");
            if (pair.length == 2) {
                // 请求头中获取到span信息，则直接使用span
                SpanThreadContextHolder.setup(Span.create(pair[0], pair[1]));
                LOG.debug("Found span header: {}, use exist span", value);
            } else {
                // 请求头中获取到span信息不合法，则通过spanEvent创建
                Span span = spanEvent.onCreate(request);
                SpanThreadContextHolder.setup(span);
                LOG.warn("Invalid span header: {}, create a new span", value);
            }
        }
        LOG.debug("Initialize span context");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        LOG.debug("Destroy span context");
        SpanThreadContextHolder.cleanup();
    }

}
