package com.struminski.imageprocessing;

import java.awt.image.BufferedImage;

public class MirrorHorizontalAlgorithm implements AlgorithmStrategy {

    @Override
    public void calculate(BufferedImage image, int start, int height, int width) {
        int first, second;
        for (int i = start; i < height; i++) {
            for (int j = 0; j < width / 2; j++) {
                first = image.getRGB(j, i);
                second = image.getRGB(width - j - 1, i);
                image.setRGB(j, i, second);
                image.setRGB(width - j - 1, i, first);
            }
        }
    }
}
