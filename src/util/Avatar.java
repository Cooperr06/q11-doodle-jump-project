package util;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class Avatar implements Movable
{
    private final Skin skin;

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
        if (xAcceleration == 0)       //Deacceleration
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
        else                      //Acceleration
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
