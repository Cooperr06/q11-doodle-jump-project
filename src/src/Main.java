import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Main {


    public static void main(String[] args)
    {
        //Fenster wird erzeugt und eingestellt (nur provisorisch kann dann auch verschoben werden)
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("DinoJump");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setPreferredSize(new Dimension(500,500));

        //RendererPanel wo das Spiel drauf angezeigt wird wird erzeugt
        Renderer canvas = new Renderer(320,320);
        window.add(canvas);
        window.pack();

        //zum Rendern image Methode bei canvas aufrufen und Position mitgeben
        //wenn Fehler auftritt wird nur weißes Feld gerendert
        canvas.render(new File("test"),50,30);


    }
}