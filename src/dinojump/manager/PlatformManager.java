package dinojump.manager;

import dinojump.Renderer;
import dinojump.util.Platform;
import dinojump.util.Position;
import dinojump.util.Skin;
import list.DataElement;
import list.List;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PlatformManager
{
    public static PlatformManager instance;

    private final List platforms = new List();
    private final Skin platformSkin = Skin.of(1);
    private final Random random = new Random();

    private final int rows;
    private final int columns;

    private PlatformManager()
    {
        rows = Renderer.getInstance().getRows();
        columns = Renderer.getInstance().getColumns();
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
            if (platform.getPosition().getY() > Renderer.getInstance().getHeight() + 100)
            {
                platforms.remove(platform);
                platform.setPosition(new Position((int) (random.nextGaussian() * 5 + columns / 2), ThreadLocalRandom.current().nextInt(0, 3) * (Renderer.getInstance().getScreenHeight() / Renderer.getInstance().getRows())));
                platforms.insertSorted(platform);

                ScoreManager.getInstance().addScore(1);
                ScoreManager.getInstance().renderScore();
            }
        }
        draw();
    }

    public void spawnInitialPlatforms(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            int x = (int) (random.nextGaussian() * 3 + columns / 2);
            int y = (rows - ((int) (((float) i / (float) (amount - 1) * rows))) * Renderer.getInstance().getScreenHeight() / rows) + 3;
            if (i == amount - 1)
            {
                x = columns / 2;
                y = (int) Math.round(rows * 0.75);
            }
            platforms.insertSorted(new Platform(platformSkin, new Position(x, y)));
        }
    }

    public void draw()
    {
        Renderer.getInstance().renderPlatforms(platforms);
    }

    public List getPlatforms()
    {
        return platforms;
    }
}
