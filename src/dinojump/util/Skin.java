package dinojump.util;

import java.io.File;
import java.util.Random;

public final class Skin
{
    private static final int[] avatarSkins = new int[]{0, 1};

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
        return Skin.of(avatarSkins[new Random().nextInt(avatarSkins.length)]);
    }

}
