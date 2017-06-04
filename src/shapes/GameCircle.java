package shapes;

import processing.core.PApplet;
/*
 * The individual circles that the player mouses over and 'pops'
 */
public class GameCircle {

    private float x, y;
    private static int radius = 150;
    private PApplet p;
    private int tillPop;

    public GameCircle(float x, float y, PApplet applet) {
        this.x = x;
        this.y = y;
        this.p = applet;
        int tillPop = 5; // Must hover for 5 frames before popping
    }

    public void draw() {
        //p.fill(100, 100, 100);
        p.ellipse(x, y, radius, radius);
    }

    public boolean isOver() {
        return (PApplet.dist(x,y,p.mouseX,p.mouseY) < radius);
    }

    public void pop() {

    }
}
