package com.hand.log.logger.core;

import org.springframework.util.StringUtils;

/**
 * span序列化、反序列化实现
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class SpanSerialization implements SpanSerializable {

    @Override
    public String serialize(Span span) {
        return span.getId() + ":" + span.getUserId();
    }

    @Override
    public Span deserialize(String value) {
        String[] pair = StringUtils.split(value, ":");
        if (pair.length == 2) {
            return Span.create(pair[0], pair[1]);
        }
        return null;
    }

}
