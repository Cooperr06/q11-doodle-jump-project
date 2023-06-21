package manager;

import util.Avatar;
import util.Platform;
import util.Position;

import java.util.List;

public class CollisionManager
{
    private static CollisionManager instance;
    private Avatar avatar;
    private List<Platform> platforms;

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

    public void setPlatforms(List<Platform> newPlatforms)
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
        List<Position> platformPos = new List<Position>();
        int avtVel = avatar.getYVelocity();

        if (avtVel < 0)
        {
            for (int i = 0; i < platformPos.size(); i++)
            {
                //First Variable is the width of the Platform, the second Variable is the width of the player
                if (avtPos.getX() + 50 < platformPos.get(i).getX() && avtPos.getX() + 12 > platformPos.get(i).getX())
                {
                    //Variable is the hight of the Platform
                    if (avtPos.getY() > platformPos.get(i).getY() && avtPos.getY() + 5 < platformPos.get(i).getY()))
                    {
                        DinoJump.get
                    }
                }

            }
        }

    }
}



