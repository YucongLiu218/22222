package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;
import org.uob.a2.gameobjects.Room;

public class Look extends Command {
    public Look (GameState gameState) {
        super(gameState);
    }

    @Override
    public void execute(String[] args) {
        Room currentRoom = gameState.getMap().getRoom(gameState.getPlayerX(), gameState.getPlayerY());
        if (currentRoom != null) {
            System.out.println("You are in: " + currentRoom.getName());
            System.out.println(currentRoom.getDescription());
            if (!currentRoom.getItems().isEmpty()) {
                System.out.println("Items in this room:");
                currentRoom.getItems().forEach(item -> System.out.println("- " + item.getName()));
            } else {
                System.out.println("There are no items in this room.");
            }
        } else {
            System.out.println("You are not in a valid room.");
        }
    }
}
