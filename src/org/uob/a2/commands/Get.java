package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;
import org.uob.a2.gameobjects.Item;
import org.uob.a2.gameobjects.Room;

public class Get extends Command {
    public Get(GameState gameState) {
        super(gameState);
    }
    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: get <item>");
            return;
        }

        String itemName = args[0];
        Room currentRoom = gameState.getMap().getRoom(gameState.getPlayerX(), gameState.getPlayerY());
        if (currentRoom != null) {
            Item item = currentRoom.getItemByName(itemName);
            if (item != null) {
                currentRoom.removeItem(itemName);
                gameState.addItem(item);
                System.out.println("You picked up the " + itemName + ".");
            } else {
                System.out.println("The item " + itemName + " is not in this room.");
            }
        } else {
            System.out.println("You are not in a valid room.");
        }
    }
}