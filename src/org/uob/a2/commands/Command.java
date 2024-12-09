package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;

public abstract class Command {
    public final GameState gameState;

    public Command(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public abstract void execute (String[] args);
}