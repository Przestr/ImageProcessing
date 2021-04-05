package com.struminski.imageprocessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    private BufferedImage image;

    public ImageManager() {
    }

    public void loadImage() {
        try {
            File input = new File("image.png");
            this.image = ImageIO.read(input);
        } catch (IOException e) {
            System.out.println(e);
        }
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
