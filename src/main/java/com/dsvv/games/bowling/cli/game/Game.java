package com.dsvv.games.bowling.cli.game;

import com.dsvv.games.bowling.cli.CLI;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Game {

    private final Frame[] frames;
    private int frameIndex;

    public Game() {
        frames = new Frame[10];
        IntStream.range(0, 9).forEach(i -> frames[i] = new Frame());
        frames[9] = new LastFrame();
        reset();
    }

    public void reset() {
        Arrays.stream(frames).forEach(Frame::reset);
        frameIndex = 0;
    }

    public String roll(String pinsDown) {
        if(isOver()) {
            return "ERROR - SHOULD NEVER HAPPEN - Game is over.";
        }
        String error = null;
        if((error = getCurrentFrame().roll(pinsDown)) != null) {
            return error;
        }
        if(getCurrentFrame().getState() == State.Done) {
            frameIndex++;
        }
        return null;
    }

    private Frame getCurrentFrame() {
        return frames[frameIndex];
    }

    public boolean isOver() {
        return frameIndex == frames.length;
    }

    /**
     * Prints current status of the game.
     */
    public void print(CLI cli) {
        cli.print(String.format("Frame #%3d, Game score:%4d\n", frameIndex + 1, getTotalScore(frames.length - 1)));
        for(int line = 0; line < 5; line++) {
            for (int ix = 0; ix <= frameIndex && ix < frames.length; ix++) {
                int totalScore = getTotalScore(ix);
                Frame frame = getFrame(ix);
                frame.print(cli, line, totalScore, ix == frameIndex);
            }
        }
    }

    public Frame getFrame(int index) {
        return frames[index];
    }

    public int getTotalScore() {
        return getTotalScore(frames.length - 1);
    }

    private int getTotalScore(int upToFrame) {
        int totalScore = 0;
        for(int i = 0; i <= upToFrame; i++) {
            totalScore += getFrame(i).getTotalScore(this, i);
        }
        return totalScore;
    }

}
