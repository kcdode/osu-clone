import processing.core.PVector;

import java.util.Random;

/*
 * Determines path of motion
 * Selects a random point on the frame, then creates a parabolic path towards it (can inc/dec steepness via exponent variable)
 * Every new circle is added on that path. 
 * When the point is reached or exceeded by a circle, the program chooses a new one
 */
        
import static processing.core.PApplet.pow;


class Motion {

    private float beginX, beginY;
    private float endX, endY;
    private float toX, toY;
    private PVector pos;
    private final float exponent = 2;   // Steepness of curve
    private Random rand;
    private float step = 0.01f;    // Distance between circles
    private float pct = 0.0f;      // Percentage traveled (0.0 to 1.0)


    Motion() {
        rand = new Random();
        pos = new PVector(1920 / 2, 1080 / 2);
        beginX = 1920/2f;
        beginY = 1080/2f;
        endX = rand.nextInt(1920);
        endY = rand.nextInt(1080);
        toX = endX - beginX;
        toY = endY - beginY;
    }

    // Return x/y for next circle.
    // The 'stronger' the beat, the larger the distance from previous point
    float[] nextCircle(int n) {
        switch (n) {
            case 100:
                step = .15f;
                break;
            case 150:
                step = .25f;
                break;
            case 200:
                step = .35f;
                break;
        }
        pct += step;
        if (pct >= 1.0) nextPoint();
        if (pct < 1.0) {
            pos.set(beginX + (pct * toX), beginY + pow(pct, exponent) * toY);
        }
        return pos.array();
    }

    // Every time random point is reached, select a new one
    private void nextPoint() {
        pct = 0.0f;
        beginX = pos.x;
        beginY = pos.y;
        endX = rand.nextInt(1900 - 20 ) + 20;
        endY = rand.nextInt(1000 - 20 ) + 20;
        toX = endX - beginX;
        toY = endY - beginY;
    }

}
