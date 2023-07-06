package dinojump.util;

import dinojump.Renderer;

import static java.lang.Math.*;

public class Avatar implements Movable
{
    private static Avatar instance;

    private final Skin skin;

    private Position position;
    private int score;

    private int xVelocity;
    private int yVelocity;

    private int maxXVelocity;
    private int maxYVelocity;

    private int xAcceleration;
    private int yAcceleration = 1;

    private Avatar()
    {
        position = new Position(Renderer.getInstance().getScreenWidth() / 2 - Renderer.getInstance().getAvatarDimensions() / 2, Renderer.getInstance().getScreenHeight() / 2);
        skin = Skin.of(0);

        maxXVelocity = 40;
        maxYVelocity = 40;
    }

    public static Avatar getInstance()
    {
        if (instance == null)
        {
            instance = new Avatar();
        }
        return instance;
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
            if (abs(xVelocity) <= ceil((double) abs(maxXVelocity) / 7))
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
        since there is no accelerating process in jumping, it goes maxVel -slowly-> Vel = 0 -slowly-> -1 * maxVel
        until collisionManager "accelerates" again, witch is considered as a starting signal to jump, therefor repeats the cycle
         */
        if (yAcceleration > 0) // got accelerated by collisionManager
        {
            yVelocity = maxYVelocity * -1;
            yAcceleration = 0;
        }
        else if (yVelocity < 0) //traveling upwards
        {
            yVelocity = (int) ceil((double) yVelocity / 1.05);
        }
        else if (yVelocity == 0) // stalling
        {
            yVelocity = 1;
        }
        else // traveling downwards
        {
            yVelocity = (int) ceil((double) yVelocity * 1.2);
            int maxFallingVelocity = (int) floor((double) maxYVelocity / 6);
            if (yVelocity > maxFallingVelocity)
            {
                yVelocity = maxFallingVelocity;
            }
        }
    }

    public void updatePosition()
    {
        position.setX(position.getX() + xVelocity / 10);
    }

    public void redraw()
    {
        Renderer.getInstance().renderAvatar(skin, position);
    }

    public void checkForGameOver()
    {
        if (instance.getPosition().getY() > Renderer.getInstance().getHeight() - 100)
        {
            Stage.getInstance().showGameOverScreen();
            //DinoJump.getInstance().setRunning(false);
        }
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

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
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

    public int getMaxYVelocity()
    {
        return maxYVelocity;
    }

    public void setMaxYVelocity(int maxYVelocity)
    {
        this.maxYVelocity = maxYVelocity;
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
}
