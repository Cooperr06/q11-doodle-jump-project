package src.util;

import java.util.List;

public class PlatformManager
{
    private List<Platform> platforms;

    public PlatformManager()
    {
        platforms = new ArrayList<>();
    }

    //adds a Platform object to the list of platforms
    public void addPlatform(Platform platform)
    {
        platforms.add(platform);
    }

    //removes a specified Platform object from the list
    public void removePlatform(Platform platform)
    {
        platforms.remove(platform);
    }

    // removes first Platform
    public Platform removeFistPlatform()
    {
        platforms.remove(platforms.getFirstPlatform());
    }

    // Gets the first Platform in the List
    public Platform getFirstPlatform()
    {
        if (!platforms.isEmpty())
        {
            return platforms.get(0);
        }
        else
        {
            return null;
        }
    }


    public List<Platform> getPlatforms()
    {
        return platforms;
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
        if (pComparison == null)
        {
            pnew = new Platform(skin, new Position(x, y));
        }
        else
        // creating a platform above the last
        {
            Position position = pComparison.getPosition();
            int yOld = position.getY();
            pnew = new Platform(skin, new Position(x, yOld + y);
        }
        return pnew;
    }
}
