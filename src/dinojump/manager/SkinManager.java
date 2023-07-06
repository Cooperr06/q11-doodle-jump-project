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

    public Skin getSkin(int id)
    {
        return skins.stream()
                .filter(skin -> skin.getId() == id)
                .limit(1)
                .findFirst().orElseThrow();
    }

    public List<Skin> getSkins()
    {
        return skins;
    }
}
