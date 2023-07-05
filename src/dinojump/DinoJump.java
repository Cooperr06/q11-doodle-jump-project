package dinojump;

import dinojump.manager.CollisionManager;
import dinojump.manager.PlatformManager;
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
        CollisionManager.getInstance().iterate();
        Avatar.getInstance().iterateLoop();
        PlatformManager.getInstance().iterateLoop();
        Renderer.getInstance().clearScreen();
    }
}
