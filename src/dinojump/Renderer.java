package dinojump;

import dinojump.manager.InputManager;
import dinojump.util.Position;
import dinojump.util.Skin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;
import static java.lang.Math.sin;

public class Renderer extends Canvas
{
    private final List<JButton> buttons = new ArrayList<>();

    private static Renderer instance;
    private final JFrame window; // frame in OS
    private final JPanel panel;
    private final BufferStrategy bufferStrategy; // required to make custom render methods

    private final int rows = 10; // logic dimensions are fixed
    private final int columns = 20;
    private final int tileSize = 32; // sprite resolution is fixed

    private int scale;
    private int finTileSize;

    private int screenHeight;
    private int screenWidth;

    private long startTime;
    private Color backgroundColor;

    private Renderer(int width, int height)
    {
        super(); // call parent constructor
        backgroundColor = new Color(0, 0, 0);//init bg color
        this.setPreferredSize(new Dimension(width, height)); // set size of canvas to draw on
        this.setBackground(backgroundColor); // set bg color
        this.setVisible(true);

        // initializing values
        screenHeight = height;
        screenWidth = width;
        scale = (screenHeight / rows) / tileSize; // scale depends on height
        finTileSize = scale * tileSize; // calculation of final tile size on screen

        // creating frame and setting default attributes
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("DinoJump");
        window.setVisible(true);
        window.setPreferredSize(new Dimension(width, height));
        window.setLocation(0, 0);

        window.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                super.componentResized(e);
                resize(e.getComponent().getWidth(), e.getComponent().getHeight());

            }
        });
        panel = new JPanel();
        panel.add(this);

        // putting canvas into frame
        window.add(panel);
        // adding InputManager as key listener
        window.addKeyListener(InputManager.getInstance());

        window.pack();

        startTime = System.currentTimeMillis();
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
     * renders image on Predetermined tileSize
     *
     * @param image image to be rendered
     */
    public void renderImage(Image image, Position position)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image, position.getX(), position.getY(), finTileSize, finTileSize, null);
    }

    /**
     * renders text
     *
     * @param text text to be shown
     * @param font text font
     * @see Font#Font(String, int, int)
     */
    public void renderText(String text, Position position, Font font)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();

        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(text, position.getX(), position.getY());
    }

    /**
     * renders text
     *
     * @param text text to be shown
     * @param size font size
     */
    public void renderText(String text, Position position, int size)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", Font.PLAIN, size));
        graphics.drawString(text, position.getX(), position.getY());
    }

    /**
     * renders Avatar
     *
     * @param skin Skin object to be passed
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
     * renders Platform with twice the width of the player and half the height
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
            graphics.drawImage(image, platform.getPosition().getX() * window.getWidth() / columns, platform.getPosition().getY(), finTileSize, finTileSize, null);
        });
        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * draws an image that is spanned across the whole window without giving a fuck about resolution or stretching
     * <pre></pre>has to be rendered first, as it simultaneously deletes the previous frame
     *
     * @param image background image
     */
    public void renderBackground(Image image)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image, 0, 0, window.getWidth(), window.getHeight(), null);
    }

    /**
     * @param button button to be rendered
     * @param x      relative position from left (float 0-1)
     * @param y      relative position from top (float 0-1)
     */
    public void renderButton(JButton button, float x, float y)
    {
        buttons.add(button);

        Graphics graphics = bufferStrategy.getDrawGraphics();
        float buttonX = x * window.getWidth() - button.getWidth() / 2f;
        float buttonY = y * window.getHeight() - button.getHeight() / 2f;

        button.setBounds((int) buttonX, (int) buttonY, button.getWidth(), button.getHeight());
        button.setVisible(true);
        panel.add(button);
    }

    public void clearScreen()
    {
        while (!buttons.isEmpty())
        {
            window.getContentPane().remove(buttons.size() - 1);
            buttons.remove(buttons.size() - 1);
        }
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.setColor(Color.black);
        graphics.clearRect(0, 0, window.getWidth(), window.getHeight());
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

    public void resize(int width, int height)
    {
        this.setBounds(window.getX(), window.getY(), width, height);
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
        return finTileSize;
    }

    public int getPlatformHeight()
    {
        return finTileSize / 2;
    }

    public JFrame getWindow()
    {
        return window;
    }
}
