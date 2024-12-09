package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;
import org.uob.a2.gameobjects.Item;

import java.util.List;

public class Status extends Command {
    public Status(GameState gameState) {
        super(gameState);
    }

    @Override
    public void execute(String[] args) {
        GameState gameState = getGameState();

        System.out.println("Player Status:");
        System.out.println("- Position: (" + gameState.getPlayerX() + ", " + gameState.getPlayerY() + ")");
        System.out.println("- Score: " + gameState.getScore());
        System.out.println("- Items:");

        List<Item> playerItems = gameState.getItems();
        if (playerItems.isEmpty()) {
            System.out.println("  No items in your inventory.");
        } else {
            playerItems.forEach(item -> System.out.println("  - " + item.getName()));
        }
    }
}
