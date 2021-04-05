package com.struminski.imageprocessing;

import java.awt.image.BufferedImage;

public interface AlgorithmStrategy {
    void calculate(BufferedImage image, int height, int width);
}
