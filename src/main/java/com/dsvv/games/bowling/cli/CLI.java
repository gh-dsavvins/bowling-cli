package com.dsvv.games.bowling.cli;

public interface CLI {

    /**
     * Reads input from interface user
     * Blocks until ENTER depressed
     */
    String read();

    /**
     * Writes output line to console NO new line added
     */
    void print(String text);

}
