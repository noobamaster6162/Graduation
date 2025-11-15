package main;

import javax.swing.*;

public class  Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Escape The Backrooms");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack();

        gamePanel.startGame();
        gamePanel.requestFocusInWindow();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
