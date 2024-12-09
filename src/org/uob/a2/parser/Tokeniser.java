package org.uob.a2.parser;

import java.util.ArrayList;
import java.util.List;

public class Tokeniser {
    public List<Token> tokenise(String input) {
        List<Token> tokens = new ArrayList<>();
        String[] parts = input.trim().split("\\s+");

        for (String part : parts) {
            if (isInteger(part)) {
                tokens.add(new Token(TokenType.INTEGER, part));
            } else if (part.matches("[a-zA-Z]+")) {
                tokens.add(new Token(TokenType.COMMAND, part));
            } else {
                tokens.add(new Token(TokenType.STRING, part));
            }
        }

        tokens.add(new Token(TokenType.END_OF_INPUT, null)); // End of input marker
        return tokens;
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
