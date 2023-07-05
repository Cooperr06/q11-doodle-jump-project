package dinojump.manager;

import dinojump.Renderer;
import dinojump.util.Platform;
import dinojump.util.Position;
import dinojump.util.Skin;
import list.DataElement;
import list.List;

import java.util.Random;

public class PlatformManager
{
    public static PlatformManager instance;

    private final List platforms = new List();
    private final Skin platformSkin = Skin.of(1);

    private final int rows;
    private final int columns;

    private Random random;

    private PlatformManager()
    {
        rows = Renderer.getInstance().getRows();
        columns = Renderer.getInstance().getColumns();
        random = new Random();
    }

    public static PlatformManager getInstance()
    {
        if (instance == null)
        {
            instance = new PlatformManager();
        }
        return instance;
    }

    public void iterateLoop()
    {
        platforms.forEach(DataElement::iterateLoop);
        // platforms that are below the window get reused on top
        for (int i = 0; i < platforms.size(); i++)
        {
            DataElement platform = platforms.get(i);
            if (platform.getPosition().getY() > Renderer.getInstance().getHeight() - 100)
            {
                platforms.remove(platform);
                platforms.insertLast(platform);
                platform.setPosition(new Position((int) (random.nextGaussian() * 3 + columns / 2), random.nextInt(10) - 5));
                i--;

            }
            else
            {
                break;
            }
        }
        draw();
    }

    public void spawnInitialPlatforms(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            int x = (int) (random.nextGaussian() * 3 + columns / 2);
            int y = (rows - ((int) (((float) i / (float) (amount - 1) * rows))) * Renderer.getInstance().getScreenHeight() / rows);
            platforms.insertLast(new Platform(platformSkin, new Position(x, y)));
        }
    }

    public void draw()
    {
        Renderer.getInstance().renderPlatforms(platforms);
    }
}
