package com.Game.player;

import java.awt.image.BufferedImage;

public class Imageutils {

    public static BufferedImage removeBackground(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x <img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int red   = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue  = rgb & 0xff;

                // Treat as white if all components are close to 255
                if (red > 240 && green > 240 && blue > 240) {
                    result.setRGB(x, y, 0x00000000); // fully transparent
                } else {
                    result.setRGB(x, y, rgb);
                }
            }
        }
        return result;
    }
}
