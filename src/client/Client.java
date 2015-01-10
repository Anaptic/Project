package client;

import client.Game.Main;
import client.Util.MessageLog;
import java.io.IOException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Jack B.
 */
public class Client {
    
    public static int[] dimention = {760, 500};
    public static AppGameContainer app;
    
    public static boolean GRID = false;
    
    public static void main(String[] args) throws IOException, SlickException {
        app = new AppGameContainer(new Main("Project Budgie"));
        app.setDisplayMode(dimention[0], dimention[1], false);
        app.setVSync(true);
        
        MessageLog.setLevel(MessageLog.PRIORITY_LOW);
        app.start();
        
    }
    
}
