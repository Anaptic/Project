package client.Game.world;

import client.Game.AssetManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jack B.
 */
public class MapLoader {

    private final static int BUFFER = 2048;

    public static void loadMap() {
        File mapDir = new File(AssetManager.getMapPath() + "\\chunks");
        for(File f : mapDir.listFiles()) {
            try {
                parseFile(f);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MapLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MapLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
    }
    
    public static void parseFile(File f) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line = null;
        int[][] tiles = new int[64][64];
        int indX = 0;
        int indY = 0;
        
        int[] coords = new int[2];
        
        while((line = br.readLine()) != null) {
            if(line.contains("=")) {
                String[] vars = line.trim().split(",");
                for(int i = 0; i < vars.length; i++) {
                    coords[i] = Integer.valueOf(vars[0].trim().split("=")[1].trim());
                }
            } else {
                for(String i : line.split(",")) {
                    tiles[indY][indX] = Integer.valueOf(i);
                    indX++;
                }
                indY++;
                indX=0;
            }
        }
        
        MapHandler.createChunk(tiles, coords[0], coords[1]);
        
    }
}
