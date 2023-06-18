import manager.InputManager;
import manager.PlatformManager;
import util.Avatar;
import util.Position;
import util.Skin;
import util.Stage;

public class DinoJump
{
    private static DinoJump instance;
    private InputManager inputManager;
    private PlatformManger platformManger;
    private Stage stage;
    private Avatar avatar;

    private DinoJump()
    {
        inputManager = new PlatformManager();
        stage = new Stage();
        avatar = new Avatar(Skin.of(0), new Position(0, 0));
    }

    public static void getInstance()
    {
        if (instance == null)
        {
            new DinoJump();
        }
    }

    public void start()
    {
        stage.showMainScreen();
    }
}
