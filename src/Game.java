
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;
import shapes.GameCircle;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Game extends PApplet {

    private AudioPlayer song;
    private ArrayList<GameCircle> circles;
    private BeatDetect bd;

    private float mx[] = new float[60];
    private float my[] = new float[60];


    public static void main(String[] args) {
        PApplet.main("Game", args);
    }

    public void settings() {
        size(1920, 1080);
    }

    public void setup() {
        surface.setTitle("OSU! 2: Electric Boogaloo");
        circles = new ArrayList<>();
//        bd = new BeatDetect(song.bufferSize(), song.sampleRate());

//        this.song = new Minim(this).loadFile(args[0]);
        // Quit game if invalid filepath to music
//        if (song == null) {
//            textSize(64);
//            fill(40, 50, 200);
//            text("Invalid filepath, quitting in 3 seconds...", 500, 500);
//            try {TimeUnit.SECONDS.sleep(3); } catch (InterruptedException ex) {System.exit(0);}
//            System.exit(0); }
//        song.play();
        noStroke();
        circles.add(new GameCircle(500, 500, this));
    }

    public void draw() {
        background(240,240,240);
        fill(100, 50, 200);

        for (GameCircle c: circles) {
            c.checkIsOver();
            c.draw();
        //    System.out.print(c.getRadius() + " ");
        }   //System.out.println();

        if (circles.size() > 60) circles.remove(0);

    }
}
