package com.struminski.imageprocessing;

public class Main {

    public static void main(String[] args) {
        View view = new View();
        ImageProcessing imageprocessing = new ImageProcessing(view);
        imageprocessing.start();
        view.setVisible(true);
    }
}
