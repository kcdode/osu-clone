
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import shapes.MenuButton;

public class StartMenu extends PApplet {

    private MenuButton play;
    private MenuButton load;
    private AudioPlayer audio;
    private String filepath;
    private boolean updateString; // toggles with pressing load


    public static void main(String[] args) {
        PApplet.main("StartMenu", args);
    }

    public void settings() {
        size(1920, 1080);
    }

    public void draw() {
        int v = 1; // For alternating the title text
        int hoverChangePlay = 0; // Change button color if hovered over
        int hoverChangeLoad = 0;
        background(255, 255, 240);

        if (play.isOver()) {
            hoverChangePlay = 64;
        }
        if (load.isOver()) {
            hoverChangeLoad = 64;
        }


        // Shifts text to tempo of Sweet Dreams (approximately)
        if (frameCount % 60 < 15) v = 2;
        else if (frameCount % 60 < 30) v = 1;
        else if (frameCount % 60 < 45) v = 2;
        else if (frameCount % 60 < 60) v = 1;

        // Fill numbers are completely random; just for making lots of colors
        fill(frameCount % 29 * 15,  frameCount % 6 * 40, frameCount % 20 * 35);
        textSize(64);
        text("OSU! 2: Electric Boogaloo", 575, 350 + (v - 1)*20);


        // Draw Buttons
        fill(0 , 191 + hoverChangePlay, 255);
        play.draw();
        fill(0 , 191 + hoverChangeLoad, 255);
        load.draw();
        // Write filepath text (different color if updateString == true)
        if (updateString) fill(0, 0, 0);
        else fill(128, 128, 128);
        textSize(24);
        text(filepath, 810, 925);

    }

    public void mouseClicked() {
        if (play.isOver()) {
            Game.main(new String[]{filepath});
            audio.close();
            surface.setVisible(false);
        } else if (load.isOver()) {
            updateString = !updateString;
        }

    }

    public void setup() {
        filepath = "Song Path";
        colorMode(RGB, 255, 255, 255);
        noStroke();
        background(255, 255, 255);

        // Initialize menu buttons
        surface.setTitle("OSU! 2: Electric Boogaloo");
        play = new MenuButton("PLAY", 810, 700, this);
        load = new MenuButton("LOAD", 810, 800, this);

        // Menu song (Sweet Dreams by Eurythmics)
        audio = new Minim(this).loadFile("music/sweetdreams.mp3");
        audio.play();
        audio.loop();
    }

    public void keyPressed() {
        if (!updateString) return;
        if (filepath.equals("Song Path")) {
            filepath = String.valueOf(key);
            return;
        }
        if (keyCode == BACKSPACE) {
            if (filepath.length() == 0) return;
            filepath = filepath.substring(0, filepath.length()-1);
        } else if (keyCode == ENTER) {
            updateString = false;
        } else {
            filepath += key;
        }
    }

}