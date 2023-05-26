package util;

import java.io.File;

public final class Skin
{
    private final File[] images;

    public Skin(File[] images)
    {
        this.images = images;
    }

    public File[] getImages()
    {
        return images;
    }
}
