package dinojump.manager;

import dinojump.Renderer;
import dinojump.util.Audio;
import dinojump.util.Avatar;
import dinojump.util.Position;
import list.List;

public class CollisionManager
{
    private static CollisionManager instance;

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

    public void checkForCollision()
    {
        Position avtPos = Avatar.getInstance().getPosition();
        List platforms = PlatformManager.getInstance().getPlatforms();
        Avatar avatar = Avatar.getInstance();
        if (avatar.getYVelocity() > 0)
        {
            for (int i = 0; i < platforms.size(); i++)
            {
                if (avatar.getPosition().getX() + Renderer.getInstance().getAvatarDimensions() > platforms.get(i).getPixelXPosition() && avatar.getPosition().getX() < platforms.get(i).getPixelXPosition() + Renderer.getInstance().getPlatformWidth())
                {
                    if (avatar.getPosition().getY() + Renderer.getInstance().getAvatarDimensions() < platforms.get(i).getPosition().getY() + Renderer.getInstance().getPlatformHeight() && avatar.getPosition().getY() + Renderer.getInstance().getAvatarDimensions() > platforms.get(i).getPosition().getY())
                    {
                        Avatar.getInstance().setYVelocity(-Avatar.getInstance().getMaxFallingVelocity());
                        Audio.getInstance().playSound("jump");
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
