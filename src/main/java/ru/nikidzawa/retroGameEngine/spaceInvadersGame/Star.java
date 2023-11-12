package ru.nikidzawa.retroGameEngine.spaceInvadersGame;

import ru.nikidzawa.retroGameEngine.config.Color;
import ru.nikidzawa.retroGameEngine.config.RetroGameEngine;

public class Star extends GameObject{
    public Star(double x, double y) {
        super(x, y);
    }
    public static final String STAR = "\u2605";

    public void draw (RetroGameEngine retroGameEngine) {
        retroGameEngine.changeCell((int) x, (int) y, STAR, Color.NONE, 10);
    }
}
