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
    private PApplet p;

    public PointCounter(PApplet p) {
        this.p = p;
    }

    // Shows text to screen
    public void drawText() {
        p.textSize(32);
        p.fill(0, 0, 0);
        p.text(pointsEarned, 20, 40);
    }

}

