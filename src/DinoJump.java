import util.Avatar;
import util.Position;

public class DinoJump
{
    private DinoJump instance;
    private InputManager inputManager;
    private PlatformManger platformManger;
    private Stage stage;
    private Avatar avatar;
    private DinoJump()
    {

    }

    public void getInstance(Skin skin, Position position)
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
