package client.Game.world;

import static client.Client.GRID;
import client.Game.Main;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Jack B.
 */
public class Chunk {
    protected final int chunkX;
    protected final int chunkY;
    
    protected final float absX;
    protected final float absY;
    
    protected float relX;
    protected float relY;
    
    protected final int[][] tiles;
    public Node[][] nodes;
    
    protected Graphics floor;
    
    public Chunk(int x, int y, int[][] tiles) {
        this.chunkX = x;
        this.chunkY = y;
        
        this.absX = x * MapHandler.chunkSize;
        this.absY = y * MapHandler.chunkSize;
        
        this.tiles = tiles;
        this.nodes = new Node[tiles.length][tiles.length];
        
        createNodes();
    }
    
    public void render(Graphics g) {
        int xPos = 0, yPos = 0;
        for(int[] i: tiles) {
            for(int x : i) {
                if(xPos*64 + relX >= client.Client.dimention[0] || yPos*18 + relY >= client.Client.dimention[1]) {
                    break;
                }
                if((yPos % 2) == 1) {
                    g.drawImage(MapHandler.tiles.get(x),((xPos*64)+32 + relX), (float) ((yPos*(17)) + relY));
                } else {
                    g.drawImage(MapHandler.tiles.get(x),(xPos*64 + relX), (yPos*(17) + relY));
                }
                xPos++;
            }
            xPos=0;
            yPos++;
        }
        
        if(GRID) { 
            for(Node[] n : nodes) {
                for(Node node : n) {
                    g.draw(node.area);
                }
            }
        }
    }
    
    public void update() {
        this.relX = this.absX - Main.cameraX;
        this.relY = this.absY - Main.cameraY;
    }
    
    public boolean needsRender() {
        return true;
    }
    
    public boolean needsExist() {
        return relX >= -6500 && relY >= -6500 || relX <= 6500 && relY <= 6500;
    }
    
    public final void createNodes() {
       int xPos = 0, yPos = 0;
        for(int i = 0; i < tiles.length; i++) {
            for(int x = 0; x < tiles[i].length; x++) {
                Vector2f pos1 = new Vector2f();
                Vector2f pos2 = new Vector2f();
                Vector2f pos3 = new Vector2f();
                Vector2f pos4 = new Vector2f();
                
                if((yPos % 2) == 1) {
                    pos1.x = (xPos*64)+32;
                    pos1.y = yPos*(17) + (64-16);
                    
                    pos2.x = pos1.x+(64/2);
                    pos2.y = pos1.y-16;
                    
                    pos3.x = pos1.x+64;
                    pos3.y = pos1.y;
                    
                    pos4.x = pos2.x;
                    pos4.y = pos2.y+32;
                } else {
                    pos1.x = (xPos*64);
                    pos1.y = yPos*(17) + (64-16);
                    
                    pos2.x = pos1.x+(64/2);
                    pos2.y = pos1.y-16;
                    
                    pos3.x = pos1.x+64;
                    pos3.y = pos1.y;
                    
                    pos4.x = pos2.x;
                    pos4.y = pos2.y+32;
                }
                
                nodes[i][x] = new Node(pos1, pos2, pos3, pos4);
                xPos++;
            }
            xPos=0;
            yPos++;
        }
    }
    
}
