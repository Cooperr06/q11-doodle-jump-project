package dinojump.manager;

import dinojump.util.Skin;

import java.util.ArrayList;
import java.util.List;

public class SkinManager
{
    private static SkinManager instance;

    private final List<Skin> skins = new ArrayList<>();

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
    }

    public List<Skin> getSkins()
    {
        return skins;
    }
}
