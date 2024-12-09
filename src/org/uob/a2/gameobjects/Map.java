package org.uob.a2.gameobjects;

public class Map {
    private final int width;
    private final int height;
    private final Room[][] rooms;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.rooms = new Room[width][height];
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 1 && x <= width && y >= 1 && y <= height;
    }

    public void addRoom(Room room, int x, int y) {
        if (x < 1 || x > width || y < 1 || y > height) {
            throw new IllegalArgumentException("Invalid room coordinates: " + x + ", " + y);
        }
        if (rooms[x - 1][y - 1] != null) {
            throw new IllegalArgumentException("Room already exists at: " + x + ", " + y);
        }
        rooms[x - 1][y - 1] = room;
    }

    public Room getRoom(int x, int y) {
        if (x < 1 || x > width || y < 1 || y > height) {
            return null;
        }
        return rooms[x - 1][y - 1];
    }

    public void display(int playerX, int playerY) {
        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                if (x == playerX && y == playerY) {
                    System.out.print("* ");
                } else if (getRoom(x, y) != null) {
                    System.out.print(getRoom(x, y).getSymbol() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
