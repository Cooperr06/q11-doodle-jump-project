package util;
// upper class Platform gets created with two variables which gain values from the constructor
public abstract class Platform
{
    private Skin skin;
    private Position position;
    Platform(Skin newSkin, Position newPosition)
    {
        skin = newSkin;
        position = newPosition;
    }
}

public class MovingPlatform extends Platform
{
    // does the same things the upper class does
    private Skin skin;
    private Position position;
    MovingPlatform(Skin newSkin, Position newPosition)
    {
        super();
        this.Movement();
    }
    public void Movement()
    {
        // creating an infinite loop
        while (skin != null)
        {
            // moving it to the right and then to the left
            for (int i = 0, i < 50,int i= i+1);
            {
            position.setX (position.getX()+1);
            Thread.sleep(200);
            }
            for ( i = 0, i < 50, i= i+1);
           {
            position.setX (position.getX()-1);
            Thread.sleep(200);
           }
    }
}

public class SimplePlatform extends Platform
{
    // does the same things the upper class does
    private Skin skin;
    private Position position;
    SimplePlatform(Skin newSkin, Position newPosition)
    {
        super();
    }
}
}

public class BreakingPlatform extends Platform
{
    // does the same things the upper class does
    private Skin skin;
    private Position position;
    BreakingPlatform(Skin newSkin, Position newPosition)
    {
        super();
    }
}
