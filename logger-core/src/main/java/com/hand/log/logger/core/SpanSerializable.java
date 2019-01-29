package com.hand.log.logger.core;

/**
 * Span序列化，反序列化
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public interface SpanSerializable {

    /**
     * 序列化
     *
     * @param span Span
     * @return 序列化字符串
     */
    String serialize(Span span);


    /**
     * 反序列化
     *
     * @param value 序列化字符串
     * @return Span
     */
    Span deserialize(String value);

}
