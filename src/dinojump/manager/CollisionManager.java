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
        avatar = Avatar.getInstance();
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
        if (avatar.getYVelocity() > 0)
        {
            for (int i = 0; i < platforms.size(); i++)
            {
                if (avatar.getPosition().getX() + Renderer.getInstance().getAvatarDimensions() > platforms.get(i).getPixelPositionX() && avatar.getPosition().getX() < platforms.get(i).getPixelPositionX() + Renderer.getInstance().getPlatformWidth())
                {
                    if (avatar.getPosition().getY() + Renderer.getInstance().getAvatarDimensions() < platforms.get(i).getPixelPositionY() + Renderer.getInstance().getPlatformHeight() && avatar.getPosition().getY() + Renderer.getInstance().getAvatarDimensions() > platforms.get(i).getPixelPositionY())
                    {
                        Avatar.getInstance().setYVelocity(-Avatar.getInstance().getMaxYVelocity());
                    }
                }
            }
        }
    }

    public void iterateLoop()
    {
        checkForCollision();
    }
}



