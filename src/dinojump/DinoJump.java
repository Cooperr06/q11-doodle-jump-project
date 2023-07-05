package dinojump;

import dinojump.manager.DatabaseManager;
import dinojump.manager.PlatformManager;
import dinojump.manager.ScoreManager;
import dinojump.util.Audio;
import dinojump.manager.SkinManager;
import dinojump.util.Avatar;
import dinojump.util.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class DinoJump
{
    private boolean running = false;
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
        DatabaseManager.getInstance().initialize(System.getProperty("db_url"), System.getProperty("db_user"), System.getProperty("db_password"));
        SkinManager.getInstance().initializeSkins();
        Stage.getInstance().showMainScreen();
    }

    public static void startGameLoop(long fps)
    {
        setRunning(true);
        Timer timer = new Timer();
        PlatformManager.getInstance().spawnInitialPlatforms(20);
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
        Avatar.getInstance().iterateLoop(); // avatar and platform are interchageable
        PlatformManager.getInstance().iterateLoop();
        ScoreManager.getInstance().iterateLoop(); // text has to be last!
        Renderer.getInstance().clearScreen();
    }

    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
}
