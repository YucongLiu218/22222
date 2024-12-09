package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Item> items;
    private int score;

    public Player() {
        this.items = new ArrayList<>();
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
        System.out.println("Score updated by " + points + ". Current score: " + score);
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println("You picked up: " + item.getName());
    }

    public boolean removeItem(String itemName) {
        return items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public boolean hasItem(String itemName) {
        return items.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
}
