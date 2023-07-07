package dinojump.util;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Audio
{
    private static Audio instance;

    private final File gameFile;
    private final File lobbyFile;

    private final File jumpFile;
    private final File smallAchievementFile;
    private final File bigAchievementFile;
    private final File gameOverFile;

    private final Clip bgMusic;

    private Audio()
    {
        gameFile = new File("./resources/audio/music/soundtrack.wav");
        lobbyFile = new File("./resources/audio/music/lobby.wav");
        jumpFile = new File("./resources/audio/fx/jump_1.wav");
        gameOverFile = new File("./resources/audio/fx/gameover2.wav");
        smallAchievementFile = new File("./resources/audio/fx/achievementSmall.wav");
        bigAchievementFile = new File("./resources/audio/fx/achievementBig.wav");
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
        try
        {
            bgMusic.open(AudioSystem.getAudioInputStream(gameFile));
        }
        catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
        {
            throw new RuntimeException(e);
        }
        bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playLobby()
    {
        try
        {
            bgMusic.open(AudioSystem.getAudioInputStream(lobbyFile));
        }
        catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
        {
            throw new RuntimeException(e);
        }
        bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playSound(String effect)
    {
        Clip clip = bgMusic;
        try
        {
            switch (effect)
            {
                case "jump" ->
                {
                    clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(jumpFile));
                }
                case "achievementSmall" ->
                {
                    clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(smallAchievementFile));
                }
                case "achievementBig" ->
                {
                    clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(bigAchievementFile));
                }
                case "gameOver" -> bgMusic.open(AudioSystem.getAudioInputStream(gameOverFile));
            }
        }
        catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
        {
            throw new RuntimeException(e);
        }
        clip.start();
    }

    public void stopMusic()
    {
        bgMusic.close();
    }
}
