package dinojump;

import dinojump.manager.PlatformManager;
import dinojump.manager.ScoreManager;
import dinojump.manager.SkinManager;
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
        SkinManager.getInstance().initializeSkins();
        Stage.getInstance().showMainScreen();
    }

    public void startGameLoop(long fps)
    {
        reset();
        this.setRunning(true);
        timer = new Timer("dinojump-game");
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
        Avatar.getInstance().setYAcceleration(1);
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
