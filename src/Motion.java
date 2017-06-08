import processing.core.PApplet;
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
    private float prevX, prevY;
    private PVector pos;
    private PApplet p;
    private final float exponent = 2;   // Steepness of curve
    private Random rand;
    private float step = 0.01f;    // Distance between circles
    private float pct = 0.0f;      // Percentage traveled (0.0 to 1.0)
    private float multiple;


    Motion(PApplet p) {
        rand = new Random();
        this.p = p;
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
                step = .1f;
                break;
            case 150:
                step = .12f;
                break;
            case 200:
                step = .14f;
                break;
        }
        pct += step;
        if (pct >= 1.0) nextPoint();
        if (pct < 1.0) {
            pos.set(beginX + (pct * toX), beginY + pow(pct, exponent) * toY);
        }
        return pos.array();
    }

    void drawLine(int a, int b, int c) {
        p.stroke(a, b, c);
        p.strokeWeight(3);
        float temp = pct;
//        prevX = beginX;
//        prevY = beginY;
        while (temp < 1.0) {
//            System.out.println(prevX + "  " + prevY);
            temp+=.01f;
            p.line(prevX, prevY, beginX + (pct * toX), beginY + pow(pct, exponent) * toY);
        }

        prevX = pos.array()[0];
        prevY = pos.array()[1];
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
        prevX = beginX;
        prevY = beginY;

        while (Math.abs(toX + toY) < 900) {
            nextPoint();
        }
    }

}
