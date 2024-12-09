package org.uob.a2.gameobjects;

public class Exit extends Feature {
    private final boolean requiresKey;

    public Exit(String name, String description, boolean requiresKey) {
        super(name, description);
        this.requiresKey = requiresKey;
    }

    public boolean canExit(GameState gameState) {
        return !requiresKey || gameState.hasItem("Key");
    }
}
