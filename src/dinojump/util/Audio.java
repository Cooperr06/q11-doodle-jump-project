package dinojump.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio
{
    private static Audio instance;
    private AudioInputStream gameStart;
    private AudioInputStream gameLoop;
    private AudioInputStream lobbyLoop;
    private AudioInputStream jump;
    private AudioInputStream gameOver;
    private Clip bgMusic;

    private Audio()
    {
        try
        {
            gameStart = AudioSystem.getAudioInputStream(new File("./resources/audio/music/soundtrack_start.wav").getAbsoluteFile());
            gameLoop = AudioSystem.getAudioInputStream(new File("./resources/audio/music/soundtrack_loop.wav").getAbsoluteFile());
            lobbyLoop = AudioSystem.getAudioInputStream(new File("./resources/audio/music/lobby.wav").getAbsoluteFile());
            jump = AudioSystem.getAudioInputStream(new File("./resources/audio/fx/jump_1.wav"));
            gameOver = AudioSystem.getAudioInputStream(new File("./resources/audio/fx/gameover2.wav"));
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            throw new RuntimeException(e);
        }
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
            bgMusic.open(gameLoop);
        }
        catch (LineUnavailableException | IOException e)
        {
            throw new RuntimeException(e);
        }
        bgMusic.loop(1000);
    }

    public void playLobby()
    {
        try
        {
            bgMusic = AudioSystem.getClip();
            bgMusic.open(lobbyLoop);
        }
        catch (LineUnavailableException | IOException e)
        {
            throw new RuntimeException(e);
        }
        bgMusic.loop(100);
    }

    public void playSound(String effect)
    {
        Clip clip = null;
        try
        {
            clip = AudioSystem.getClip();
            switch (effect)
            {
                case "jump" ->
                {
                    clip.open(jump);
                }
                case "gameOver" ->
                {
                    clip.open(gameOver);
                }
            }
        }
        catch (LineUnavailableException | IOException e)
        {
            throw new RuntimeException(e);
        }

        clip.start();
    }

    public void stopMusic()
    {
        bgMusic.stop();
    }
}
