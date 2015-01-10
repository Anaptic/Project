package client.Game;

import client.Game.Characters.CharacterManager;
import client.Game.world.Chunk;
import client.Game.world.MapHandler;
import client.Game.world.MapLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Jack B
 */
public class Main extends BasicGame {

    public static JFrame loginWindow;
    
    public static float cameraX = 0f;
    public static float cameraY = 0f;
    
    public Main(String title) {
        super(title);
    }

    public Chunk chunk;
    public static client.Game.Characters.Character c;
    
    @Override
    public void init(GameContainer gc) throws SlickException {
        MapHandler.loadTiles();
        AssetManager.loadImages();
        SpriteSheet charSheet = AssetManager.getCharSheet();
        MapLoader.loadMap();
        
        c = new client.Game.Characters.Character("Jack", 100, 0, 5, charSheet);
        c.setPos(32, 32);
        CharacterManager.chars.add(c);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        c.update();
        MapHandler.updateChunks();
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        InputHandler.processInput(gc.getInput(), g);
        MapHandler.renderChunks(g);
        c.render(g);
    }
    
}
