package com.Game.player;

import main.GamePanel;
import main.KeyHandler;
import com.Game.player.Imageutils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler kanye;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler kanye) {
        this.gp = gp;
        this.kanye = kanye;

        screenX = gp.maxSreenWidth/2 - (gp.tileSize - 2);
        screenY = gp.maxScreenHeight/2 - (gp.tileSize - 2);

        setDefaultValues();
        playerGetImage();
    }

    public void setDefaultValues() {
        this.worldX = gp.tileSize * 35;
        this.worldY = gp.tileSize * 35;

        speed = 4;
        direction = "down";
    }

    public void playerGetImage() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up_1 = Imageutils.removeBackground(up1);
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            up_2 = Imageutils.removeBackground(up2);

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down_1 = Imageutils.removeBackground(down1);
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            down_2 = Imageutils.removeBackground(down2);

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left_1 = Imageutils.removeBackground(left1);
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            left_2 = Imageutils.removeBackground(left2);

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right_1 = Imageutils.removeBackground(right1);
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            right_2 = Imageutils.removeBackground(right2);


        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {

        if (kanye.upPressed == true || kanye.downPressed == true ||
                kanye.leftPressed == true || kanye.rightPressed == true) {

            if (kanye.upPressed == true) {
                direction = "up";
                worldY -= speed;
            }
            if (kanye.downPressed == true) {
                direction = "down";
                worldY += speed;
            }
            if (kanye.leftPressed == true) {
                direction = "left";
                worldX -= speed;
            }
            if (kanye.rightPressed == true) {
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            gp.repaint();

        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up": image = (spriteNum == 1) ? up_1 : up_2; break;
            case "down": image = (spriteNum == 1) ? down_1 : down_2; break;
            case "left": image = (spriteNum == 1) ? left_1 : left_2; break;
            case "right": image = (spriteNum == 1) ? right_1 : right_2; break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
