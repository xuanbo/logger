package com.hand.log.logger.core.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggerContextVO;
import org.slf4j.Marker;

import java.util.HashMap;
import java.util.Map;

/**
 * ILoggingEvent包装类
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class LoggingEventWrapper implements ILoggingEvent {

    private final ILoggingEvent event;

    private Map<String, String> propertyMap;

    /**
     * 线程名称
     */
    private String threadName;

    public LoggingEventWrapper(ILoggingEvent event) {
        this.event = event;
    }

    @Override
    public String getThreadName() {
        if (threadName == null) {
            threadName = event.getThreadName();
        }
        return threadName;
    }

    @Override
    public Level getLevel() {
        return event.getLevel();
    }

    @Override
    public String getMessage() {
        return event.getMessage();
    }

    @Override
    public Object[] getArgumentArray() {
        return event.getArgumentArray();
    }

    @Override
    public String getFormattedMessage() {
        return event.getFormattedMessage();
    }

    @Override
    public String getLoggerName() {
        return event.getLoggerName();
    }

    @Override
    public LoggerContextVO getLoggerContextVO() {
        return event.getLoggerContextVO();
    }

    @Override
    public IThrowableProxy getThrowableProxy() {
        return event.getThrowableProxy();
    }

    @Override
    public StackTraceElement[] getCallerData() {
        return event.getCallerData();
    }

    @Override
    public boolean hasCallerData() {
        return event.hasCallerData();
    }

    @Override
    public Marker getMarker() {
        return event.getMarker();
    }

    @Override
    public Map<String, String> getMDCPropertyMap() {
        return event.getMDCPropertyMap();
    }

    @Override
    public Map<String, String> getMdc() {
        return getMDCPropertyMap();
    }

    @Override
    public long getTimeStamp() {
        return event.getTimeStamp();
    }

    @Override
    public void prepareForDeferredProcessing() {
        event.prepareForDeferredProcessing();
    }

    /**
     * 获取内部propertyMap，便于设置参数到event
     *
     * @return 参数
     */
    public Map<String, String> getPropertyMap() {
        if (propertyMap == null) {
            propertyMap = new HashMap<>();
        }
        return propertyMap;
    }

    /**
     * 设置线程名称，否则异步打印日志无法获取原始线程名称
     *
     * @param threadName 线程名称
     */
    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
