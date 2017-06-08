package shapes;

import processing.core.PApplet;
/*
 * The individual circles that the player mouses over and 'pops'
 * User has 40 frames (2/3 second) to pop, before it expires
 * Increase tillExpire to increase time frame and lower difficulty
 */
public class GameCircle {

    private final float  x, y;
    private int radius;
    private int popCount;
    private int tillExpire;
    private int[] rgb;
    private final PApplet p;
    private final PointCounter counter;
    private boolean counted;
    private boolean hoveredOver;


    public GameCircle(float x, float y, int[] rgb, PApplet applet, PointCounter counter) {
        this.x = x;
        this.y = y;
        this.p = applet;
        this.rgb = rgb;
        this.counter = counter;
        this.hoveredOver = false;
        radius = 100;
        tillExpire = 40;
        popCount = 0;
        counted = false;
    }

    public GameCircle(float[] pos, int[] rgb, PApplet applet, PointCounter counter) {
        this.x = pos[0];
        this.y = pos[1];
        this.p = applet;
        this.rgb = rgb;
        this.counter = counter;
        counter.possiblePoints+=100;
        this.hoveredOver = false;
        radius = 100;
        tillExpire = 40;
        popCount = 0;
        counted = false;
    }

    public void draw() {
        p.fill(rgb[0], rgb[1], rgb[2]);
        p.ellipse(x, y, radius, radius);

        // Player has n frames to pop the bubble
        if (tillExpire > 0) {tillExpire --;}
        if (tillExpire == 0) {
            expireBubble();
        } else if (hoveredOver) {
            growThenPop();
        }

    }

    public void checkIsOver() {
        if (PApplet.dist(x,y,p.mouseX,p.mouseY) < radius)
            hoveredOver = true;
    }

    // If not touched in time limit, draw an X over it for 10 frames then pop
    private void expireBubble() {
        if (popCount < 5) {
            popCount++;
            p.fill(0, 0, 0);
            p.text("Missed!", 20, 80);
        } else if (popCount < 10 && radius > 0) {
            radius-=5;
        }

        if (radius > 0) {
            p.stroke(0, 0, 0);
            p.strokeWeight(5);
            p.line(x - radius / 2, y - radius / 2, x + radius / 2, y + radius / 2);
            p.line(x + radius / 2, y - radius / 2, x - radius / 2, y + radius / 2);
            p.noStroke();
        }

    }

    // If touched in time limit, grow slightly, then quickly 'pop'
    private void growThenPop() {
        if (!counted) {
            counted = true;
            counter.pointsEarned += 100;
        }
        // Stops expiration timer and blocks call to expireBubble()
        tillExpire = -1;
        if (popCount < 4) {
            popCount++;
            radius += 2;
        } else if (radius > 0) {
            radius -= 12;
        }
    }

    // For testing
    public int getRadius() {
        return radius;
    }

}
