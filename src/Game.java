
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import shapes.GameCircle;
import shapes.PointCounter;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game extends PApplet {

    private AudioPlayer             song;
    private ArrayList<GameCircle>   circles;
    private ArrayList<Float>        bands;
    private BeatDetect              beat;
    private FFT                     fft;
    private Thread                  t;
//    private PointCounter            counter;
    private Random                  rand;

    private int                     xpos, ypos;
    private int                     totalPoints, pointsEarned;
    private int                     totalCircles, circlesHit;


    public static void main(String[] args) {
        PApplet.main("Game", args);
    }

    public void settings() {
        size(1920, 1080);
    }

    public void setup() {
        surface.setTitle("OSU! 2: Electric Boogaloo");
        noStroke();

        circles     = new ArrayList<>();
        bands       = new ArrayList<>();
//        counter     = new PointCounter();
        rand        = new Random();
        song        = new Minim(this).loadFile(args[0]);
        checkNullSong();
        beat        = new BeatDetect(song.bufferSize(), song.sampleRate());
        fft         = new FFT(song.bufferSize(), song.sampleRate());
        t           = new Thread(this::drawFFT);
        xpos        = 0;
        ypos        = 100;

        totalCircles = circlesHit = totalPoints = pointsEarned = 0;

        beat.setSensitivity(400);
        song.play();
        t.start();
    }

    public void draw() {
        background(240,240,240);
        drawFFT();
        noStroke();

        beat.detect(song.mix);

        if (beat.isOnset()) System.out.println("hi");
        if (beat.isRange(0, 1, 1) ) {
//            circles.add(new GameCircle(rand.nextInt(1920), rand.nextInt(1080), this));
            circles.add(new GameCircle(xpos, ypos, this));
            xpos+=100;
            if (xpos > 1900) {
                xpos = 0;
                ypos+=100;
            }
            if (ypos > 900) {
                xpos = 0;
                ypos = 100;
            }
        }



        for (GameCircle c: circles) {
            c.checkIsOver();
            c.draw();
//            System.out.print(c.getRadius() + " ");
        }
//        System.out.println();

        // Keep list trimmed of useless, clicked on circles
        if (circles.size() > 20) circles.remove(0);

    }

    // Draws the background, a linear average of all FFT bands at a given second.
    private void drawFFT() {
        stroke(205, 183, 158);
        strokeWeight(1);
        fft.forward(song.mix);
        fft.linAverages(1); // Take a singular linear average of the entire FFT spectrum
        if (frameCount%2 == 0) bands.add(fft.getBand(0));
        for (int i = 0; i < bands.size(); i++) {
            line(i*3, 540 + (bands.get(i)*5), i*3, 540 - (bands.get(i)*5));
            // Clear and go back to left-hand side if about to overflow
            if (bands.size() == 640) bands.clear();
        }
    }

    // Quit game with error message if invalid filepath to music
    private void checkNullSong() {
        if (song == null) {
            textSize(64);
            fill(40, 50, 200);
            text("Invalid filepath, quitting in 3 seconds...", 500, 500);
            try {TimeUnit.SECONDS.sleep(3); } catch (InterruptedException ex) {System.exit(0);}
            System.exit(0); }
    }

}
