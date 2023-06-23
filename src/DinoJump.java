import util.Avatar;
import util.Position;
import util.Skin;
import util.Stage;

public class DinoJump
{
    private static DinoJump instance;

    private Avatar avatar;

    private DinoJump()
    {
        avatar = new Avatar(Skin.of(0), new Position(0, 0));
    }

    public static DinoJump getInstance()
    {
        if (instance == null)
        {
            instance = new DinoJump();
        }
        return instance;
    }

    public void start()
    {
        Stage.getInstance().showMainScreen();
    }
}
