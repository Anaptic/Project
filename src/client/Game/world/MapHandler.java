package client.Game.world;

import client.Game.AssetManager;
import client.Util.MessageLog;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Jack B.
 */
public class MapHandler {
    public static ArrayList<Chunk> MapChunks = new ArrayList<>();
    public static ArrayList<Image> tiles = new ArrayList<>();
    public static int[][] impassable;
    
    protected static final int chunkSize = 64;
    
    public static void loadTiles() {
        if(tiles.isEmpty()) {
            MessageLog.print("Loading map tiles", MessageLog.PRIORITY_LOW);
            SpriteSheet tileset;
            
            try {
                tileset = new SpriteSheet(AssetManager.path+"\\map\\tiles.png", 64, 64);
                
                for(int y = 0; y < tileset.getVerticalCount(); y++) {
                    for(int x = 0; x < tileset.getHorizontalCount(); x++) {
                        tiles.add(tileset.getSubImage(x, y));
                    }
                }
                
                MessageLog.print("Loaded ("+tiles.size()+") map tiles", MessageLog.PRIORITY_LOW);
            } catch(SlickException e) {
                System.err.println(e);
            }
            
        } else {
            MessageLog.print("Attempt to double load map tiles.", MessageLog.PRIORITY_MED);
        }
    }
    
    public static void renderChunks(Graphics g) {
        for(Chunk c : MapChunks) {
            if(c.needsRender()) {
                c.render(g);
            }
        }
    }
    
    public static void updateChunks() {
        for(Chunk c : MapChunks) {
            if(c.needsRender()) {
                c.update();
            }
        }
    }
    
    public static void createChunk(int[][] data, int x, int y) {
        if(data.length != 64) {
            MessageLog.print("Packet to create map chunk failed", MessageLog.PRIORITY_HIGH);
        } else {
            MapChunks.add(new Chunk(x, y, data));
            MessageLog.print("Created new map chunk.", MessageLog.PRIORITY_LOW);
        }
    }
    
    public static Chunk[] getRenderedChunks() {
        ArrayList<Chunk> chunks = new ArrayList<>();
        
        for(Chunk c : MapChunks) {
            if(c.needsRender()) {
                chunks.add(c);
            }
        }
        
        return (Chunk[]) chunks.toArray();
    }
    
    public static Chunk getChunkForCoord(float x, float y) {
        for(Chunk c : MapChunks) {
           Rectangle r = new Rectangle(c.absX-1, c.absY-1, c.absX + (64*64), c.absY + (64*64)); 
           if(r.contains(x, y)) {
               return c;
           } 
        }
        
        return null;
    }
    
    public static boolean isPassable(int i) {
        switch(i) {
            case 10:
                return false;
            default:
                return true;
        }           
    }
    
}
