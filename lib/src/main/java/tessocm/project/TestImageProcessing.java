package tessocm.project;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestImageProcessing {
    public static void main(String[] args) throws IOException {
        BufferedImage b1 = null;
        try {
           b1 = ImageIO.read(new File("coolpic.jfif"));
        } catch(Exception e) {
            System.err.println(e);
        }

        int width = b1.getWidth();
        int height = b1.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int p = b1.getRGB(j, i);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // subtract RGB from 255
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                // set new RGB value
                p = (a << 24) | (r << 16) | (g << 8) | b;
                b1.setRGB(j,i,p);
            }
        }
        try {
            ImageIO.write(b1,"png",new File("output.png"));
        }catch (Exception e) {
            System.err.println(e);
        }
    }
}
