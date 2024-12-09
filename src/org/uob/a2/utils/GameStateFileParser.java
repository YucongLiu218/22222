package org.uob.a2.utils;

import org.uob.a2.gameobjects.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameStateFileParser {
    public GameState parse(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Map map = null;
            Player player = new Player();
            int startX = 1, startY = 1;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String type = parts[0].trim();
                String data = parts[1].trim();

                switch (type.toLowerCase()) {
                    case "map":
                        map = parseMap(data);
                        break;
                    case "player":
                        parsePlayer(data, player);
                        break;
                    case "start":
                        int[] coordinates = parseCoordinates(data);
                        startX = coordinates[0];
                        startY = coordinates[1];
                        break;
                    case "equipment":
                        parseEquipment(data, player);
                        break;
                    default:
                        System.out.println("Unknown type in file: " + type);
                }
            }

            if (map == null) {
                throw new IllegalArgumentException("Map data is missing in the file.");
            }

            return new GameState(map, startX, startY);
        }
    }

    private Map parseMap(String data) {
        String[] dimensions = data.split("x");
        int width = Integer.parseInt(dimensions[0].trim());
        int height = Integer.parseInt(dimensions[1].trim());
        return new Map(width, height);
    }

    private void parsePlayer(String data, Player player) {
        String[] items = data.split(",");
        for (String itemData : items) {
            String[] itemParts = itemData.split("-");
            String itemName = itemParts[0].trim();
            String itemDescription = itemParts.length > 1 ? itemParts[1].trim() : "No description";
            player.addItem(new Item(itemName, itemDescription));
        }
    }

    private void parseEquipment(String data, Player player) {
        String[] parts = data.split(",");
        String name = parts[0].trim();
        String description = parts[1].trim();
        int power = parts.length > 2 ? Integer.parseInt(parts[2].trim()) : 0;
        Equipment equipment = new Equipment(name, description, power);
        player.addItem(equipment);
    }

    private int[] parseCoordinates(String data) {
        String[] parts = data.split(",");
        return new int[]{Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim())};
    }
}
