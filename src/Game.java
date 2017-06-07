
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;
import shapes.GameCircle;
import shapes.PointCounter;

import java.util.ArrayList;
import java.util.Random;

public class Game extends PApplet {

    private AudioPlayer song;
    private ArrayList<GameCircle> circles;
    private BeatDetect bd;
    private PointCounter counter;
    private Random rand;


    public static void main(String[] args) {
        PApplet.main("Game", args);
    }

    public void settings() {
        size(1920, 1080);
    }

    public void setup() {
        surface.setTitle("OSU! 2: Electric Boogaloo");
        counter = new PointCounter();
        rand = new Random();
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
    }

    public void draw() {
        background(240,240,240);

        if (frameCount % 30 == 0) {
            circles.add(new GameCircle(rand.nextInt(1920), rand.nextInt(1080), this));
        }

        for (GameCircle c: circles) {
            c.checkIsOver();
            c.draw();
        }

        // Keep list trimmed of useless, clicked on circles
        if (circles.size() > 100) circles.remove(0);

    }
}
