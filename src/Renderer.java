import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas
{
    private final JFrame window; // frame in OS
    private final BufferStrategy bufferStrategy; // required to make custom render methods

    private final int rows = 10; // logic dimensions are fixed
    private final int columns = 6;
    private final int tileSize = 32; // sprite resolution is fixed

    public int screenHeight;
    public int screenWidth;

    public int scale;
    public int finTileSize;

    public Renderer(int width, int height)
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

        // putting canvas into frame
        window.add(this);
        window.pack();

        // creating custom buffer strategy (required to make custom render methods)
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();

    }

    /**
     * renders image on Predetermined tileSize
     *
     * @param image image to be rendered
     * @param x     x position
     * @param y     y position
     */
    public void renderImage(Image image, int x, int y)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image, x, y, finTileSize, finTileSize, null);
        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * renders text
     *
     * @param text text to be shown
     * @param x    position x (left border of the text)
     * @param y    position y (bottom of the text)
     * @param font text font
     * @see Font#Font(String, int, int)
     */
    public void renderText(String text, int x, int y, Font font)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();

        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString("Hallo", x, y);
        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * renders text
     *
     * @param text text to be shown
     * @param x    position x (left border of the text)
     * @param y    position y (bottom of the text)
     * @param size font size
     */
    public void renderText(String text, int x, int y, int size)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", Font.PLAIN, size));
        graphics.drawString("Hallo", x, y);
        graphics.dispose();
        bufferStrategy.show();

    }

    /**
     * draws an image that is spanned across the whole window without giving a fuck about resolution or stretching
     * <pre></pre>has to be rendered first, as it simultaneously deletes the previous frame
     *
     * @param image background image
     */
    public void drawBackground(Image image)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image, 0, 0, window.getWidth(), window.getHeight(), null);
        graphics.dispose();
        bufferStrategy.show();
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

    public int getFinTileSize()
    {
        return finTileSize;
    }
}
