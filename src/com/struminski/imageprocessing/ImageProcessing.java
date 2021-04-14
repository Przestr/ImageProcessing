package com.struminski.imageprocessing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ImageProcessing {

    private ImageManager imageManager;
    View view;

    public ImageProcessing(View view) {
        imageManager = new ImageManager();
        this.view = view;
        view.addBinarizationListener(new BinarizationListener());
        view.addExitListener(new ExitListener());
        view.addHorizontalMirrorListener(new HorizontalMirrorListener());
        view.addGrayScaleListener(new GrayScaleListener());
        view.addLoadButton(new LoadListener());
    }

    public void start() {
        view.showMenu(imageManager.getImage());
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

    class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getFile();
            System.out.println(file + " chosen.");
            imageManager.loadImage(file);
            view.showMenu(imageManager.getImage());
            view.revalidate();
            view.repaint();
        }
    }

    class BinarizationListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            AlgorithmStrategy algorithmStrategy = new BinarizationAlgorithm();
            execute(algorithmStrategy);
            view.revalidate();
            view.repaint();
        }
    }

    class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            imageManager.saveImage();
            view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
        }
    }

    class HorizontalMirrorListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            AlgorithmStrategy algorithmStrategy = new MirrorHorizontalAlgorithm();
            execute(algorithmStrategy);
            view.revalidate();
            view.repaint();
        }
    }

    class GrayScaleListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            AlgorithmStrategy algorithmStrategy = new GrayScaleAlgorithm();
            execute(algorithmStrategy);
            view.revalidate();
            view.repaint();
        }
    }
}
