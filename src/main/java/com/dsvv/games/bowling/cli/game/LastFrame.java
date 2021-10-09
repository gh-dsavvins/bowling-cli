package com.dsvv.games.bowling.cli.game;

import com.dsvv.games.bowling.cli.CLI;

public class LastFrame extends Frame {

    private int bonusRollPinCount;

    @Override
    public void reset() {
        super.reset();
        bonusRollPinCount = 0;
    }

    @Override
    protected String updateScore(int pins) {

        if(state == State.New) {
            firstRollPinCount = pins;
            state = State.FirstRollDone;

        } else if(state == State.FirstRollDone) {
            if(!isStrike()) {
                if (firstRollPinCount + pins > 10) {
                    return "Invalid input: total number of pins player can knock down in the frame can not exceed 10.";
                }
                state = State.Done;
            } else {
                state = State.SecondRollDone;
            }
            secondRollPinCount = pins;

        } else if(state == State.SecondRollDone) {
            bonusRollPinCount = pins;
            state = State.Done;
        }

        return null;
    }

    @Override
    public int getTotalScore(Game game, int thisFrameIndex) {
        return firstRollPinCount + secondRollPinCount + bonusRollPinCount;
    }

    /**
     * Prints current frame one line at the time
     *
     * Format:
     *  --------------
     * | FR | SR | BR |
     * |--------------|
     * |           TS |
     *  --------------
     *  Legend:
     *      FR - First roll score
     *      SR - Second roll score if STRIKE -> X, if SPARE / if MISS -
     *      BR - Bonus roll
     *      TS - Total score up to that frame
     */
    @Override
    public void print(CLI cli, int line, int scoreUpToThisFrame, boolean last) {
        switch (line) {
            case 0:
            case 2:
            case 4:
                cli.print(" -----------\n");
                break;
            case 1:
                String firstRoll = isStrike() ? "X" : firstRollPinCount == 0 ? "-" : Integer.toString(firstRollPinCount);
                String seconsRoll = secondRollPinCount == 0 ? "-" : isSpare() ? "/" : isSecondStrike() ? "X" : Integer.toString(secondRollPinCount);
                String bonusRoll = bonusRollPinCount == 10 ? "X" : bonusRollPinCount == 0 ? "-" : Integer.toString(bonusRollPinCount);
                cli.print(String.format("|%2s |%2s |%2s |\n", firstRoll, seconsRoll, bonusRoll));
                break;
            case 3:
                cli.print(String.format("|       %3d |\n", scoreUpToThisFrame));
        }
    }

    @Override
    protected boolean isStrike() {
        return firstRollPinCount == 10;
    }

    private boolean isSecondStrike() {
        return firstRollPinCount == 10 && secondRollPinCount == 10;
    }

    @Override
    protected boolean isSpare() {
        return !isStrike() && firstRollPinCount + secondRollPinCount == 10;
    }

}
