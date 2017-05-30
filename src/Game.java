
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

import java.util.concurrent.TimeUnit;


public class Game extends PApplet {

    private AudioPlayer song;

    public static void main(String[] args) {
        PApplet.main("Game", args);
    }

    public void settings() {
        size(1920, 1080);
    }

    public void setup() {
        background(255,255,255);
        this.song = new Minim(this).loadFile(args[0]);
        // Quit game if invalid filepath to music
        if (song == null) {
            textSize(64);
            fill(40, 50, 200);
            text("Invalid filepath, quitting in 3 seconds...", 500, 500);
            try {TimeUnit.SECONDS.sleep(3); } catch (InterruptedException ex) {System.exit(0);}
            System.exit(0); }
        song.play();
    }

    public void draw() {


    }
}
