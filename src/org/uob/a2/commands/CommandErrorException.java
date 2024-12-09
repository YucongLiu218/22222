package org.uob.a2.commands;

public class CommandErrorException extends Exception {
    public CommandErrorException(String message) {
        super(message);
    }
}
