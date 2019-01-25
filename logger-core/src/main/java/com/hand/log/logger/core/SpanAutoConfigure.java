package com.hand.log.logger.core;

import com.hand.log.logger.core.web.ApplicationReadyEventListener;
import com.hand.log.logger.core.web.DefaultSpanEvent;
import com.hand.log.logger.core.web.SpanEvent;
import com.hand.log.logger.core.web.SpanServletRequestListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletRequestListener;

/**
 * span自动配置
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Configuration
public class SpanAutoConfigure {

    /**
     * spanEvent bean不存在，则创建一个默认的
     *
     * @return DefaultSpanEvent
     */
    @Bean
    @ConditionalOnMissingBean
    public SpanEvent spanEvent() {
        return new DefaultSpanEvent();
    }

    /**
     * 注册SpanServletRequestListener
     *
     * @param event SpanEvent
     * @return SpanServletRequestListener
     */
    @Bean
    public ServletListenerRegistrationBean spanServletRequestListener(SpanEvent event) {
        ServletListenerRegistrationBean<ServletRequestListener> listenerRegistrationBean = new ServletListenerRegistrationBean<>();
        listenerRegistrationBean.setListener(new SpanServletRequestListener(event));
        return listenerRegistrationBean;
    }


    /**
     * restTemplate不存在时，自动配置，并设置span拦截器
     *
     * @return RestTemplate
     */
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * bean`RestTemplate`存在则在容器启动后设置span拦截器
     *
     * @return ApplicationReadyEventListener
     */
    @Bean
    @ConditionalOnBean(RestTemplate.class)
    public ApplicationReadyEventListener applicationReadyEventListener() {
        return new ApplicationReadyEventListener();
    }

}
