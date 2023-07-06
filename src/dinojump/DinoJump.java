package dinojump;

import dinojump.manager.CollisionManager;
import dinojump.manager.PlatformManager;
import dinojump.manager.ScoreManager;
import dinojump.util.Audio;
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
        PlatformManager.getInstance().spawnInitialPlatforms(14);
        Audio.getInstance().playGame();
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
        // this order has to be preserved in order for graphics to show up correctly
        Renderer.getInstance().updateBackgroundColor(); // background to draw on
        CollisionManager.getInstance().iterateLoop();
        Avatar.getInstance().iterateLoop(); // avatar and platform are interchageable
        PlatformManager.getInstance().iterateLoop();
        ScoreManager.getInstance().iterateLoop(); // text has to be last!
        Renderer.getInstance().clearScreen();
    }
}
