package client.Game.world;

import client.Game.Main;
import java.util.Arrays;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

/**
 *
 * @author Jack B.
 */
public class Node {
    public final Vector2f[] points = new Vector2f[4];
    public Polygon area = new Polygon();
    public final Vector2f centre = new Vector2f();
    public boolean isPassable = true;
    public Vector2f[] originalPoints;
    
    public Node(Vector2f p1, Vector2f p2, Vector2f p3, Vector2f p4) { 
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        
        originalPoints = points;
        
        area.addPoint((int) p1.x,  (int) p1.y);
        area.addPoint((int) p2.x,  (int) p2.y);
        area.addPoint((int) p3.x,  (int) p3.y);
        area.addPoint((int) p4.x,  (int) p4.y);
        
        centre.x = area.getCenterX();
        centre.y = area.getCenterY();
    } 
    
    public static Node stoodOn(Chunk chunk, client.Game.Characters.Character c) {
        for(Node[] node : chunk.nodes) {
            for(Node n : node) {
                if(n.area.contains(c.getAbsPos().x-Main.cameraX, c.getAbsPos().y-Main.cameraY)) {
                    return n;
                }
            }
        }  
        
        return c.getNode();
    }
    
    public void draw(Graphics g) {
        g.draw(this.area);
    }
    
    public void update() {
        area = new Polygon();
        
        for(int i = 0; i < 4; i++) {
            area.addPoint(originalPoints[i].x-Main.cameraX, originalPoints[i].y-Main.cameraY);
        }
    }
}