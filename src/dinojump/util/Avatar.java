package dinojump.util;

import dinojump.DinoJump;
import dinojump.Renderer;
import dinojump.manager.PlatformManager;
import dinojump.manager.ScoreManager;
import dinojump.manager.SkinManager;

import static java.lang.Math.*;

public class Avatar implements Movable
{
    private static Avatar instance;

    private final Skin skin;

    private Position position;

    private int xVelocity;
    private int yVelocity;

    private int maxXVelocity;
    private int maxFallingVelocity;
    private int maxJumpingVelocity;

    private int xAcceleration;
    private int yAcceleration = 1;

    private Avatar()
    {
        reset();
        skin = SkinManager.getInstance().selectAvatarSkin();

        maxXVelocity = 60;
        maxFallingVelocity = 70;
        maxJumpingVelocity = 40;
    }

    public static Avatar getInstance()
    {
        if (instance == null)
        {
            instance = new Avatar();
        }
        return instance;
    }

    public void reset()
    {
        position = new Position(Renderer.getInstance().getScreenWidth() / 2 - Renderer.getInstance().getAvatarDimensions() / 2,
                Renderer.getInstance().getScreenHeight() / 2);
        yAcceleration = 1;
    }

    @Override
    public void moveTo(int x, int y)
    {
        position.setX(x);
        position.setY(y);
    }

    public void iterateLoop()
    {
        updateVelocity();
        updatePosition();
        checkForGameOver();
        redraw();
    }

    public void updateVelocity()
    {
        // xVelocity
        if (xAcceleration == 0) // deceleration
        {
            if (abs(xVelocity) <= ceil((double) abs(maxXVelocity) / 10))
            {
                xVelocity = 0;
            }
            else
            {
                if (xVelocity < 0)
                {
                    xVelocity = (int) ceil((double) xVelocity / 1.1);
                }
                else
                {
                    xVelocity = (int) floor((double) xVelocity / 1.1);
                }
            }
        }
        else // acceleration
        {
            xVelocity += xAcceleration;

            if (xVelocity > maxXVelocity)
            {
                xVelocity = maxXVelocity;
            }
            else if (xVelocity < maxXVelocity * -1)
            {
                xVelocity = maxXVelocity * -1;
            }
        }

        // yVelocity
        /*
        since there is no accelerating process in jumping, it goes maxVel - slowly -> Vel = 0 - slowly -> -1 * maxVel
        until collisionManager "accelerates" again, witch is considered as a starting signal to jump, therefor repeats the cycle
         */
        if (yAcceleration > 0) // getting accelerated by collisionManager
        {
            yVelocity = maxJumpingVelocity * -1;
            yAcceleration = 0;
        }
        else if (yVelocity < 0) // travelling upwards
        {
            yVelocity = (int) ceil((double) yVelocity / 1.05);
        }
        else if (yVelocity == 0) // stalling
        {
            yVelocity = 1;
        }
        else // travelling downwards
        {
            yVelocity = (int) ceil((double) yVelocity * 1.2);
            int maxFallingVelocity = (int) floor((double) this.maxFallingVelocity / 6);
            if (yVelocity > maxFallingVelocity)
            {
                yVelocity = maxFallingVelocity;
            }
        }
    }

    public void updatePosition()
    {
        if (DinoJump.getInstance().isRunning() &&
                ((xVelocity > 0 && getPosition().getX() < Renderer.getInstance().getScreenWidth() - Renderer.getInstance().getAvatarDimensions() ||
                        (xVelocity < 0 && getPosition().getX() > Renderer.getInstance().getAvatarDimensions()))))
        {
            position.setX(position.getX() + xVelocity / 10);
        }
    }

    public void checkForGameOver()
    {
        // if the current y of the avatar is greater than the y of the lowest platform, the avatar cannot move upwards anymore --> Game Over
        if (getPosition().getY() > PlatformManager.getInstance().getPlatforms().getFirst()
                .getDataElement().getPosition().getY() + 800 && yVelocity > 0)
        {
            ScoreManager.getInstance().updateScore();
            Stage.getInstance().showGameOverScreen();
            DinoJump.getInstance().getTimer().cancel();
            DinoJump.getInstance().setRunning(false);
            PlatformManager.getInstance().getPlatforms().clear();
        }
    }

    public void redraw()
    {
        Renderer.getInstance().renderAvatar(skin, position);
    }

    public Skin getSkin()
    {
        return skin;
    }

    @Override
    public Position getPosition()
    {
        return position;
    }

    @Override
    public void setPosition(Position position)
    {
        this.position = position;
    }

    public int getXVelocity()
    {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity)
    {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity()
    {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity)
    {
        this.yVelocity = yVelocity;
    }

    public int getMaxXVelocity()
    {
        return maxXVelocity;
    }

    public void setMaxXVelocity(int maxXVelocity)
    {
        this.maxXVelocity = maxXVelocity;
    }

    public int getMaxFallingVelocity()
    {
        return maxFallingVelocity;
    }

    public void setMaxFallingVelocity(int maxFallingVelocity)
    {
        this.maxFallingVelocity = maxFallingVelocity;
    }

    public int getXAcceleration()
    {
        return xAcceleration;
    }

    public void setXAcceleration(int xAcceleration)
    {
        this.xAcceleration = xAcceleration;
    }

    public int getYAcceleration()
    {
        return yAcceleration;
    }

    public void setYAcceleration(int yAcceleration)
    {
        this.yAcceleration = yAcceleration;
    }

    public int getMaxJumpingVelocity()
    {
        return maxJumpingVelocity;
    }

    public void setMaxJumpingVelocity(int maxJumpingVelocity)
    {
        this.maxJumpingVelocity = maxJumpingVelocity;
    }
}
