package org.uob.a2;

import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.Parser;

public class Game {
    private final GameState gameState;
    private final Parser parser;
    private boolean isPlaying;

    public Game() {
        this.gameState = new GameState(new Map(5, 5), 1, 1);
        this.parser = new Parser(gameState);
        this.isPlaying = true;
        setupRooms();
    }

    private void setupRooms() {
        gameState.getMap().addRoom(new Room("Starting Room", "A safe starting point.", 'S', 1, 1, "starting"), 1, 1);
        gameState.getMap().addRoom(new Room("Gem Room", "A room with a hidden gem.", 'G', 3, 3, "gem"), 3, 3);
        gameState.getMap().addRoom(new Room("Enemy Room", "A room with an Enemy lurking.", 'F', 4, 5, "enemy"), 4, 5);
        gameState.getMap().addRoom(new Room("Puzzle Room1", "Solve the riddle to proceed.", 'P', 2, 4, "puzzle", "What is 6 x 7?", 42), 2, 4);
        gameState.getMap().addRoom(new Room("Puzzle Room2", "Another riddle awaits.", 'P', 4, 4, "puzzle", "What is 10 + 20?", 30), 4, 4);
        gameState.getMap().addRoom(new Room("Key Room", "An important key lies in this room.", 'K', 5, 2, "key"), 5, 2);
        gameState.getMap().addRoom(new Room("Gun Room", "A room containing a powerful weapon.", 'U', 1, 5, "gun"), 1, 5);
        gameState.getMap().addRoom(new Room("Bomb Room", "Watch out! There's a bomb here.", 'B', 3, 5, "bomb"), 3, 5);
        gameState.getMap().addRoom(new Room("Exit Room", "The way out. You need a key and a score over 55.", 'E', 5, 5, "exit"), 5, 5);
        for (int x = 1; x <= 5; x++) {
            for (int y = 1; y <= 5; y++) {
                if (gameState.getMap().getRoom(x, y) == null) {
                    gameState.getMap().addRoom(new Room("Regular Room", "A regular room.", '.', x, y, "normal"), x, y);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting Maze Adventure: Path of the Brave");
        System.out.println("Get ready for an exciting journey through the maze!");
        new Game().play();
    }

    public void play() {
        while (isPlaying && gameState.isPlaying()) {
            gameState.getMap().display(gameState.getPlayerX(), gameState.getPlayerY());
            System.out.print("Enter command: ");
    
            try {
                if (gameState.getScanner().hasNextLine()) {
                    String input = gameState.getScanner().nextLine().trim().toLowerCase();
                    parser.parseAndExecute(input);
    
                    Room currentRoom = gameState.getMap().getRoom(gameState.getPlayerX(), gameState.getPlayerY());
                    if (currentRoom != null) {
                        System.out.println("You are in: " + currentRoom.getName());
                    } else {
                        System.out.println("You are not in a valid room.");
                    }
    
                    if ("quit".equals(input)) {
                        isPlaying = false;
                    }
                } else {
                    System.out.println("No more input. Exiting game.");
                    break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                break;
            }
        }
    
        gameState.closeScanner();
        System.out.println("Game Over. Final Score: " + gameState.getScore());
    }
}    