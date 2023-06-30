package dinojump.manager;

import dinojump.Renderer;
import dinojump.util.Platform;
import dinojump.util.Position;
import dinojump.util.Skin;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PlatformManager
{
    public static PlatformManager instance;

    private final List<Platform> platforms = new LinkedList<>();
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
        for (Platform platform : platforms)
        {
            platform.iterateLoop();
        }

        // platforms that are below the window get reused on top
        itteration:
        {
            for (int i = 0; i < platforms.size(); i++)
            {

                if (platforms.get(i).getPosition().getY() > Renderer.getInstance().getHeight() - 100)
                {
                    Platform speicher = platforms.get(i);
                    platforms.remove(platforms.get(i));
                    platforms.add(speicher);
                    speicher.setPosition(new Position((int) (random.nextGaussian() * 3 + columns / 2), random.nextInt(10) - 5));
                    i--;
                }
                else
                {

                    break itteration;
                }


            }
        }
        draw();
    }

    public void spawnInitialPlatforms(int amount)
    {

        for (int i = 0; i < amount; i++)
        {
            platforms.add(new Platform(platformSkin, new Position((int) (random.nextGaussian() * 3 + columns / 2), (rows - ((int) (((float) i / (float) (amount - 1) * rows))) * Renderer.getInstance().getScreenHeight() / rows))));
        }
    }

    public void draw()
    {
        Renderer.getInstance().renderPlatforms(platforms);
    }
}
