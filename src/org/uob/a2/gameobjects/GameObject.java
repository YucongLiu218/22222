package org.uob.a2.gameobjects;

public abstract class GameObject {
    private final String name;
    private final String description;

    public GameObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
