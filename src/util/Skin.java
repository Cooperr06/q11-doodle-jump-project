package util;

import java.io.File;

public final class Skin
{
    private final File[] images;

    public Skin(File[] images)
    {
        this.images = images;
    }

    public static Skin ofId(int id)
    {
        File skinDirectory = new File("./src/resources/skins/" + id);
        return new Skin(skinDirectory.listFiles());
    }

    public File[] getImages()
    {
        return images;
    }
}
