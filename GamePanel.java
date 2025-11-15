package main;

import TIle.TileCode;
import com.Game.player.Player;

import javax.swing.*;
import java.awt.*;

//GAME-MECHANICS

public class GamePanel extends JPanel implements Runnable{

    // SETTINGS //
    final int originalTileSize = 16; //16x16//
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int maxSreenWidth = tileSize * maxScreenCol;
    public final int maxScreenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 75;
    public final int maxWorldRow = 75;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    public TileCode tileC = new TileCode(this);
    KeyHandler kanye = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, kanye);

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(maxSreenWidth, maxScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kanye);
        this.setFocusable(true);
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.01666666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            long timer = 0;

            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);

                drawCount = 0;
                FPS = 0;
            }
        }
    }

    public void update() {

        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileC.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
