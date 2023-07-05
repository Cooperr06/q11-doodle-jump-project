package dinojump.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

public class Audio
{
    //bitte nicht angucken
    private static Audio instance;
    private AudioInputStream gameStart;
    private AudioInputStream gameLoop;
    private AudioInputStream lobbyLoop;
    Clip bgMusic;
    private AudioInputStream jump;
    private AudioInputStream gameOver;

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
        catch (Exception e)
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
        catch (LineUnavailableException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
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
        }
        catch (LineUnavailableException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            bgMusic.open(lobbyLoop);
        }
        catch (LineUnavailableException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
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
        }
        catch (LineUnavailableException e)
        {
            throw new RuntimeException(e);
        }

        switch (effect)
        {
            case "jump" ->
            {
                try
                {
                    clip.open(jump);
                }
                catch (LineUnavailableException | IOException e)
                {
                    throw new RuntimeException(e);
                }

            }
            case "gameOver" ->
            {
                try
                {
                    clip.open(gameOver);
                }
                catch (LineUnavailableException e)
                {
                    throw new RuntimeException(e);
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
        clip.start();
    }

    public void stopMusic()
    {
        bgMusic.stop();
    }
}
