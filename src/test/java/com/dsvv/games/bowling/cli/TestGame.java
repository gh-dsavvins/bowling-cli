package com.dsvv.games.bowling.cli;

import com.dsvv.games.bowling.cli.game.Frame;
import com.dsvv.games.bowling.cli.game.Game;
import org.junit.Test;

public class TestGame {

    @Test
    public void testFrameNonDigitsRoll() {
        Frame frame = new Frame();
        for (int b = 0; b <= Byte.MAX_VALUE; b++) {
            frame.reset();
            char c = (char) b;
            if (Character.isDigit(c)) {
                continue;
            }
            String error = frame.roll("" + c);
            assert error != null;
        }
    }

    @Test
    public void testGame() {
        Game game = new Game();
        testGame(game, "0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0", 0);
        testGame(game, "1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1", 20);
        testGame(game, "10, 10, 10, 10, 10, 10, 10, 10, 10, 10,10,10", 300);
        testGame(game, "10, 10, 10, 10, 10, 10, 10, 10, 10, 10,10,9", 299);
        testGame(game, "0,9, 10, 10, 10, 10, 10, 10, 10, 10, 10,10,9", 278);
        testGame(game, "1,0", 1);
        testGame(game, "0,1", 1);
        testGame(game, "10, 0", 10);
        testGame(game, "0,10", 10);
        testGame(game, "0,10, 1,7", 19);
        testGame(game, "0,10, 1,9, 1", 23);
        testGame(game, "0,10, 10, 1,0, 5", 37);
    }

    private static void testGame(Game game, String pinsDown, int expGameScore) {
        game.reset();
        for(String pd: pinsDown.split(",")) {
            assert game.roll(pd.trim()) == null;
        }
        assert game.getTotalScore() == expGameScore;
    }


    @Test
    public void testPerfectGame() {
        Game game = new Game();
        int[] expScore = new int[] { 0, 10, 30, 60, 90, 120, 150, 180, 210, 240, 270, 290, 300 };
        for(int roll = 0; roll >= expScore.length; roll++) {
            assert game.getTotalScore() == expScore[roll];
            if(game.isOver()) {
                break;
            }
            game.roll("10");
        }
    }

}
