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

    public void addSkins(List<Skin> skins)
    {
        this.skins.addAll(skins);
    }

    public void addSkin(Skin skin)
    {
        skins.add(skin);
    }

    public void addSkin(int id)
    {
        skins.add(Skin.of(id));
    }

    public List<Skin> getSkins()
    {
        return skins;
    }
}
