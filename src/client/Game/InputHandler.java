package client.Game;

import client.Game.Characters.CharacterManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import static org.newdawn.slick.Input.*;

/**
 *
 * @author Jack B.
 */
public class InputHandler {
    public static void processInput(Input input, Graphics g) {
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            CharacterManager.chars.get(0).addToWalkingQeue(input.getMouseX()+Main.cameraX, input.getMouseY()+Main.cameraY);
        }
        
        if(input.isKeyDown(KEY_LEFT)) { 
            Main.cameraX-=2;
        }if(input.isKeyDown(KEY_RIGHT)) { 
            Main.cameraX+=2;
        }if(input.isKeyDown(KEY_UP)) { 
            Main.cameraY-=2;
        }if(input.isKeyDown(KEY_DOWN)) { 
            Main.cameraY+=2;
        }
    }
}
