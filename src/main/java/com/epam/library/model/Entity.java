package com.epam.library.model;

import java.io.Serializable;

public class Entity implements Serializable {
    private static final long serialVersionUID = -4767629413295377029L;

    private Long id;

    public Entity(Long id) {
        this.id = id;
    }

    public Entity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
