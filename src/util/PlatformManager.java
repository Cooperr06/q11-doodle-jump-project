package util;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
public class PlatformManager
{
    /** Draft of a class using the singleton pattern*/
    private List<Platform> platforms;
    private Random rnGenerator;
    private int windowX;
    private int windowY;

    public static PlatformManager platformManager = new PlatformManager();


    private PlatformManager()
    {
        platforms = new LinkedList<Platform>();
        rnGenerator = new Random();
        windowX = 720;
        windowY = 1280;
    }
    public static PlatformManager returnplatformManager()
    {
        return platformManager;
    }




    public Platform generatePlatform()
    {
        Platform pnew;
        if(platforms.get(0).getPosition().getY()<720)
        {
            platforms.remove(0);
            pnew = platforms.get(0);
        }
        /** Asking the List to return the Platform with the highest y value */

            Platform pComparison = platforms.get(platforms.size());
            /** gaining a random number for x and y */
            int y = rnGenerator.nextInt(100)+20;
            int x = rnGenerator.nextInt(720);
            /** if not then the game just began -> creating a platform a bit above the avatar */
            if(pComparison == null)
            {
                pnew = new Platform(skin, new Position(360,1200));
            }
            else
            /** creating a platform above the last */
            {
                Position position = pComparison. getPosition();
                int yOld = position.getY();
                pnew = new Platform(skin, new Position (x,yOld + y);

            }
            return pnew;

    }
}
