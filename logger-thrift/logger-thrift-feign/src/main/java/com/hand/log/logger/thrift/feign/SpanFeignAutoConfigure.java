package com.hand.log.logger.thrift.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * span feign自动配置
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Configuration
public class SpanFeignAutoConfigure {

    /**
     * span feign拦截器
     *
     * @return SpanRequestInterceptor
     */
    @Bean
    public RequestInterceptor spanRequestInterceptor() {
        return new SpanRequestInterceptor();
    }

}
