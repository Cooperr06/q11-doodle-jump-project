package manager;

import util.Platform;
import util.Position;
import util.Skin;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PlatformManager
{
    public static PlatformManager instance = new PlatformManager();
    private final List<Platform> platforms = new LinkedList<>();
    private final Skin platformSkin = Skin.of(1);

    private int rows;
    private int columns;

    private PlatformManager()
    {
        rows = Renderer.getInstance().getRows();
        columns = Renderer.getInstance().getColumns();
    }

    public static PlatformManager getInstance()
    {
        return instance;
    }

    public void iterateLoop()
    {
        Platform firstPlatform = platforms.get(0);
        // If there is a platform which is outside the playable area it gets deleted
        if (firstPlatform.getPosition().getY() < 0)
        {
            platforms.remove(firstPlatform);
            platforms.add(firstPlatform);
        }
        firstPlatform.getPosition().setY(rows + 2);
    }

    public void spawnInitialPlatforms(int amount)
    {
        Random random = new Random();
        int distance = rows - 1 / amount;
        int currentY = distance;
        for (int i = 0; i < amount; i++)
        {
            platforms.add(new Platform(platformSkin, new Position(random.nextInt(columns), currentY)));
            currentY += distance;
        }
    }
}
