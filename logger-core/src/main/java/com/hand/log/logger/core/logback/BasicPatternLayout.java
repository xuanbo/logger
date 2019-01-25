package com.hand.log.logger.core.logback;

import ch.qos.logback.classic.PatternLayout;

/**
 * 自定义PatternLayout
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class BasicPatternLayout extends PatternLayout {

    static {
        defaultConverterMap.put("span", SpanConverter.class.getName());
    }

}
