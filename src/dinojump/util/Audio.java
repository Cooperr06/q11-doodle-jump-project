package dinojump.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio
{
    private static Audio instance;

    private final AudioInputStream gameStart;
    private final AudioInputStream gameLoop;
    private final AudioInputStream lobbyLoop;
    private final AudioInputStream jump;
    private final AudioInputStream gameOver;

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
            bgMusic = AudioSystem.getClip();
        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e)
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
        Clip clip;
        try
        {
            clip = AudioSystem.getClip();
            switch (effect)
            {
                case "jump" -> clip.open(jump);
                case "gameOver" -> clip.open(gameOver);
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
