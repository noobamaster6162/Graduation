package com.Game.player;

import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, //Main FIle
    up_1, up_2, left_1, left_2, right_1, right_2, down_1, down_2; //Removed Background Images

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

}