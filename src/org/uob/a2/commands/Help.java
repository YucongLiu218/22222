package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;

public class Help extends Command {
    public Help (GameState gameState) {
        super(gameState);
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Available commands:");
        System.out.println("- move <direction>: Move in a direction (north, south, east, west).");
        System.out.println("- get <item>: Pick up an item from the room.");
        System.out.println("- drop <item>: Drop an item.");
        System.out.println("- look: Look at the current room.");
        System.out.println("- status: Display your current status.");
        System.out.println("- use <item>: Use an item.");
        System.out.println("- quit: Quit the game.");
    }
}
