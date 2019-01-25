package com.hand.log.logger.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 奔波儿灞
 * @since 1.0
 */
@RestController
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        LOG.info("hello方法");
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8080/world", String.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            LOG.info("访问world成功：{}", entity.getBody());
        } else {
            LOG.info("访问world失败：{}, code: {}", entity.getBody(), entity.getStatusCode().value());
        }
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/world")
    public ResponseEntity<String> world() {
        LOG.info("world方法");
        return ResponseEntity.ok("world");
    }

}
