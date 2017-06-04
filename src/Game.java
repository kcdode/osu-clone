
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import shapes.GameCircle;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Game extends PApplet {

    private int prevX, prevY;

    private AudioPlayer song;
    private ArrayList<GameCircle> circles;

    float mx[] = new float[60];
    float my[] = new float[60];


    public static void main(String[] args) {
        PApplet.main("Game", args);
    }

    public void settings() {
        size(1920, 1080);
    }

    public void setup() {
        surface.setTitle("OSU! 2: Electric Boogaloo");
        circles = new ArrayList<>();
        /*
        this.song = new Minim(this).loadFile(args[0]);
        // Quit game if invalid filepath to music
        if (song == null) {
            textSize(64);
            fill(40, 50, 200);
            text("Invalid filepath, quitting in 3 seconds...", 500, 500);
            try {TimeUnit.SECONDS.sleep(3); } catch (InterruptedException ex) {System.exit(0);}
            System.exit(0); }
        song.play();
        */

    }

    public void draw() {
//        noStroke();
        background(240,240,240);
//        fill(100, 50, 200);
//        GameCircle c = new GameCircle(500, 500, this);
//        c.draw();

        int which = frameCount % 60;
        mx[which] = mouseX;
        my[which] = mouseY;

        for (int i = 0; i < 60; i++) {
            // which+1 is the smallest (the oldest in the array)
            int index = (which+1 + i) % 60;
//            System.out.println(" Which" + (which +1));
//            System.out.println(" Which" + (which +1 + i));
//            System.out.println(index);
            GameCircle f = new GameCircle(mx[index], my[index], this);
            f.draw();
            circles.add(0, f);
            if (circles.size() > 60) circles.remove(59);
        }

    }
}
