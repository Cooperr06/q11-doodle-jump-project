package dinojump.util;

import java.io.File;

public final class Skin
{
    private final File[] images;
    private final int prize;

    public Skin(File[] images, int prize)
    {
        this.images = images;
        this.prize = prize;
    }

    public static Skin of(int id)
    {
        File skinDirectory = new File("./resources/skins/" + id);
        return new Skin(skinDirectory.listFiles(), 0); // TODO: Get Prize of Database
    }

    public File[] getImages()
    {
        return images;
    }

    public int getPrize()
    {
        return prize;
    }
}
