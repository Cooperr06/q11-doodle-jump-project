package dinojump;

import dinojump.manager.InputManager;
import dinojump.util.Position;
import dinojump.util.Skin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import static java.lang.Math.round;
import static java.lang.Math.sin;

public class Renderer extends Canvas
{
    private static Renderer instance;

    private final JFrame window; // frame in OS
    private final BufferStrategy bufferStrategy; // required to make custom render methods

    private final int rows = 10; // logic dimensions are fixed
    private final int columns = 20;

    private final int finTileSize;

    private final int screenHeight;
    private final int screenWidth;

    private long startTime;
    private Color backgroundColor;

    private Renderer(int width, int height)
    {
        super(); // call parent constructor
        this.setPreferredSize(new Dimension(width, height)); // set size of canvas to draw on
        this.setBackground(backgroundColor); // set bg color
        this.setVisible(true);

        // initializing values
        screenHeight = height;
        screenWidth = width;
        // sprite resolution is fixed
        int tileSize = 32;
        int scale = (screenHeight / rows) / tileSize; // scale depends on height
        finTileSize = scale * tileSize; // calculation of final tile size on screen

        // creating frame and setting default attributes
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("DinoJump");
        window.setVisible(true);
        window.setPreferredSize(new Dimension(width, height));
        window.setLocation(0, 0);

        JPanel panel = new JPanel();
        panel.add(this);

        // putting canvas into frame
        window.add(panel);
        // adding InputManager as key listener
        window.addKeyListener(InputManager.getInstance());
        // adding a focus listener to always request focus back if it is lost
        window.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                window.requestFocus();
            }
        });
        window.pack();

        // creating custom buffer strategy (required to make custom render methods)
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
    }

    public static Renderer getInstance()
    {
        if (instance == null)
        {
            instance = new Renderer(1080, 720);
        }
        return instance;
    }

    /**
     * Renders an image on predetermined tileSize
     *
     * @param image image to be rendered
     */
    public void renderImage(Image image, Position position)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image, position.getX(), position.getY(), finTileSize, finTileSize, null);
    }

    /**
     * Renders the specific text in the specific font
     *
     * @param text text to be shown
     * @param font text font
     */
    public void renderText(String text, Position position, Font font)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();

        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(text, position.getX(), position.getY());
    }

    /**
     * Renders the specific text in the specific font size, here the default font "Arial" is used
     *
     * @param text text to be shown
     * @param size font size
     */
    public void renderText(String text, Position position, int size)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", Font.PLAIN, size));
        graphics.drawString(text, position.getX(), position.getY());
        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * Renders the specific (avatar) skin at the specific position
     *
     * @param skin     skin object to be rendered
     * @param position position at which the skin should be rendered
     */
    public void renderAvatar(Skin skin, Position position)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        Image image;
        try
        {
            image = ImageIO.read(skin.getImages()[0]);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        graphics.drawImage(image, position.getX(), position.getY(), finTileSize, finTileSize, null);
    }

    /**
     * Renders the specific platforms with twice the width of the player and half the height
     *
     * @param platforms platforms to render
     */
    public void renderPlatforms(list.List platforms)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();

        platforms.forEach(platform ->
        {
            Image image;
            try
            {
                image = ImageIO.read(platform.getSkin().getImages()[0]);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            graphics.drawImage(image, platform.getPosition().getX() * window.getWidth() / columns, platform.getPosition().getY(), finTileSize * 3 / 4, finTileSize * 3 / 4, null);
        });
    }

    /**
     * Draws an image that is spanned across the whole window without giving a fuck about resolution<br>
     * or stretching has to be rendered first, as it simultaneously deletes the previous frame
     *
     * @param image background image to draw
     */
    public void renderBackground(Image image)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image, 0, 0, window.getWidth(), window.getHeight(), null);
    }

    public void updateBackgroundColor()
    {
        float minimumBrightness = 0.2f;
        float speed = 0.8f;
        float time = (System.currentTimeMillis() - startTime) / 1000f;

        int r = (int) round((((sin(time * 0.3f * speed) + 1) / 2) / (1 / (1 - minimumBrightness)) + minimumBrightness) * 255f);
        int g = (int) round((((sin(time * 0.4f * speed) + 1) / 2) / (1 / (1 - minimumBrightness)) + minimumBrightness) * 255f);
        int b = (int) round((((sin(time * 0.5f * speed) + 1) / 2) / (1 / (1 - minimumBrightness)) + minimumBrightness) * 255f);
        backgroundColor = new Color(r, g, b);
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, window.getWidth(), window.getHeight());
    }

    public void reset()
    {
        clearScreen();
        resetBackgroundColor();
    }

    public void clearScreen()
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.setColor(Color.black);
        graphics.clearRect(0, 0, window.getWidth(), window.getHeight());
    }

    public void resetBackgroundColor()
    {
        backgroundColor = new Color(0, 0, 0);
        startTime = System.currentTimeMillis();
    }

    public JFrame getWindow()
    {
        return window;
    }

    public int getRows()
    {
        return rows;
    }

    public int getColumns()
    {
        return columns;
    }

    public int getScreenHeight()
    {
        return screenHeight;
    }

    public int getScreenWidth()
    {
        return screenWidth;
    }

    public int getAvatarDimensions()
    {
        return finTileSize;
    }

    public int getPlatformWidth()
    {
        return finTileSize / 2;
    }

    public int getPlatformHeight()
    {
        return finTileSize / 4;
    }
}
