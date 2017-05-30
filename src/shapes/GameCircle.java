package shapes;

import processing.core.PApplet;
/*
 * The individual circles that the player mouses over and 'pops'
 */
public class GameCircle {

    private int x, y;
    private static int radius; // TODO: Find good radius size
    private PApplet p;

    public GameCircle(int x, int y, PApplet applet) {
        this.x = x;
        this.y = y;
        this.p = applet;
    }

    public void draw() {
        p.ellipse(x, y, radius, radius);
    }
}
