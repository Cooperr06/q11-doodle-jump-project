package dinojump;

import dinojump.manager.PlatformManager;
import dinojump.manager.SkinManager;
import dinojump.util.Avatar;
import dinojump.util.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class DinoJump
{
    private static DinoJump instance;

    private DinoJump()
    {
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
        DatabaseManager.getInstance(System.getProperty("db_url"), System.getProperty("db_user"), System.getProperty("db_password"));
        SkinManager.getInstance().addSkins(DatabaseManager.getInstance().getSkins());
        Stage.getInstance().showMainScreen();
    }

    public void startGameLoop(long fps)
    {
        Timer timer = new Timer();
        PlatformManager.getInstance().spawnInitialPlatforms(20);
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                loop();
            }
        }, 0L, 1000 / fps);
    }

    private void loop()
    {
        Renderer.getInstance().updateBackgroundColor();
        Avatar.getInstance().iterateLoop();
        PlatformManager.getInstance().iterateLoop();
        Renderer.getInstance().clearScreen();
    }
}
