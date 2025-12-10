package main;

import com.Game.player.Entity;
import com.Game.player.Player;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity en) {

        int entityWorldLeftX   = en.worldX + en.playerArea.x;
        int entityWorldRightX  = en.worldX + en.playerArea.x + en.playerArea.width;
        int entityWorldTopY    = en.worldY + en.playerArea.y;
        int entityWorldBottomY = en.worldY + en.playerArea.y + en.playerArea.height;

        int entityLeftCol   = entityWorldLeftX  / gp.tileSize;
        int entityRightCol  = entityWorldRightX / gp.tileSize;
        int entityTopRow    = entityWorldTopY   / gp.tileSize;
        int entityBottomRow = entityWorldBottomY/ gp.tileSize;

        int tileNum1, tileNum2;

        switch (en.direction) {   // use instance direction, not Entity.direction
            case "up":
                entityTopRow = (entityWorldTopY - en.speed) / gp.tileSize;
                break;
            case "down":
                entityBottomRow = (entityWorldBottomY + en.speed) / gp.tileSize;
                break;
            case "left":
                entityLeftCol = (entityWorldLeftX - en.speed) / gp.tileSize;
                break;
            case "right":
                entityRightCol = (entityWorldRightX + en.speed) / gp.tileSize;
                break;
        }

        // clamp indices to valid range to avoid -1 / overflow
        entityLeftCol   = Math.max(0, Math.min(entityLeftCol,   gp.maxWorldCol - 1));
        entityRightCol  = Math.max(0, Math.min(entityRightCol,  gp.maxWorldCol - 1));
        entityTopRow    = Math.max(0, Math.min(entityTopRow,    gp.maxWorldRow - 1));
        entityBottomRow = Math.max(0, Math.min(entityBottomRow, gp.maxWorldRow - 1));

        switch (en.direction) {
            case "up":
                tileNum1 = gp.tileC.tileMapNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileC.tileMapNum[entityTopRow][entityRightCol];
                break;
            case "down":
                tileNum1 = gp.tileC.tileMapNum[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileC.tileMapNum[entityBottomRow][entityRightCol];
                break;
            case "left":
                tileNum1 = gp.tileC.tileMapNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileC.tileMapNum[entityBottomRow][entityLeftCol];
                break;
            case "right":
                tileNum1 = gp.tileC.tileMapNum[entityTopRow][entityRightCol];
                tileNum2 = gp.tileC.tileMapNum[entityBottomRow][entityRightCol];
                break;
            default:
                return;
        }

        if (gp.tileC.tile[tileNum1].Collision || gp.tileC.tile[tileNum2].Collision) {
            en.collisionOn = true;
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] == null) continue;

            if (gp.obj[i] != null) {
                //PLAYER area
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // OBJECT area
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            index = i;
                        }
                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            index = i;
                        }
                        break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            index = i;
                        }
                        break;

                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            index = i;
                        }
                        break;
                }
                // reset AFTER each object
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
