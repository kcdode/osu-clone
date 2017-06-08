
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import shapes.MenuButton;

public class StartMenu extends PApplet {

    private MenuButton play;
    private MenuButton load;
    private AudioPlayer audio;
    private RGBPicker rgb;
    private int[] colors;
    private String filepath;
    private boolean updateString; // toggles when pressing 'load' button


    public static void main(String[] args) {
        PApplet.main("StartMenu", args);
    }

    public void settings() {
        size(1920, 1080);
    }

    public void setup() {
        surface.setTitle("OSU! 2: Electric Boogaloo");
        background(255, 255, 255);
        colorMode(RGB, 255, 255, 255);
        noStroke();

        rgb = new RGBPicker();
        colors = new int[3];
        filepath = "Song Path";

        // Initialize menu buttons
        play = new MenuButton("PLAY", 835, 700, this);
        load = new MenuButton("LOAD", 835, 800, this);

        // Menu song (Sweet Dreams by Eurythmics)
        audio = new Minim(this).loadFile("music/sweetdreams.mp3");
        audio.play();
        audio.loop();
    }

    public void draw() {
        colors = rgb.nextColor();
        int v = 1; // For alternating the title text
        int hoverChangePlay = 0; // Change button color if hovered over
        int hoverChangeLoad = 0;
        background(255, 255, 255);

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
        fill(colors[0],  colors[1], colors[2]);
        textSize(64);
        text("OSU! 2: Electric Boogaloo", 1920/2, 350 + (v - 1)*20);


        // Draw Buttons
        fill(0 , 191 + hoverChangePlay, 255);
        play.draw();
        fill(0 , 191 + hoverChangeLoad, 255);
        load.draw();
        // Write filepath text (different color if updateString == true)
        if (updateString) fill(0, 0, 0);
        else fill(128, 128, 128);
        textSize(24);
        textAlign(CENTER);
        text(filepath, 1920/2, 925);

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