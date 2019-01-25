package com.hand.log.logger.core;

import java.io.Serializable;
import java.util.UUID;

/**
 * span
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class Span implements Serializable {

    private String id;

    private String userId;

    public static Span create() {
        String id = UUID.randomUUID().toString().replace("-", "");
        return create(id);
    }

    public static Span create(String id) {
        return create(id, null);
    }

    public static Span create(String id, String userId) {
        return new Span(id, userId);
    }

    public Span(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
