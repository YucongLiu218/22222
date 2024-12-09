package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;

public class Move extends Command {
    public Move(GameState gameState) {
        super(gameState);
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: move <direction>");
            return;
        }

        String direction = args[0].toLowerCase();
        switch (direction) {
            case "north":
            case "south":
            case "east":
            case "west":
                gameState.movePlayer(direction);
                break;
            default:
                System.out.println("Invalid direction: " + direction);
        }
    }
}
