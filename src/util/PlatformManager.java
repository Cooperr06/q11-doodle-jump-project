package util;
import java.util.Random;
public class PlatformManager
{
    // Draft of a class using the singleton pattern
    private List platforms;
    private Random rnGenerator;
    private static PlatformManager pManager = new PlatformManager();

    private PlatformManager()
    {
        platforms = new List();
        rnGenerator = new Random();
    }
    public static PlatformManager returnpManager()
    {
        return pManager;
    }
    public Platform generatePlatform()
    {
        // Asking the List to return the Platform with the highest y value
        Platform pComparison = platforms.giveLastPlatform();
        // gaining a random number for x and y
        int y = rnGenerator.nextInt(20);
        int x = rnGenerator.nextInt(80);
        Platform pnew;
        // if not then the game just began -> creating a platform a bit above the avatar
        if(pComparison == null)
             {
                 pnew = new Platform(skin, new Position(x,y));
             }
        else
        // creating a platform above the last
            {
                Position position = pComparison. getPosition();
                int yOld = position.getY();
                pnew = new Platform(skin, new Position (x,yOld + y);
            }
        return pnew;
    }
}