package shapes;

import processing.core.PApplet;

/*
 * For use in the StartMenu class
 * Color determined by master class
 */
public class MenuButton {

    private int x;
    private final int width = 250;
    private int y;
    private final int height = 80;
    private String name;
    private PApplet p;

    public MenuButton(String name, int x, int y, PApplet p) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.p = p;
    }

    public void display() {
        p.rect(x, y, width, height);
        p.textSize(30);
        p.fill(255, 255, 255);
        p.text(name, x + 85, y + 50);
    }

    public boolean isClicked() {
        return (p.mouseX > x && p.mouseX < x + width) &&
                (p.mouseY > y && p.mouseY < y + height);
    }
}