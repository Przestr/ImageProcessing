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
        int threads = 4;
        int division = image.getHeight() / threads;
        int end = division;
        ArrayList<Thread> threadList = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threads -1; i++) {
            Thread t = new Thread(new Context(new BinarizationAlgorithm(), image, start, end, image.getWidth()));
            t.start();
            threadList.add(t);
            start += division;
            end += division;
        }
        Thread t = new Thread(new Context(new BinarizationAlgorithm(), image, start, image.getHeight(), image.getWidth()));
        t.start();
        threadList.add(t);

        try {
            for (Thread thread : threadList) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("Execution time: " + duration + " miliseconds");

        imageManager.saveImage();
    }
}
