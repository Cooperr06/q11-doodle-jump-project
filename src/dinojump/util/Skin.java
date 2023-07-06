package dinojump.util;

import java.io.File;
import java.util.Random;

public final class Skin
{
    private static final int[] AVATAR_SKINS = new int[]{0,2,3,4};

    private final int id;
    private final File[] images;

    public Skin(int id, File[] images)
    {
        this.id = id;
        this.images = images;
    }

    public static Skin of(int id)
    {
        File skinDirectory = new File("./resources/skins/" + id);
        return new Skin(id, skinDirectory.listFiles());
    }

    public File[] getImages()
    {
        return images;
    }

    public int getId()
    {
        return id;
    }

    public static Skin selectAvatarSkin()
    {
        return Skin.of(AVATAR_SKINS[new Random().nextInt(AVATAR_SKINS.length)]);
    }

}
