package com.struminski.imageprocessing;

import java.awt.image.BufferedImage;

public class Context {
    private AlgorithmStrategy strategy;
    public Context(AlgorithmStrategy strategy){
        this.strategy = strategy;
    }
    public void executeStrategy(BufferedImage image, int height, int width){
        strategy.calculate(image, height, width);
    }
}
