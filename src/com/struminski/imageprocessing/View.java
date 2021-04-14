package com.struminski.imageprocessing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class View extends JFrame {

    private JPanel p = new JPanel();
    private JPanel q = new JPanel();
    private JSplitPane splitPane = new JSplitPane();
    private JButton loadButton = new JButton("Load image");
    private JButton binarizationButton = new JButton("Binarization");
    private JButton horizontalMirrorButton = new JButton("Horizontal mirror effect");
    private JButton grayScaleButton = new JButton("Gray scale");
    private JButton exitButton = new JButton("Save and exit");

    public View() {
    }

    public void showMenu(BufferedImage image) {
        p.removeAll();
        q.removeAll();
        q.setLayout(new BoxLayout(q, BoxLayout.Y_AXIS));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
        this.getContentPane().setLayout(new GridBagLayout());

        if (image != null) {

            p.add(new JLabel((new ImageIcon(image))));
            q.add(binarizationButton);
            q.add(Box.createVerticalStrut(10));
            q.add(horizontalMirrorButton);
            q.add(Box.createVerticalStrut(10));
            q.add(grayScaleButton);
            q.add(Box.createVerticalStrut(10));
            q.add(exitButton);
            q.add(Box.createVerticalStrut(10));
            q.add(loadButton);

            getContentPane().add(splitPane);
            splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
            //splitPane.setDividerLocation(500);
            splitPane.setLeftComponent(p);
            splitPane.setRightComponent(q);
            this.pack();
        } else {
            q.add(loadButton);
            this.add(q);
        }
        this.setLocationRelativeTo(null);
    }

    void addLoadButton(ActionListener listenForLoadButton) {
        loadButton.addActionListener(listenForLoadButton);
    }

    void addBinarizationListener(ActionListener listenForBinarizationButton) {
        binarizationButton.addActionListener(listenForBinarizationButton);
    }

    void addHorizontalMirrorListener(ActionListener listenForHorizontalMirrorButton) {
        horizontalMirrorButton.addActionListener(listenForHorizontalMirrorButton);
    }

    void addGrayScaleListener(ActionListener listenForGrayScaleButton) {
        grayScaleButton.addActionListener(listenForGrayScaleButton);
    }

    void addExitListener(ActionListener listenForExitButton) {
        exitButton.addActionListener(listenForExitButton);
    }
}
