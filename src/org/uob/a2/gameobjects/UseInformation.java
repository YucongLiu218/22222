package org.uob.a2.gameobjects;

public class UseInformation {
    private final String message;
    private final int effect;

    public UseInformation(String message, int effect) {
        this.message = message;
        this.effect = effect;
    }

    public String getMessage() {
        return message;
    }

    public int getEffect() {
        return effect;
    }
}
