package shapes;

import processing.core.PApplet;
/*
 * The individual circles that the player mouses over and 'pops'
 * User has 20 frames (1/3 second) to pop, before it expires and
 */
public class GameCircle {

    private float x, y;
    private int radius = 150;
    private PApplet p;
    private boolean hoveredOver;
    private int popCount;
    private int tillExpire;

    public GameCircle(float x, float y, PApplet applet) {
        this.x = x;
        this.y = y;
        this.p = applet;
        this.hoveredOver = false;
        tillExpire = 40;
        popCount = 0;
    }

    public void draw() {
        // Player has n frames to pop the bubble
        if (tillExpire > 0) {tillExpire --;}

        if (tillExpire == 0) {
            expireBubble();
        } else if (hoveredOver) {growThenPop();}

        p.fill(100, 100, 100);
        p.ellipse(x, y, radius, radius);
    }

    public void checkIsOver() {
        if (PApplet.dist(x,y,p.mouseX,p.mouseY) < radius)
            hoveredOver = true;
    }

    // If not touched in time limit, draw an X over it for 10 frames then pop
    private void expireBubble() {
        if (popCount < 5) {
            popCount++;
        } else if (popCount < 10) {
            radius-=5;
        }
        p.stroke(0, 0, 0);
        p.strokeWeight(5);
        p.line(x - radius/3, y - radius/3, x + radius/3, y + radius/3);
        p.line(x + radius/3, y - radius/3, x - radius/3, y + radius/3);
        p.noStroke();

    }

    // If touched in time limit, grow slightly, then quickly 'pop'
    private void growThenPop() {
        // Stops expiration timer and blocks call to expireBubble()
        tillExpire = -1;
        if (popCount < 4) {
            popCount++;
            radius+=3;
        } else if (radius > 0) {
            radius -= 9;
        }
    }

}
