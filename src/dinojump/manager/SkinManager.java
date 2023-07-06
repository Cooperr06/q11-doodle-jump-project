package dinojump.manager;

import dinojump.util.Skin;

import java.util.List;
import java.util.Random;

public class SkinManager
{
    private static SkinManager instance;

    private List<Skin> skins;

    private SkinManager()
    {
    }

    public static SkinManager getInstance()
    {
        if (instance == null)
        {
            instance = new SkinManager();
        }
        return instance;
    }

    public void initializeSkins()
    {
        skins = DatabaseManager.getInstance().getSkins();
    }

    public Skin selectAvatarSkin()
    {
        if (skins.size() == 0)
        {
            throw new RuntimeException("There are no skins configured");
        }
        return skins.get(new Random().nextInt(skins.size()));
    }

    public List<Skin> getSkins()
    {
        return skins;
    }
}
