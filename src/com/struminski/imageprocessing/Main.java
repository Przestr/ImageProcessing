package com.struminski.imageprocessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        int height;
        int width;

        try{
            File input = new File("image.png");
            BufferedImage image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

        }
        catch (Exception e) {
            System.out.println("Cannot open the file");
        }


	// write your code here
    }
}
