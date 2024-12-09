package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room {
    private String name;
    private String description;
    private char symbol;
    private int x;
    private int y;
    private String type;
    private String puzzleQuestion;
    private int puzzleAnswer;
    private boolean visited;
    private List<Item> items;
    private List<GameObject> features;

    public Room(String name, String description, char symbol, int x, int y) {
        this(name, description, symbol, x, y, "normal");
    }

    public Room(String name, String description, char symbol, int x, int y, String type) {
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.x = x;
        this.y = y;
        this.type = type;
        this.items = new ArrayList<>();
        this.visited = false;
        this.features = new ArrayList<>();
    }

    public Room(String name, String description, char symbol, int x, int y, String type, String puzzleQuestion, int puzzleAnswer) {
        this(name, description, symbol, x, y, type);
        this.puzzleQuestion = puzzleQuestion;
        this.puzzleAnswer = puzzleAnswer;
    }

    public String getName() {
        return name;
    }

    public void addFeature(GameObject feature) {
        features.add(feature);
    }

    public String getDescription() {
        return description;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
            System.out.println(item.getName() + " added to your items.");
        } else {
            System.out.println("Item already in your inventory: " + item.getName());
        }
    }
    

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public Item getItemByName(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public boolean hasItem(String itemName) {
        return items.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }    

    public boolean removeItem(String itemName) {
        Item itemToRemove = getItemByName(itemName);
        if (itemToRemove != null) {
            items.remove(itemToRemove);
            return true;
        }
        return false;
    }

    public int enter(GameState gameState) {
        // 对 exit 房间单独处理，不受 visited 限制
        if ("exit".equals(type)) {
            System.out.println("You are in the Exit Room.");
    
            boolean hasKey = gameState.hasItem("Key");
            int score = gameState.getScore();
    
            if (!hasKey && score < 55) {
                System.out.println("You cannot exit. You need a key and at least 55 points.");
                return 0;
            }
    
            if (!hasKey) {
                System.out.println("You cannot exit. You need a key.");
                return 0;
            }
    
            if (score < 55) {
                System.out.println("Your score is too low to exit. You need at least 55 points.");
                return 0;
            }
    
            Scanner scanner = gameState.getScanner();
            System.out.println("Do you want to use the key to exit? (use/no)");
    
            if (scanner.hasNextLine()) {
                String response = scanner.nextLine().trim().toLowerCase();
    
                if ("use".equals(response)) {
                    System.out.println("Congratulations! You've successfully exited the maze.");
                    gameState.setPlaying(false); // 结束游戏
                } else if ("no".equals(response)) {
                    System.out.println("You chose not to use the key. You can try again later.");
                } else {
                    System.out.println("Invalid input. The key remains unused.");
                }
            } else {
                System.out.println("No input detected. Please try again.");
            }
            return 0; // 确保 exit 房间不返回加分
        }
    
        // 其他房间逻辑
        if (visited) {
            System.out.println("You revisit " + name + ". No additional points awarded.");
            return 0;
        }
    
        int scoreChange = 0;
    
        switch (type) {
            case "normal":
                System.out.println("You entered a normal room: " + description + " (+7 points)");
                scoreChange = 7;
                break;
    
            case "gem":
                if (!gameState.hasItem("Gem")) {
                    System.out.println("You found a gem! Added to your inventory.");
                    gameState.addItem(new Item("Gem", "A shiny gem"));
                }
                scoreChange = 5;
                break;
    
            case "key":
                if (!gameState.hasItem("Key")) {
                    System.out.println("You found a key! Added to your items.");
                    gameState.addItem(new Item("Key", "A rusty key"));
                }
                scoreChange = 5;
                break;
    
            case "gun":
                if (!gameState.hasItem("Gun")) {
                    System.out.println("You found a gun! Added to your items.");
                    gameState.addItem(new Item("Gun", "A powerful weapon"));
                } else {
                    System.out.println("You already have a gun.");
                }
                break;
    
            case "bomb":
                System.out.println("Boom! You hit a bomb. (-5 points)");
                scoreChange = -5;
                break;
    
            case "puzzle":
                scoreChange = solvePuzzle(gameState);
                break;
    
            case "enemy":
                scoreChange = handleEnemy(gameState);
                break;
    
            default:
                System.out.println("You entered an unknown room.");
                break;
        }
    
        visited = true; // 标记非 exit 房间为已访问
        return scoreChange;
    }        


    public int solvePuzzle(GameState gameState) {
        if (visited) {
            System.out.println("You have already solved the puzzle in this room.");
            return 0;
        }
    
        System.out.println("Puzzle: " + puzzleQuestion);
        System.out.print("Your answer: ");
        Scanner scanner = gameState.getScanner();
        if (scanner.hasNextInt()) {
            int answer = scanner.nextInt();
            if (answer == puzzleAnswer) {
                System.out.println("Correct! +10 points.");
                return 10;
            } else {
                System.out.println("Wrong answer. Try again next time.");
                return 0;
            }
        } else {
            System.out.println("Invalid input.");
            scanner.next();
            return 0;
        }
    }
    
    private int handleEnemy(GameState gameState) {
        if (!gameState.hasItem("Gun")) {
            System.out.println("You encountered an enemy but have no gun! (-10 points)");
            return -10;
        }
    
        Scanner scanner = gameState.getScanner();
        System.out.println("You encountered an enemy! Do you want to use your gun? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();
    
        if ("yes".equals(response)) {
            System.out.println("You defeated the enemy with your gun! (+10 points)");
            return 10;
        } else if ("no".equals(response)) {
            System.out.println("You chose not to use the gun and ran away! (-5 points)");
            return -5;
        } else {
            System.out.println("Invalid input. The enemy attacked you! (-10 points)");
            return -10;
        }
    }
}    