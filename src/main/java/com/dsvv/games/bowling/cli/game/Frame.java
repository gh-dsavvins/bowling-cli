package com.dsvv.games.bowling.cli.game;

import com.dsvv.games.bowling.cli.CLI;
import lombok.Getter;

@Getter
public class Frame {

    private static final int ROLL_OUTCOME_STRIKE = -1;
    private static final int ROLL_OUTCOME_SPARE = -2;

    protected State state;
    protected int firstRollPinCount;
    protected int secondRollPinCount;

    public Frame() {
        reset();
    }

    public int getTotalScore(Game game, int thisFrameIndex) {

        int thisScore = firstRollPinCount + secondRollPinCount;

        if(isSpare()) {
            Frame nextFrame = game.getFrame(thisFrameIndex + 1);
            thisScore += nextFrame.getFirstRollPinCount();

        } else if(isStrike()) {
            Frame nextFrame = game.getFrame(thisFrameIndex + 1);
            thisScore += nextFrame.getFirstRollPinCount() + nextFrame.getSecondRollPinCount();
            if(!(nextFrame instanceof LastFrame) && nextFrame.isStrike()) {
                nextFrame = game.getFrame(thisFrameIndex + 2);
                thisScore += nextFrame.getFirstRollPinCount();
            }
        }

        return thisScore;
    }

    /**
     * Prints current frame one line at the time
     *
     * Format:
     *  ---------
     * | FR | SR |
     * |---------|
     * |      TS |
     *  ---------
     *  Legend:
     *      FR - First roll score if MISS -
     *      SR - Second roll score if STRIKE -> X, if SPARE / if MISS -
     *      TS - Total score up to that frame
     */
    public void print(CLI cli, int line, int scoreUpToThisFrame, boolean last) {
        switch (line) {
            case 0:
            case 2:
            case 4:
                cli.print(" -----" + (last ? "\n" : ""));
                break;
            case 1:
                String firstRoll = isStrike() ? "" : firstRollPinCount == 0 ? "-" : Integer.toString(firstRollPinCount);
                String secondRoll = isStrike() ? "X" : isSpare() ? "/" : secondRollPinCount == 0 ? "-" : Integer.toString(secondRollPinCount);
                cli.print(String.format("|%2s|%2s" + (last ? "|\n" : ""), firstRoll, secondRoll));
                break;
            case 3:
                cli.print(String.format("|  %3s" + (last ? "|\n" : ""), state == State.New ? "-" : Integer.toString(scoreUpToThisFrame)));
                break;
        }
    }

    public void reset() {
        state = State.New;
        firstRollPinCount = 0;
        secondRollPinCount = 0;
    }

    public String roll(String pinsDown) {
        if(state == State.Done) {
            return "ERROR - Frame state is Done";
        }
        int pins = rollInputToInt(pinsDown);
        if(pins == -1) {
            return "Invalid input, please type number of pins you knocked down in the roll then press 'Enter'";
        }
        return updateScore(pins);
    }

    protected String updateScore(int pins) {

        if(state == State.New) {
            firstRollPinCount = pins;
            if(isStrike()) {
                state = State.Done;
            } else {
                state = State.FirstRollDone;
            }

        } else if(state == State.FirstRollDone) {
            if(firstRollPinCount + pins > 10) {
                return "Invalid input: total number of pins player can knock down in the frame can not exceed 10.";
            }
            secondRollPinCount = pins;
            state = State.Done;
        }

        return null;
    }

    private int rollInputToInt(String pinsDown) {
        switch (pinsDown) {
            case "0":
                return 0;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            default:
                return -1;
        }
    }

    protected boolean isStrike() {
        return firstRollPinCount == 10 && secondRollPinCount == 0;
    }

    protected boolean isSpare() {
        return !isStrike() && firstRollPinCount + secondRollPinCount == 10;
    }

}
