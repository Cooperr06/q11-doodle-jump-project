package dinojump.manager;

import dinojump.Renderer;
import dinojump.util.Avatar;
import dinojump.util.Position;
import list.List;

public class CollisionManager
{
    private static CollisionManager instance;
    private Avatar avatar;
    private List platforms;

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

    public void checkForCollision()
    {
        Position avtPos = avatar.getInstance().getPosition();
        platforms = PlatformManager.getInstance().getPlatforms();
        int avtVel = avatar.getInstance().getYVelocity();

        if (avtVel > 0)
        {
            for (int i = 0; i < platforms.size(); i++)
            {
                if (avtPos.getX() < platforms.get(i).getPosition().getX() + Renderer.getInstance().getPlatformWidth() && avtPos.getX() - Renderer.getInstance().getAvatarDimensions() > platforms.get(i).getPosition().getX())
                {
                    if (avtPos.getY() > platforms.get(i).getPosition().getY() && avtPos.getY() + Renderer.getInstance().getPlatformHeight() < platforms.get(i).getPosition().getY())
                    {
                        Avatar.getInstance().setYAcceleration(1);
                    }
                }

            }
        }

    }

    public void iterate()
    {
        checkForCollision();
    }
}



