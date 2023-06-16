package manager;

import util.Avatar;
import util.Platform;
import util.Position;

import java.util.ArrayList;

public class CollisionManager
{
    private static CollisionManager instance;
    private Avatar avatar;
    private ArrayList<Platform> platforms;

    private CollisionManager()
    {

    }

    public static CollisionManager getInstance()
    {
        if (instance == null)
        {
            return new CollisionManager();
        }
        else
        {
            return instance;
        }

    }

    public void setPlatforms(ArrayList<Platform> newPlatforms)
    {
        platforms = newPlatforms;
    }

    public void setAvatar(Avatar newAvatar)
    {
        Avatar = newAvatar;
    }

    public void checkForCollision()
    {
        Position avtPos = avatar.getPosition();
        ArrayList<Position> platformPos = new ArrayList<Position>();
    }


}
