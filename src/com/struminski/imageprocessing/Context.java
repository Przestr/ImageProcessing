package com.struminski.imageprocessing;

import java.awt.image.BufferedImage;

public class Context implements Runnable {
    private AlgorithmStrategy strategy;
    BufferedImage image;
    int start;
    int height;
    int width;

    public Context(AlgorithmStrategy strategy, BufferedImage image, int start, int height, int width){
        this.strategy = strategy;
        this.image = image;
        this.start = start;
        this.height = height;
        this.width = width;
    }

    @Override
    public void run(){
        strategy.calculate(image, start, height, width);
    }
}
