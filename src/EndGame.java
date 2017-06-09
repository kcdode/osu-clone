import processing.core.PApplet;

public class EndGame extends PApplet {

    public static void main(String[] args) {
        PApplet.main("EndGame", args);
    }

    public void settings() {
        size(1920, 1080);
    }

    public void setup() {

    }

    public void draw() {
        String percentage = String.valueOf(((double) Integer.parseInt(args[1]) / (double) Integer.parseInt(args[0]))*100);
        if (percentage.length() > 5) percentage = percentage.substring(0, 5); // Hacky truncate to 2 decimals
        background(255, 255, 255);
        fill(0, 0, 0);
        textSize(32);
        textAlign(CENTER);
        text("Thanks for Playing!", 1920 / 2, 200);
        text("Total circles: " + Integer.parseInt(args[0])/100, 1920 / 2, 300);
        text("You hit: " + Integer.parseInt(args[1])/100, 1920 / 2, 400);
        text("You Earned: " + args[2] + " points", 1920 / 2, 500);
        text("Your max multiplier was " + args[3], 1920 / 2, 700);
        text("Your completion percentage was: " + percentage + "%",1920 / 2, 600);

    }
}
