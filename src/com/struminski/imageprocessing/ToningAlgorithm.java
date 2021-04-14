package com.struminski.imageprocessing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ToningAlgorithm implements AlgorithmStrategy {

    @Override
    public void calculate(BufferedImage image, int start, int height, int width) {
        int r, g, b, p, w;
        w = 30;
        for (int i = start; i < height; i++) {
            for (int j = 0; j < width; j++) {
                p = image.getRGB(j, i);
                r = (int) (((p >> 16) & 0xff) + 2 * w);
                g = (int) (((p >> 8) & 0xff) + w);
                b = (int) ((p & 0xff));
                p = r + g + b;

                if (r > 255)
                    r = 255;
                if (g > 255)
                    g = 255;

                Color col;
                col = new Color(r, g, b);
                image.setRGB(j, i, col.getRGB());
            }
        }
    }
}
