import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer extends JPanel {

    public int screenHeight;
    public int screenWidth;
    final int rows = 10; //Anzahl an Reihen ist festgelegt
    final int columns = 6;
    final int tileSize = 32; // Auflösung der Sprites ist fest
    public int scale;
    public int finTileSize;

    public int currentx;
    public int currenty;


    public Renderer(int width, int height)
    {
        super();
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setDoubleBuffered(true);

        screenHeight = height; //Höhe je nach auflösung und FensterHöhe
        screenWidth = width; //"
        scale = (screenHeight/rows)/tileSize ; //Skalierung geht von der Höhe aus
        finTileSize = scale * tileSize; //Berechnung der finalen fröße der Tiles

    }
    /** @param graphics Grafikobjekt von Swing
     * @param x pos x
     * @param y pos y
     * **/
    public void render(Graphics graphics,int x, int y)
    {
        //setzen globaler Variablen, da es iwi nicht anders geht
        currentx = x;
        currenty = y;

        paintComponent(graphics);
    }
    /**
     * @param image zu renderendes Sprite
     * @param x pos x
     * @param y pos y
     * Positionen als Position objekt kann man später auch noch implementieren**/
    public void render(File image, int x, int y)
    {
        //setzen globaler Variablen, da es iwi nicht anders geht
        currentx = x;
        currenty = y;

        try {
            paintComponent(ImageIO.read(image).getGraphics());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void paintComponent(Graphics graphics)
    {
        //Panel bemalen
        super.paintComponent(graphics);
        //Umwandeln in typ Graphics2d
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setColor(Color.white);
        g2d.fillRect(currentx,currenty,finTileSize,finTileSize);
        g2d.dispose();
    }
}
