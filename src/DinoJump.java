import util.Avatar;
import util.Position;
import util.Skin;
import util.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class DinoJump
{
    private static DinoJump instance;
    private Stage stage;
    private Avatar avatar;

    private DinoJump()
    {
        stage = new Stage();
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
        stage.showMainScreen();
    }

    public void startGameLoop(long fps)
    {
        Timer timer = new Timer("timer1", true);
        PlatformManager.getInstance().spawnInitialPlatforms(6);
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                loop();
            }
        }, 0L, fps / 1000);

    }

    private void loop()
    {
        avatar.iterateLoop();
        PlatformManager.getInstance().iterateLoop();
    }

    public Avatar getAvatar()
    {
        return avatar;
    }
}
