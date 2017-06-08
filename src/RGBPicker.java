import java.util.Random;

class RGBPicker {

    int[] rgb;
    private Random rd;
    private int distanceTo;
    private int baseColor;
    private int color;

    RGBPicker() {
        rgb = new int[3];
        rd = new Random();
        for (int i = 0; i < rgb.length; i++) {
            rgb[i] = rd.nextInt(255);
        }
    }

    int[] nextColor() {
        if (distanceTo < 5) newColor();
        switch (baseColor) {
            case 1:
                if (rgb[0] > color) rgb[0]-=4;
                else rgb[0] +=4;
                break;
            case 2:
                if (rgb[1] > color) rgb[1]-=4;
                else rgb[1] +=4;
                break;
            case 3:
                if (rgb[2] > color) rgb[2]-=4;
                else rgb[2] +=4;
                break;
        }
        distanceTo-=4;
        return rgb;
    }

    private void newColor() {
        baseColor = rd.nextInt(3) + 1;
        System.out.println(baseColor);
        color = rd.nextInt(255);
        System.out.println(color);
        switch (baseColor) {
            case 1:
                distanceTo = Math.abs(rgb[0] - color);
                break;
            case 2:
                distanceTo = Math.abs(rgb[1] - color);
                break;
            case 3:
                distanceTo = Math.abs(rgb[2] - color);
        }
    }
}
