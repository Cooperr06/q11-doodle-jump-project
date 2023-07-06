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
        int avtVel = avatar.getInstance().getYVelocity();
        if (avtVel > 0)
        {
            for (int i = 0; i < platforms.size(); i++)
            {

                //System.out.println(avatar.getPosition().getX() + Renderer.getInstance().getAvatarDimensions() > platforms.get(i).getPixelPositionX());
                //System.out.println(avatar.getPosition().getX() < platforms.get(i).getPixelPositionX() + Renderer.getInstance().getPlatformWidth());
                //System.out.println("avatar position: " + avatar.getPosition().getX() + " platform position: " + platforms.get(i).getPosition().getX() + " Platformwidth: " + Renderer.getInstance().getPlatformWidth() + " Platform right position :" + (int)(platforms.get(i).getPosition().getX() + Renderer.getInstance().getPlatformWidth()));
                if (avatar.getPosition().getX() + Renderer.getInstance().getAvatarDimensions() > platforms.get(i).getPixelPositionX() && avatar.getPosition().getX() < platforms.get(i).getPixelPositionX() + Renderer.getInstance().getPlatformWidth())
                {
                    //System.out.println("x collision");
                    //System.out.println("avatar.botom position :" + (int)(avatar.getPosition().getY()+ Renderer.getInstance().getAvatarDimensions()));
                    //System.out.println("pixel position :" + platforms.get(i).getPixelPositionY());
                    //if(avatar.getPosition().getY() + Renderer.getInstance().getAvatarDimensions()< platforms.get(i).getPixelPositionY() + Renderer.getInstance().getPlatformHeight() ){System.out.println("condition 1 ---------------------");}
                    //if(avatar.getPosition().getY() + Renderer.getInstance().getAvatarDimensions() > platforms.get(i).getPixelPositionY()){System.out.println("condition 2 ====================================D");}
                    if (avatar.getPosition().getY() + Renderer.getInstance().getAvatarDimensions() < platforms.get(i).getPixelPositionY() + Renderer.getInstance().getPlatformHeight() && avatar.getPosition().getY() + Renderer.getInstance().getAvatarDimensions() > platforms.get(i).getPixelPositionY())
                    {
                        Avatar.getInstance().setYVelocity(-Avatar.getInstance().getMaxYVelocity());
                        System.out.println(" ::::::::::::::::-----collision------:::::::::::::::");
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



