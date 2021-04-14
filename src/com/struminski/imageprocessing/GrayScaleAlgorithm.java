package com.struminski.imageprocessing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayScaleAlgorithm implements AlgorithmStrategy {

    @Override
    public void calculate(BufferedImage image, int start, int height, int width) {
        int r, g, b, p;
        for (int i = start; i < height; i++) {
            for (int j = 0; j < width; j++) {
                p = image.getRGB(j, i);
                r = (int) (((p >> 16) & 0xff) * 0.299f);
                g = (int) (((p >> 8) & 0xff) * 0.587f);
                b = (int) ((p & 0xff) * 0.144f);
                p = r + g + b;
                if (p > 255)
                    p = 255;
                Color col;
                col = new Color(p, p, p);
                image.setRGB(j, i, col.getRGB());
            }
        }
    }
}
