package client.Game.Characters;

import java.util.ArrayList;

/**
 *
 * @author Jack B.
 */
public class CharacterManager {
    public static ArrayList<Character> chars = new ArrayList<>();
    
    public static Character getCharForName(String name) {
        for(Character c: chars) {
            if(c.Name.equals(name)) {
                return c;
            }
        }
        
        return null;
    }
}
