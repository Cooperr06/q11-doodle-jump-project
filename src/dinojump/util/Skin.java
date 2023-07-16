package dinojump.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Skin
{
    private final int id;
    private final BufferedImage image;

    public Skin(int id, BufferedImage image)
    {
        this.id = id;
        this.image = image;
    }

    public static Skin of(int id)
    {
        File[] skinDirectoryFiles = new File("./resources/skins/" + id).listFiles();
        if (skinDirectoryFiles == null || skinDirectoryFiles.length < 1)
        {
            throw new RuntimeException("There is no skin file in the directory with the id %d".formatted(id));
        }
        try
        {
            return new Skin(id, ImageIO.read(skinDirectoryFiles[0]));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public int getId()
    {
        return id;
    }

    public BufferedImage getImage()
    {
        return image;
    }
}
