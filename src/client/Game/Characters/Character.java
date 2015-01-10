package client.Game.Characters;

import client.Client;
import client.Game.Main;
import client.Game.world.Chunk;
import client.Game.world.MapHandler;
import client.Game.world.Node;
import client.Util.MessageLog;
import static java.lang.Math.abs;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Jack B
 */
public class Character {

    protected final Image STAND_NORTH;
    protected final Image STAND_SOUTH;
    protected final Image STAND_EAST;
    protected final Image STAND_WEST;

    protected final Animation walkNorth;
    protected final Animation walkSouth;
    protected final Animation walkEast;
    protected final Animation walkWest;

    protected final int startX;
    protected final int startY;

    protected float absX;
    protected float absY;

    protected float relX;
    protected float relY;

    protected float[][] moveCoords = new float[100][2];
    protected int moveDir;

    protected float speed = 2.0f;

    protected Node stoodOn;
    protected Node moveTo;

    protected Chunk currentChunk;

    protected float lastX = 0;
    protected float lastY = 0;
    
    protected int HP;
    protected String Name;

    public Character(String name, int hp, int x, int y, SpriteSheet sheet) {
        Name = name;
        HP = hp;
        
        STAND_NORTH = sheet.getSubImage(x, y);
        STAND_SOUTH = sheet.getSubImage(x, y + 1);
        STAND_EAST = sheet.getSubImage(x, y + 3).getFlippedCopy(true, false);
        STAND_WEST = sheet.getSubImage(x, y + 3);

        startX = (x);
        startY = (y);
        Image[][] anim = new Image[4][6];

        for (int i = 0; i <= 5; i++) {
            anim[0][i] = sheet.getSubImage(x + i, y);
            anim[1][i] = sheet.getSubImage(x + i, y + 1);
            anim[2][i] = sheet.getSubImage(x + i, y + 3).getFlippedCopy(true, false);
            anim[3][i] = sheet.getSubImage(x + i, y + 3);
        }

        walkNorth = new Animation(anim[0], 100, true);
        walkSouth = new Animation(anim[1], 100, true);
        walkEast = new Animation(anim[2], 100, true);
        walkWest = new Animation(anim[3], 100, true);
    }
    
    public void setHP(int x) {
        this.HP = x;
    }

    public boolean needsRender() {
        return ((this.relX > 0 && this.relX <= Client.dimention[0])
                && (this.relY > 0 && this.relY <= Client.dimention[1]));
    }

    public void render(Graphics g) {

        if (this.stoodOn != null) {
            this.stoodOn.draw(g);
        } else {
            //this.stoodOn = Node.stoodOn(currentChunk, absX, absX);
        }

        if (this.moveCoords[0][0] == 0f && this.moveCoords[0][1] == 0f) {
            switch (moveDir) {
                case 0:
                    g.drawImage(this.STAND_NORTH, this.relX-32, this.relY-50);
                    break;
                case 1:
                    g.drawImage(this.STAND_NORTH, this.relX-32, this.relY-50);
                    break;
                case 2:
                    g.drawImage(this.STAND_SOUTH, this.relX-32, this.relY-50);
                    break;
                case 3:
                    g.drawImage(this.STAND_EAST, this.relX-32, this.relY-50);
                    break;
                case 4:
                    g.drawImage(this.STAND_WEST, this.relX-32, this.relY-50);
                    break;
            }
        } else {
            switch (moveDir) {
                case 1:
                    g.drawAnimation(this.walkNorth, this.relX-32, this.relY-50);
                    break;
                case 2:
                    g.drawAnimation(this.walkSouth, this.relX-32, this.relY-50);
                    break;
                case 3:
                    g.drawAnimation(this.walkEast, this.relX-32, this.relY-50);
                    break;
                case 4:
                    g.drawAnimation(this.walkWest, this.relX-32, this.relY-50);
                    break;
            }
        }
    }

    public void update() {
        //Set relative position on screen based on camera
        this.relX = this.absX - Main.cameraX;
        this.relY = this.absY - Main.cameraY;

        this.currentChunk = MapHandler.getChunkForCoord(absX, absY);
        
        //Update the nodes in our current chunk
        for(Node[] n : this.currentChunk.nodes) {
            for(Node node: n) {
                node.update();
            }
        }
        
        //Find the node the character is stood on
        if(abs(lastX - absX) >= 32 || abs(lastY - absY) >= 3) {
            this.stoodOn = Node.stoodOn(currentChunk, this);
            this.lastX = absX;
            this.lastY = absY;
        }
 
        //Move to the next point in the walking qeue if we have arrived.
        if (this.absX == moveCoords[0][0] && this.absY == moveCoords[0][1]) {
            if (moveCoords[1][0] == 0f) {
                
                   //re add check if in centre
                   moveCoords[0][0] = 0f;
                   moveCoords[0][1] = 0f;
                
            } else {
                moveCoords[0] = moveCoords[1];
            }
        }

        //Calcualte the direction of movement
        if (moveCoords[0][0] != 0f && moveCoords[0][1] != 0f) {
            if (moveCoords[0][0] != this.absX) {
                if (moveCoords[0][0] > this.absX) {
                    this.moveDir = 3;
                } else {
                    this.moveDir = 4;
                }
            } else {
                if (moveCoords[0][1] < this.absY) {
                    this.moveDir = 2;
                } else {
                    this.moveDir = 1;
                }
            }
        } else {
            moveDir = 0;
        }

        Pathfinding.findPath(this, new Vector2f(moveCoords[0][0], moveCoords[0][0]));
    }

    public void addToWalkingQeue(float x, float y) {
        for (float[] pos : moveCoords) {
            if (pos[0] == 0f && pos[1] == 0f) {
                pos[0] = x;
                pos[1] = y;
                MessageLog.print("Added point to walking qeue. ("+pos[0]+", "+pos[1]+")", MessageLog.PRIORITY_LOW);
                return;
            }
        }
    }

    public void setPos(float x, float y) {
        this.absX = (x);
        this.absY = (y);
    }
    
    public Vector2f getAbsPos() {
        return new Vector2f(absX, absY);
    }
    
    public Vector2f getRelPos() {
        return new Vector2f(relX, relY);
    }

    public Node getNode() {
        return this.stoodOn;
    }
    
}
