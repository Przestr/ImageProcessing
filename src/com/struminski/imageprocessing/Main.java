package com.struminski.imageprocessing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        BufferedImage image;
        ImageManager imageManager = new ImageManager();
        imageManager.loadImage();
        image = imageManager.getImage();

        int start = 0;
        int division = image.getHeight() / 4;
        int end = division;
        ArrayList<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(new Context(new BinarizationAlgorithm(), image, start, end, image.getWidth()));
            t.start();
            threadList.add(t);
            start = division;
            end += division;
        }
        try {
            for (Thread thread : threadList) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }


        imageManager.saveImage();
    }
}
