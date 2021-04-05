package com.struminski.imageprocessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        BufferedImage image = null;
        int height;
        int width;

        try{
            File input = new File("image.png");
            image = ImageIO.read(input);

        }
        catch (IOException e) {
            System.out.println(e);
        }

        if(image!=null) {
            width = image.getWidth();
            height = image.getHeight();

            Context context = new Context(new BinarizationAlgorithm());
            context.executeStrategy(image, height, width);
        }

        try{
            File output = new File("output.png");
            ImageIO.write(image, "jpg", output);

        }
        catch (IOException e) {
            System.out.println(e);
        }

    }
}
