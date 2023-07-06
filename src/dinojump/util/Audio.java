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

    private final File gameStart;
    private final File gameLoop;
    private final File lobbyLoop;
    private final File jump;
    private final File gameOver;
    private final File smallAchievement;
    private final File bigAchievement;

    private Clip bgMusic;

    private Audio()
    {
        gameStart = new File("./resources/audio/music/soundtrack_start.wav");
        gameLoop = new File("./resources/audio/music/soundtrack_loop.wav");
        lobbyLoop = new File("./resources/audio/music/lobby.wav");
        jump = new File("./resources/audio/fx/jump_1.wav");
        gameOver = new File("./resources/audio/fx/gameover2.wav");
        smallAchievement = new File("./resources/audio/fx/achievementSmall.wav");
        bigAchievement = new File("./resources/audio/fx/achievementBig.wav");
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
            bgMusic.open(AudioSystem.getAudioInputStream(gameLoop));
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
            bgMusic.open(AudioSystem.getAudioInputStream(lobbyLoop));
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
                    clip.open(AudioSystem.getAudioInputStream(jump));
                }
                case "gameOver" -> bgMusic.open(AudioSystem.getAudioInputStream(gameOver));
                case "achievementSmall" -> bgMusic.open(AudioSystem.getAudioInputStream(smallAchievement));
                case "achievementBIg" -> bgMusic.open(AudioSystem.getAudioInputStream(bigAchievement));
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
