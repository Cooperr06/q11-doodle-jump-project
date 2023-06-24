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
        Avatar.getInstance().iterateLoop();
        PlatformManager.getInstance().iterateLoop();
    }
}
