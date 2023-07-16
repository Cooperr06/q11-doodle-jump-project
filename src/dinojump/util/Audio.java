package dinojump.util;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Audio
{
    private static Audio instance;

    // music files
    private final URL lobbyFileUrl;
    private final URL soundtrackFileUrl;

    // fx effect files
    private final URL bigAchievementFileUrl;
    private final URL smallAchievementFileUrl;
    private final URL gameOverFileUrl;
    private final URL jumpFileUrl;

    private final Clip bgMusic;

    private Audio()
    {
        lobbyFileUrl = getClass().getResource("/audio/music/lobby.wav");
        soundtrackFileUrl = getClass().getResource("/audio/music/soundtrack.wav");

        bigAchievementFileUrl = getClass().getResource("/audio/fx/achievementBig.wav");
        smallAchievementFileUrl = getClass().getResource("/audio/fx/achievementSmall.wav");
        gameOverFileUrl = getClass().getResource("/audio/fx/gameover.wav");
        jumpFileUrl = getClass().getResource("/audio/fx/jump.wav");

        try
        {
            bgMusic = AudioSystem.getClip();
        }
        catch (LineUnavailableException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Audio getInstance()
    {
        if (instance == null)
        {
            instance = new Audio();
        }
        return instance;
    }

    public void playGame()
    {
        createClip(soundtrackFileUrl, false, true);
    }

    public void playLobby()
    {
        createClip(lobbyFileUrl, false, true);
    }

    public void playSound(String effect)
    {
        switch (effect)
        {
            case "jump" -> createClip(jumpFileUrl, true, false);
            case "achievementSmall" -> createClip(smallAchievementFileUrl, true, false);
            case "achievementBig" -> createClip(bigAchievementFileUrl, true, false);
            case "gameOver" -> createClip(gameOverFileUrl, false, false);
        }
    }

    public void createClip(URL fileUrl, boolean useNewClip, boolean loop)
    {
        try (AudioInputStream inputStream = AudioSystem.getAudioInputStream(fileUrl))
        {
            Clip clip = useNewClip ? AudioSystem.getClip() : bgMusic;
            clip.open(inputStream);
            if (loop)
            {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.start();
        }
        catch (LineUnavailableException | UnsupportedAudioFileException | IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void stopMusic()
    {
        bgMusic.close();
    }
}
