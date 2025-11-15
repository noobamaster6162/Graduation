package TIle;


import com.Game.player.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileCode {

    GamePanel gp;
    Tile[] tile;
    int tileMapNum [][];

    public TileCode(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        tileMapNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                if ((row + col) % 13 == 0) tileMapNum[row][col] = 4; // pavement
                else if ((row * col) % 19 == 0) tileMapNum[row][col] = 2; // lake
                else if ((row + col) % 7 == 0) tileMapNum[row][col] = 5; // tree
                else tileMapNum[row][col] = 3; // grass
            }
        }

        getTileImage();
        loadmap();
    }

    public void loadmap() {

        try {

            InputStream is = getClass().getResourceAsStream("/maps/GameMap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (row < gp.maxWorldRow) {
                String line = br.readLine();
                String[] numbers = line.trim().split(" ");

                for (col = 0; col < gp.maxScreenCol; col++) {

                    String cell = numbers[col];
                    String[] parts = cell.split(" ");

                    int num = Integer.parseInt(parts[0]);
                    tileMapNum[row][col] = num;
                }
                row++;
            }
            br.close();

        } catch(Exception e) {

        }

    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Ghaaas.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wall2.png"));
            tile[1].Collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water2.png"));
            tile[2].Collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Pavement.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree1.png"));
            tile[5].Collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree2.png"));
            tile[6].Collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                int tileNum = tileMapNum[row][col];
                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;

                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
