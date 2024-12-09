package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameState {
    private final Map map;
    private final Player player;
    private int playerX;
    private int playerY;
    private int score;
    private boolean isPlaying;
    private final List<Item> items = new ArrayList<>();
    private final Scanner scanner;

    public GameState(Map map, int startX, int startY) {
        this.map = map;
        this.player = new Player();
        this.playerX = startX;
        this.playerY = startY;
        this.score = 0;
        this.isPlaying = true;
        this.scanner = new Scanner(System.in);
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getScore() {
        return score;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
    
    public void movePlayer(String direction) {
        int newX = playerX;
        int newY = playerY;

        switch (direction.toLowerCase()) {
            case "north":
                newY -= 1;
                break;
            case "south":
                newY += 1;
                break;
            case "east":
                newX += 1;
                break;
            case "west":
                newX -= 1;
                break;
            default:
                System.out.println("Unknown direction: " + direction);
                return;
        }

        if (map.isValidPosition(newX, newY)) {
            Room room = map.getRoom(newX, newY);
            if (room != null) {
                playerX = newX;
                playerY = newY;

                System.out.println("You moved to: " + room.getName());
                System.out.println("You are in: " + room.getName());

                int scoreChange = room.enter(this);
                addScore(scoreChange);
            } else {
                System.out.println("There is no room in that direction.");
            }
        } else {
            System.out.println("Invalid move: Out of bounds.");
        }
    }

    public boolean updatePlayerPosition(int newX, int newY) {
        if (map.isValidPosition(newX, newY)) {
            playerX = newX;
            playerY = newY;
            Room room = map.getRoom(newX, newY);
            if (room != null) {
                int scoreChange = room.enter(this);
                addScore(scoreChange);
            }
            map.display(playerX, playerY);
            return true;
        }
        System.out.println("Cannot move to position: (" + newX + ", " + newY + ")");
        return false;
    }

    public void addScore(int points) {
        this.score += points;
        System.out.println("Score updated by " + points + ". Current Score: " + this.score);
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " added to your items.");
    }

    public boolean removeItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(i);
                System.out.println(itemName + " has been removed from your inventory.");
                return true;
            }
        }
        System.out.println(itemName + " not found in your inventory.");
        return false;
    }


    public boolean hasItem(String itemName) {
        return items.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("You have no items.");
        } else {
            System.out.println("Your items: ");
            items.forEach(item -> System.out.println("- " + item.getName()));
        }
    }

    public Object getItemByName(String itemName) {
        return null;
    }

    public CharSequence getRoomItems() {
        return null;
    }
}