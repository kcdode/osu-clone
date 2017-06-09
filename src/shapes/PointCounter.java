package shapes;

/*
 * Displays points earned
 * Tracks statistics for counter
 * Every circle has 100 point value (so divide by 100 for number of circles hit/created)
 */

import processing.core.PApplet;

public class PointCounter {
    public int possiblePoints;
    public int pointsEarned;
    public int multipliedScore;
    public int multiplier;
    public int maxMultiplier;
    private PApplet p;

    public PointCounter(PApplet p) {
        this.p = p;
    }

    // Shows text to screen
    public void drawText() {
        p.textSize(32);
        p.textAlign(p.LEFT);
        p.fill(0, 0, 0);
        p.text(multipliedScore, 20, 40);
        p.text("x" + multiplier, 20, 70);
    }


    public void send(boolean b) {
        multiplier = (b) ? multiplier+1 : 1;
        if (multiplier > maxMultiplier) maxMultiplier = multiplier;
    }

}

