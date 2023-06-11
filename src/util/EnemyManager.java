package util;
import java.util.Random;
public class EnemyManager
{
    // Draft of a class using the singleton pattern
    private List enemies;
    private Random rnGenerator;
    public static EnemyManager eManager = new EnemyManager();

    private EnemyManager()
    {
        enemies = new List();
        rnGenerator = new Random();
    }
    public static EnemyManager returneManager()
    {
        return eManager;
    }

    public Enemy generateEnemy()
    {
        // Asking the List to return the Enemy with the highest y value
        Enemy eComparison = enemies.giveLastEnemy();
        // gaining a random number for x and y
        int y = rnGenerator.nextInt(20) + 20;
        int x = rnGenerator.nextInt(80);
        Enemy enew;
        // if not then the game just began -> creating a platform a bit above the avatar
        if(eComparison == null);
        {
            enew= new Enemy(skin, Position(x,y));
        }
        else
        // creating a Enemy above the last
        {
            Position position = eComparison.getPosition();
            int yOld = position.getY();               enew = new Platform(skin, new Position (x,yOld + y);
        }
        return enew;
    }
}
}
