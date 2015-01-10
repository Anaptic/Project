package client.Game.Interfaces;

import org.newdawn.slick.Graphics;

/**
 *
 * @author Jack B
 */
public abstract class Interface {
    protected static boolean isDisplayed;
    
    public static void loadResources() {
        
    }
    
    public static void draw(Graphics g) {
        
    }
    
    public static boolean needsRender() {
        return isDisplayed;
    }
    
    public static void setNeedsRender(boolean need) {
        isDisplayed = need;
    }
    
    public static void input() {
        
    }
}
