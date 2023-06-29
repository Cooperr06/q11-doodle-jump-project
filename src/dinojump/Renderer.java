package dinojump;

import dinojump.util.Position;
import dinojump.util.Skin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Renderer extends Canvas
{
    private final List<JButton> buttons = new ArrayList<>();

    private static Renderer instance;
    private final JFrame window; // frame in OS
    private final JPanel panel;
    private final BufferStrategy bufferStrategy; // required to make custom render methods

    private final int rows = 10; // logic dimensions are fixed
    private final int columns = 6;
    private final int tileSize = 32; // sprite resolution is fixed

    private int screenHeight;
    private int screenWidth;

    private int scale;
    private int finTileSize;

    private Renderer(int width, int height)
    {
        super(); // call parent constructor
        this.setPreferredSize(new Dimension(width, height)); // set size of canvas to draw on
        this.setBackground(Color.black); // bg color black
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

        panel = new JPanel();
        panel.add(this);
        // putting canvas into frame
        window.add(panel);
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
     * renders image on Predetermined tileSize
     *
     * @param image image to be rendered
     */
    public void renderImage(Image image, Position position)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image, position.getX(), position.getY(), finTileSize, finTileSize, null);
        graphics.dispose();
        bufferStrategy.show();
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
        graphics.dispose();
        bufferStrategy.show();
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
        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * renders Avatar
     *
     * @param skin Skinobject to be passed
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
        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * renders Platform with twice the width of the player and half the height
     *
     * @param skin skin to render
     */
    public void renderPlatform(Skin skin, Position position)
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
        graphics.drawImage(image, position.getX(), position.getY(), finTileSize / 2, finTileSize * 2, null);
        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * draws an image that is spanned across the whole window without giving a fuck about resolution or stretching
     * <pre></pre>has to be rendered first, as it simultaneously deletes the previous frame
     *
     * @param image background image
     */
    public void renderBackground(Image image) {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image, 0, 0, window.getWidth(), window.getHeight(), null);
        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * @param button button to be rendered
     * @param x      relative position from left (float 0-1)
     * @param y      relative position from top (float 0-1)
     */
    public void renderButton(JButton button, float x, float y) {
        buttons.add(button);

        Graphics graphics = bufferStrategy.getDrawGraphics();
        float buttonX = x * window.getWidth() - button.getWidth() / 2f;
        float buttonY = y * window.getHeight() - button.getHeight() / 2f;
        button.setBounds((int) buttonX, (int) buttonY, button.getWidth(), button.getHeight());
        button.setVisible(true);
        panel.add(button);
        graphics.dispose();
        bufferStrategy.show();
    }

    public void clearScreen() {
        while (!buttons.isEmpty()) {
            window.getContentPane().remove(buttons.size() - 1);
            buttons.remove(buttons.size() - 1);
        }
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.setColor(Color.black);
        graphics.clearRect(0, 0, window.getWidth(), window.getHeight());

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

    public int getPlatformWidth() {
        return finTileSize * 2;
    }

    public int getPlatformHeight() {
        return finTileSize / 2;
    }

    public JFrame getWindow() {
        return window;
    }
}
