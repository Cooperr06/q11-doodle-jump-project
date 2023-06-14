import util.Avatar;
import util.Position;

public class DinoJump
{
    private static DinoJump instance;
    private static InputManager inputManager;
    private static PlatformManger platformManger;
    private static Stage stage;
    private static Avatar avatar;
    private DinoJump()
    {

    }

    public static void getInstance(Skin skin, Position position)
    {
        if(instance == null)
        {
            instance = new DinoJump();
        }
        if(inputManager == null)
        {
            inputManager = new InputManager();
        }
        if(stage == null)
        {
            stage = new Stage();
        }
        if(avatar == null)
        {
            avatar = new Avatar(skin, position);
        }
    }
    public void start()
    {
        stage.showMainScreen();
    }
    public void startGameLoop()
    {

    }
}
