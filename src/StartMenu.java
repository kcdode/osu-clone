
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import shapes.MenuButton;

public class StartMenu extends PApplet {

    private MenuButton play;
    private MenuButton load;
    private int v = 1;

    public static void main(String[] args) {
        PApplet.main("StartMenu", args);
    }

    public void settings() {
        size(1920, 1080);
    }

    public void draw() {
        background(255, 255, 240);


        if (mousePressed) {
            if (play.isClicked()) {
                System.out.println("Play");
            } else if (load.isClicked()) {
                System.out.println("load");
            }
        }

        // Fancy color changing of the tile text (alternates 4 times/second)
        if (frameCount % 60 < 15) v = 2;
        else if (frameCount % 60 < 30) v = 1;
        else if (frameCount % 60 < 45) v = 2;
        else if (frameCount % 60 < 60) v = 1;

        fill(frameCount % 60 * 15,  v* 40, v * 122);
        textSize(64);
        text("OSU! 2: Electric Boogaloo", 575, 350 + (v -1)*20);


        // Draw Buttons
        fill(0 , 191, 255);
        play.display();
        fill(0 , 191, 255);
        load.display();

    }

    public void setup() {
        colorMode(RGB, 255, 255, 255);
        noStroke();
        background(255, 255, 255);

        surface.setTitle("OSU! 2: Electric Boogaloo");
        play = new MenuButton("PLAY", 810, 700, this);
        load = new MenuButton("LOAD", 810, 800, this);




        // Menu song (beginning of Sweet Dreams by Eurythmics)
        AudioPlayer audio = new Minim(this).loadFile("music/sweetdreams.mp3");
        audio.play();
        audio.loop();
    }

}