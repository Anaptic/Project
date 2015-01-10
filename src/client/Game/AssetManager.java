package client.Game;

import java.util.Hashtable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Jack
 */
public class AssetManager {
    public static Hashtable images = new Hashtable();
    public static final String path = System.getProperty("user.dir") + "\\Data";
    public static SpriteSheet charsheet;
    
    public static void loadImages() throws SlickException {
        charsheet = new SpriteSheet(getCharPath() + "citizenmale.png", 64, 64);
    }
    
    public static String getMusicPath() {
        return path + "\\music\\";
    }
    
    public static String getMapPath() {
        return path + "\\map\\";
    }
    
    public static String getCharPath() {
        return path + "\\characters\\";
    }
    
    public static SpriteSheet getCharSheet() {
        return charsheet;
    }
    
}
