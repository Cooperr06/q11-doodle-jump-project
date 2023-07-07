package dinojump;

import dinojump.manager.*;
import dinojump.util.Audio;
import dinojump.util.Avatar;
import dinojump.util.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class DinoJump
{
    private boolean running = false;
    private Timer timer;
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
        DatabaseManager.getInstance().initialize(System.getenv("db_url"), System.getenv("db_user"), System.getenv("db_password"));
        SkinManager.getInstance().initializeSkins();
        Stage.getInstance().showMainScreen();
    }

    public void startGameLoop(long fps)
    {
        reset();
        PlatformManager.getInstance().spawnInitialPlatforms(14);
        this.setRunning(true);
        timer = new Timer("dinojump-game");
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
        Avatar.getInstance().iterateLoop(); // avatar and platform are interchangeable
        PlatformManager.getInstance().iterateLoop();
        ScoreManager.getInstance().iterateLoop(); // text has to be last!
        Renderer.getInstance().clearScreen();
    }

    public void reset()
    {
        if (timer != null)
        {
            timer.cancel();
        }
        Renderer.getInstance().clearScreen();
        Audio.getInstance().stopMusic();
        PlatformManager.getInstance().getPlatforms().clear();
        Avatar.getInstance().reset();
        ScoreManager.getInstance().resetScore();
    }

    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    public Timer getTimer()
    {
        return timer;
    }
}
