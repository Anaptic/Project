package client.Util;

/**
 *
 * @author Jack B.
 */
public class MessageLog {
    public static int PRIORITY_HIGH = 3;
    public static int PRIORITY_MED = 2;
    public static int PRIORITY_LOW = 1;
    
    protected static int level = 3;
    
    public static void setLevel(int i) {
        if(i <= 3 && i > 0) {
            level = i;
        } else {
            System.err.println("Error with Logger: Failed to set level");
        }
    }
    
    public static void print(String s, int priority) {
        if(priority >= level) {
            switch(level) {
                case 1:
                    System.out.println("[Low Priority] " + s);
                break;
                case 2:
                    System.out.println("[Mid Priority] " + s);
                break;
                case 3:
                    System.err.println("[IMPORTANT] " + s);
                break;
            }
        }
    }
    
}
