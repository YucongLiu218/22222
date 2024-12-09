package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;

public class Drop extends Command {
    public Drop(GameState gameState) {
        super(gameState);
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: drop <item>");
            return;
        }

        String itemName = args[0];
        GameState gameState = getGameState();
        if (gameState.removeItem(itemName)) {
            System.out.println("You dropped the " + itemName + ".");
        } else {
            System.out.println("You don't have the item: " + itemName + ".");
        }
    }
}
