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

        DataElement firstPlatform = platforms.getFirst().getDataElement();
        // If there is a platform which is outside the playable area it gets deleted
        if (firstPlatform.getPosition().getY() < 0)
        {
            platforms.removeFirst();
            platforms.insertLast(firstPlatform);
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
            Platform platform = new Platform(platformSkin, new Position(random.nextInt(columns), currentY));
            platforms.insertLast(platform);
            currentY += distance;
        }
    }
}
