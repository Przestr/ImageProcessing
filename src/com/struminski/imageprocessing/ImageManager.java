package com.struminski.imageprocessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    private BufferedImage image;
    private BufferedImage imageCopy;

    public ImageManager() {
    }

    public boolean loadImage(String file) {
        try {
            File input = new File(file);
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

    public void makeCopy() {
        imageCopy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics g = imageCopy.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    public void restoreCopy() {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                image.setRGB(j, i, imageCopy.getRGB(j, i));
            }
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
