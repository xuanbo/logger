package com.hand.log.logger.core.web;

import com.hand.log.logger.core.web.SpanClientHttpRequestInterceptor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 容器完成后对restTemplate设置span拦截器
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        // 如果SpanClientHttpRequestInterceptor不存在则添加到拦截器
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        boolean exist = false;
        for (ClientHttpRequestInterceptor interceptor : interceptors) {
            if (interceptor instanceof SpanClientHttpRequestInterceptor) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            interceptors.add(new SpanClientHttpRequestInterceptor());
        }
    }

}
