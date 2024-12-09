package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;

public class Quit extends Command {
    public Quit(GameState gameState) {
        super(gameState);
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length > 0) {
            System.out.println("Warning: 'quit' command does not take any arguments. Ignoring extra input.");
        }

        GameState gameState = getGameState();
        System.out.println("Exiting the game. Thanks for playing!");
        gameState.setPlaying(false);
    }
}

