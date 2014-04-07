package com.teamplay.managers;

import com.teamplay.data.Level;
import com.teamplay.player.Player;

import java.awt.*;

/**
 * En enkel lite kollisionshanterare
 * Todo: Kolla andra spännande tiles än gränserna
 * Todo: Lägga pixel konvertering i en separat utility klass sen
 */
public class CollisionManager {

    //Todo: Fixa så att denna kan fås på något sätt, kanske läses in ur mapfilen
    private static final int TILE_SIZE = 32;

    private final Level level;

    private Point pointCache = new Point();

    public CollisionManager(Level level) {
        this.level = level;
    }

    public void handlePlayerUpdate(Player player, float elapsedTime) {
        float dx = player.getVelocityX();
        float oldX = player.getXPos();
        float newX = oldX + dx * elapsedTime;
        float dy = player.getVelocityY();
        float oldY = player.getYPos();
        float newY = oldY + dy * elapsedTime;

        Point collidingTile = getTileCollision(player, newX, player.getYPos());
        if (collidingTile == null) {
            player.setxPos(newX, elapsedTime);
        } else {
            // Linea up med gränsen vid kollision
            if (dx > 0) {
                //Todo: fixa bättre player get height
                player.setxPos(tileToPixel(collidingTile.x) - player.getCurrentFrame().getRegionWidth(), elapsedTime);
            } else if (dx < 0) {
                player.setxPos(tileToPixel(collidingTile.x + 1), elapsedTime);
            }
            player.collideHorizontal();
        }

        //Ändring y-led

        collidingTile = getTileCollision(player, player.getXPos(), newY);
        if (collidingTile == null) {
            player.setyPos(newY, elapsedTime);
        } else {
            // Linea upp med gränsen vid kollision
            if (dy > 0) {
                //Todo: fixa bättre player get width
                player.setyPos(tileToPixel(collidingTile.y) - player.getCurrentFrame().getRegionHeight(), elapsedTime);
            } else if (dy < 0) {
                player.setyPos(tileToPixel(collidingTile.y + 1), elapsedTime);
            }
            player.collideVertical();
        }
    }

    public Point getTileCollision(Player sprite, float newX, float newY) {
        float fromX = Math.min(sprite.getXPos(), newX);
        float fromY = Math.min(sprite.getYPos(), newY);
        float toX = Math.max(sprite.getXPos(), newX);
        float toY = Math.max(sprite.getYPos(), newY);

        //Ta fram tile positioner
        int fromTileX = pixelToTile(fromX);
        int fromTileY = pixelToTile(fromY);
        int toTileX = pixelToTile(toX + sprite.getCurrentFrame().getRegionWidth() - 1);
        int toTileY = pixelToTile(toY + sprite.getCurrentFrame().getRegionHeight() - 1);

        // Loopa igenom alla tiles efter en kollision
        for (int x = fromTileX; x <= toTileX; x++) {
            for (int y = fromTileY; y <= toTileY; y++) {

                if (x < 0 || x >= level.getLevelWidth() || y < 0 || y >= level.getLevelHeight()) {

                    //Oups kollition
                    pointCache.setLocation(x, y);
                    return pointCache;
                }
            }
        }

        // Hepp, inga kollisioner
        return null;
    }

    /**
     * Koverterar pixelposition (float) till tile
     */
    public static int pixelToTile(float pixels) {
        return pixelToTile(Math.round(pixels));
    }

    /**
     * Koverterar pixelposition (int) till tile
     */
    public static int pixelToTile(int pixels) {
        return (int)Math.floor((float)pixels / TILE_SIZE);
    }

    /**
     * Konverterar tileposition till pixelposition
     */
    public static int tileToPixel(int numTiles) {
        return numTiles * TILE_SIZE;
    }
}
