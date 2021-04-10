package com.struminski.imageprocessing;

import java.util.ArrayList;

public class ImageProcessing {
    private ImageManager imageManager;

    public ImageProcessing() {
        imageManager = new ImageManager();
    }

    public void execute() {
        if (!imageManager.loadImage()) {
            return;
        }
        int start = 0;
        int threads = 4;
        int division = imageManager.getImage().getHeight() / threads;
        int end = division;
        ArrayList<Thread> threadList = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threads - 1; i++) {
            Thread t = new Thread(new Context(new BinarizationAlgorithm(), imageManager.getImage(), start, end, imageManager.getImage().getWidth()));
            t.start();
            threadList.add(t);
            start += division;
            end += division;
        }
        Thread t = new Thread(new Context(new BinarizationAlgorithm(), imageManager.getImage(), start, imageManager.getImage().getHeight(), imageManager.getImage().getWidth()));
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
        System.out.println("Execution time: " + duration + " milliseconds");

        imageManager.saveImage();
    }
}
