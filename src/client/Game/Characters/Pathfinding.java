package client.Game.Characters;

import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Jack B
 */
public class Pathfinding {
    
    public static void findPath(Character c, Vector2f target) {
        switch(c.moveDir) {
            case 1:
                c.absY+=1;
                break;
            case 2:
                c.absY-=1;
                break;
            case 3:
                c.absX+=1;
                break;
            case 4:
                c.absX-=1;
                break;
        }
    }
}
