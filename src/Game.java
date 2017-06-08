
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import shapes.GameCircle;
import shapes.PointCounter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game extends PApplet {

    private AudioPlayer             song;
    private ArrayList<GameCircle>   circles;
    private ArrayList<Float>        bands;
    private BeatDetect              beat;
    private FFT                     fft;
    private Thread                  t;
    private PointCounter            counter;
    private Motion                  motion;
    private RGBPicker               rgbPicker;


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
        counter     = new PointCounter(this);
        motion      = new Motion(this);
        rgbPicker   = new RGBPicker();
        song        = new Minim(this).loadFile(args[0]);
        checkNullSong();
        beat        = new BeatDetect(song.bufferSize(), song.sampleRate());
        fft         = new FFT(song.bufferSize(), song.sampleRate());
        t           = new Thread(this::drawFFT);

        beat.setSensitivity(300);
        song.play();
        t.start();
        circles.add(new GameCircle(1920 / 2, 1080 / 2, rgbPicker.nextColor(), this, counter));
    }

    public void draw() {
        if (!song.isPlaying()) {
            endGame();
        }
        background(240,240,240);
        counter.drawText();
        drawFFT();
        noStroke();

        beat.detect(song.mix);

        if (beat.isHat() ) {
            circles.add(new GameCircle(motion.nextCircle(100), rgbPicker.nextColor(), this, counter));
        } if (beat.isSnare() ) {
            circles.add(new GameCircle(motion.nextCircle(150), rgbPicker.nextColor(), this, counter));
        } if (beat.isKick() ) {
            circles.add(new GameCircle(motion.nextCircle(200), rgbPicker.nextColor(), this, counter));
        }
        int[] f = rgbPicker.nextColor();
        motion.drawLine(f[0], f[1], f[2]);
        noStroke();

        for (GameCircle c: circles) {
            c.checkIsOver();
            c.draw();
//            System.out.print(c.getRadius() + " ");
        }
//        System.out.println();

        // Keep list trimmed of old on circles
        if (circles.size() > 40) circles.remove(0);

    }

    private void endGame() {
        String[] paremeters = {String.valueOf(counter.possiblePoints), String.valueOf(counter.pointsEarned)};
        EndGame.main(paremeters);
        song.close();
        surface.setVisible(false);
        dispose();
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

    public void keyPressed() {
        if (keyCode == TAB) {
            endGame();
        }
    }



}


