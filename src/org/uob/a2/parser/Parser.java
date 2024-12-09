package org.uob.a2.parser;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.GameState;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private final Map<String, Command> commands;

    public Parser(GameState gameState) {
        commands = new HashMap<>();
        commands.put("drop", new Drop(gameState)); 
        commands.put("get", new Get(gameState));
        commands.put("help", new Help(gameState));
        commands.put("look", new Look(gameState));
        commands.put("quit", new Quit(gameState));
        commands.put("status", new Status(gameState));
        commands.put("use", new Use(gameState));
        commands.put("move", new Move(gameState));
        commands.put("north", new Move(gameState));
        commands.put("south", new Move(gameState));
        commands.put("east", new Move(gameState));
        commands.put("west", new Move(gameState));
}

    public void parseAndExecute(String input) {
        if (input == null || input.isBlank()) {
            System.out.println("Please enter a command.");
            return;
        }

        String[] tokens = input.trim().split("\\s+");
        String commandWord = tokens[0].toLowerCase();

        Command command = commands.get(commandWord);
        if (command != null) {
            String[] args = tokens.length > 1 ? new String[]{tokens[1]} : new String[]{};
            command.execute(args);
        } else {
            System.out.println("Unknown command. Type HELP for a list of commands.");
        }
    }
}
