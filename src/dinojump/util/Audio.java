package dinojump.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Audio
{
    private static Audio instance;
    private AudioInputStream gameStart;
    private AudioInputStream gameLoop;
    private AudioInputStream lobbyLoop;

    private Audio()
    {
        try
        {
            gameStart = AudioSystem.getAudioInputStream(new File("./resources/audio/music/soundtrack_start.wav").getAbsoluteFile());
        }
        catch (UnsupportedAudioFileException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            gameLoop = AudioSystem.getAudioInputStream(new File("./resources/audio/music/soundtrack_loop.wav").getAbsoluteFile());
        }
        catch (UnsupportedAudioFileException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            lobbyLoop = AudioSystem.getAudioInputStream(new File("./resources/audio/music/lobby.wav").getAbsoluteFile());
        }
        catch (UnsupportedAudioFileException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
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

    public void playSoundtrack()
    {/*
        Clip clip;
        try
        {
            clip = AudioSystem.getClip();
        }
        catch (LineUnavailableException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            clip.open(gameLoop);
        }
        catch (LineUnavailableException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        clip.start();
        */
    }

}
