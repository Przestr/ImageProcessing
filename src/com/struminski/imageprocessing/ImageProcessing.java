package com.struminski.imageprocessing;

import java.util.ArrayList;
import java.util.Scanner;

public class ImageProcessing {
    private ImageManager imageManager;
    View view;

    public ImageProcessing(View view) {

        imageManager = new ImageManager();
        this.view = view;
    }

    public void start() {
        if (!imageManager.loadImage()) {
            return;
        }
        Scanner scanner = new Scanner(System.in);
        AlgorithmStrategy algorithmStrategy;
        do {
            view.showMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1: {
                    algorithmStrategy = new BinarizationAlgorithm();
                    execute(algorithmStrategy);
                    break;
                }
                case 9: {
                    imageManager.saveImage();
                    return;
                }
                default:
                    break;
            }
        } while (true);
    }

    public void execute(AlgorithmStrategy algorithmStrategy) {
        int start = 0;
        int threads = 4;
        int division = imageManager.getImage().getHeight() / threads;
        int end = division;
        ArrayList<Thread> threadList = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threads - 1; i++) {
            Thread t = new Thread(new Context(algorithmStrategy, imageManager.getImage(), start, end, imageManager.getImage().getWidth()));
            t.start();
            threadList.add(t);
            start += division;
            end += division;
        }
        Thread t = new Thread(new Context(algorithmStrategy, imageManager.getImage(), start, imageManager.getImage().getHeight(), imageManager.getImage().getWidth()));
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
    }
}
