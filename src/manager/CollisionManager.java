package manager;

import util.Avatar;
import util.Platform;
import util.Position;

import java.util.List;

public class CollisionManager
{
    private static CollisionManager instance;
    private List<Platform> platforms;

    private CollisionManager()
    {

    }

    public static CollisionManager getInstance()
    {
        if (instance == null)
        {
            instance = new CollisionManager();
        }
            return instance;
    }

    public void setPlatforms(List<Platform> newPlatforms)
    {
        platforms = newPlatforms;
    }

    public void checkForCollision()
    {
        Position avtPos = Avatar.getInstance().getPosition();
        List<Position> platformPos = new ArrayList<Position>();
        Position avtPos = Avatar.getInstance().getPosition();

        if (Avatar.getInstance().getY < 0)
        {
            for (int i = 0; i < platformPos.size(); i++)
            {
                //First Variable is the width of the Platform, the second Variable is the width of the player
                if (avtPos.getX() + Renderer.getInstance().getPlatformWidth() < platformPos.get(i).getX() && avtPos.getX() + Renderer.getInstance().getAvatarDimensions() > platformPos.get(i).getX())
                {
                    //Variable is the hight of the Platform
                    if (avtPos.getY() > platformPos.get(i).getY() && avtPos.getY() + 5 < platformPos.get(i).getY())
                    {
                        Avatar.getInstance().setYAcceleration(10);
                    }
                }

            }
        }

    }
}



