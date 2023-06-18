import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas {

    public int screenHeight;
    public int screenWidth;
    private final int rows = 10; //logic dimensions are fixed
    private final int columns = 6;
    private final int tileSize = 32; //sprite resolution is fixed
    public int scale;
    public int finTileSize;


    JFrame window; //frame in OS
    private BufferStrategy bufferStrategy;//required to make custom render functions

    public Renderer(int width, int height)
    {

        super();//call parent constructor
        this.setPreferredSize(new Dimension(width,height));//set size of canvas to draw on
        this.setBackground(Color.black);//bg color black
        this.setVisible(true);

        //initializing values
        screenHeight = height;
        screenWidth = width;
        scale = (screenHeight/rows)/tileSize ; //scale depends on height
        finTileSize = scale * tileSize; //calculation of final tile size on screen

        //creating frame and setting default attributes
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("DinoJump");
        window.setVisible(true);
        window.setPreferredSize(new Dimension(width,height));
        window.setLocation(0,0);

        //putting canvas into frame
        window.add(this);
        window.pack();

        //creation of custom buffer strategy required to make custom render functions
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();

    }

    /**
     * renders Image on Predetermined tilesize
     * @param image image to be rendered
     * @param x x position
     * @param y y position
     */
    public void renderImage(Image image,int x,int y)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image,x,y,finTileSize,finTileSize,null);
        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     *renders text
     * @param text text to be shown
     * @param x position x (left border of the text)
     * @param y position y (bottom of the text)
     * @param font synthax : new Font(fontName, Font.linestrength, fontSize)
     */
    public void renderText(String text,int x,int y,Font font)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();

        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString("Hallo",x,y);
        graphics.dispose();
        bufferStrategy.show();

    }
    /**
     *renders text
     * @param text text to be shown
     * @param x position x (left border of the text)
     * @param y position y (bottom of the text)
     * @param size font size
     */
    public void renderText(String text,int x,int y, int size)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", Font.PLAIN, size));
        graphics.drawString("Hallo",x,y);
        graphics.dispose();
        bufferStrategy.show();

    }

    /**
     *
     * draws an image that is spanned across the whole window without giving a fuck about resolution or stretching
     * <pre></pre>has to be rendered first, as it simultaniously deletes the previous frame
     * @param image background image
     */
    public void drawBackground(Image image)
    {
        Graphics graphics = getBufferStrategy().getDrawGraphics();
        graphics.drawImage(image,0,0,window.getWidth(),window.getHeight(),null);
        graphics.dispose();
        bufferStrategy.show();
    }
}
