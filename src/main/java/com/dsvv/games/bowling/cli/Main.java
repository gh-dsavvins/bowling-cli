package com.dsvv.games.bowling.cli;

import com.dsvv.games.bowling.cli.game.Game;

public class Main {

    private static void printHeader(CLI cli) {
        cli.print("Command line bowling game score keeper\n");
        cli.print("\tEnter count of knocked down pins for the roll, then press Enter.\n");
        cli.print("\tScore keeper will calculate and prnt current score and game status.\n");
        cli.print("\tIf at any point you wish to reset the game, please type 'reset', then press Enter\n");
        cli.print("\tIf you wish to quit plaese press <Ctrl>+C\n");
    }

    private static void printNewGame(CLI cli) {
        cli.print("New Game\n");
        cli.print("Please enter knocked pin count...\n");
    }

    public static void main(String[] args) {
        CLI cli = new DefaultCLI();
        printHeader(cli);
        Game game = new Game();
        printNewGame(cli);
        while(true) {
            String read = cli.read().trim();

            if(isReset(read)) {
                game.reset();
                printNewGame(cli);
                continue;
            }

            String error = game.roll(read);
            if(error != null) {
                cli.print(error + '\n');
            } else {
                game.print(cli);
            }

            if(game.isOver()) {
                game.reset();
                printNewGame(cli);
            }
        }
    }

    private static boolean isReset(String read) {
        return read.toLowerCase().equals("reset");
    }

}
