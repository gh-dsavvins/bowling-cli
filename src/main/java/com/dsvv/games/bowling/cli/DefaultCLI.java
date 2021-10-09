package com.dsvv.games.bowling.cli;

import java.io.Console;

public class DefaultCLI implements CLI {

    private Console console;

    public DefaultCLI() {
        console = System.console();
        if(console == null) {
            throw new Error("System console is null");
        }
    }

    @Override
    public String read() {
        return console.readLine();
    }

    @Override
    public void print(String text) {
        System.out.print(text);
    }

}
