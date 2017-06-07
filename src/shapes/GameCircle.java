package shapes;

import processing.core.PApplet;
/*
 * The individual circles that the player mouses over and 'pops'
 */
public class GameCircle {

    private float x, y;
    private int radius = 150;
    private PApplet p;
    private boolean hoveredOver;
    private int grow3 = 0;

    public GameCircle(float x, float y, PApplet applet) {
        this.x = x;
        this.y = y;
        this.p = applet;
        this.hoveredOver = false;
    }

    public int getRadius() {
        return radius;
    }

    public void draw() {
        p.fill(100, 100, 100);
        // If hovered over, grow slightly, then quickly 'pop'
        if (hoveredOver) {
            if (grow3 < 4) {
                grow3++;
                radius+=3;
            }
            else if (radius > 0)
                radius-=9;
        }
        p.ellipse(x, y, radius, radius);
    }

    public void checkIsOver() {
        if (PApplet.dist(x,y,p.mouseX,p.mouseY) < radius)
            hoveredOver = true;
    }

}
