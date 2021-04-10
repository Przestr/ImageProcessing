package com.struminski.imageprocessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    private BufferedImage image;

    public ImageManager() {
    }

    public boolean loadImage() {
        try {
            File input = new File("obraz.bmp");
            this.image = ImageIO.read(input);
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public void saveImage() {
        try {
            File output = new File("output.png");
            ImageIO.write(image, "jpg", output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
