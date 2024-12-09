package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;

public class Use extends Command {
    public Use(GameState gameState) {
        super(gameState);
    }

    @Override
    public void execute(String[] args) {
        if (args == null || args.length < 1) {
            System.out.println("Usage: use <item>");
            return;
        }

        String itemName = args[0];
        GameState gameState = getGameState();

    
        if (gameState.hasItem(itemName)) {
            System.out.println("You used the " + itemName + ".");
            gameState.removeItem(itemName); 
        } else {
            System.out.println("You don't have the item: " + itemName + ".");
        }
    }
}
