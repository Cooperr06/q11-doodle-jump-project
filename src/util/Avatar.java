package util;

import static java.lang.Math.*;

public class Avatar implements Movable
{
    private final Skin skin;

    private final Renderer renderer;

    private Position position;
    private int score;

    private int xVelocity;
    private int yVelocity;

    private int maxXVelocity;
    private int maxYVelocity;

    private int xAcceleration;
    private int yAcceleration;

    public Avatar(Skin skin, Position position)
    {
        this.skin = skin;
        this.position = position;

        maxXVelocity = 10;
        maxYVelocity = 10;

        renderer = Renderer.getInstance();
    }

    @Override
    public void moveTo(int x, int y)
    {
        position.setX(x);
        position.setY(y);
    }

    public void updateVelocity()
    {
        //update xVelocity

        //xVelocity
        if (xAcceleration == 0)       //deceleration
        {
            if (abs(xVelocity) < floor((double) abs(maxXVelocity) / 4))
            {
                xVelocity = 0;
            }
            else
            {
                xVelocity = (int) floor((double) xVelocity / 2);
            }
        }
        else                      //acceleration
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

        //yVelocity

        /*
        since there is no accelerating process in jumping, it goes maxVel -slowly-> Vel = 0 -slowly-> -1 * maxVel
        until collisionManager "accelerates" again, witch is considered as a starting signal to jump, therefor repeats the cycle
         */
        if (yAcceleration > 0)       //got accelerated by collisionManager
        {
            yVelocity = maxYVelocity;
            yAcceleration = 0;
        }
        else if (yVelocity > 0)       //traveling upwards
        {
            yVelocity = (int) floor((double) yVelocity / 1.2);
        }
        else if (yVelocity == 0)        //stalling
        {
            yVelocity = -1;
        }
        else     //traveling downwards
        {
            yVelocity = (int) ceil((double) yVelocity * 1.2);
            if (yVelocity < maxYVelocity * -1)
            {
                yVelocity = maxYVelocity * -1;
            }
        }
    }


    public void updatePosition()
    {
        moveTo(position.getX() + xVelocity, position.getY() + yVelocity);
    }

    public void iterateLoop()
    {
        updateVelocity();
        updatePosition();
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
