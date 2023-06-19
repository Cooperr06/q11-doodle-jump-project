package util;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;

public class PlatformManager
{
    // Draft of a class using the singleton pattern
    private List<Platform> platforms;
    private Random rnGenerator;
    private int windowX;
    private int windowY;
    public static PlatformManager instance = new PlatformManager();

    private PlatformManager()
    {
        platforms = new LinkedList<Platform>();
        rnGenerator = new Random();
        windowX = Renderer.getInstance().getwindowX();
        windowY = Renderer.getInstance().getwindowY();
    }

    public static PlatformManager getInstance()
    {
        return instance;
    }

    public Platform generatePlatform()
    {
        Platform pnew;
        // If there is a platform which is outside of the playable area it gets deleted
        if (platforms.get(0).getPosition().getY() > windowY) {
            platforms.remove(0);
            pnew = platforms.get(0);
        }
        // Asking the List to return the Platform with the highest y value

            Platform pComparison = platforms.get(platforms.size());
            // gaining a random number for x and y
            int y = rnGenerator.nextInt(100)+20;
            int x = rnGenerator.nextInt(720);
            // if not then the game just began -> creating a platform a bit above the avatar
            if(pComparison == null)
            {
                pnew = new Platform(skin, new Position(360,1200));
            }
            else
            // creating a platform above the last
            {
                Position position = pComparison. getPosition();
                int yOld = position.getY();
                pnew = new Platform(skin, new Position (x,yOld + y);
                platforms.add(pnew);

            }
            return pnew;

    }
}

