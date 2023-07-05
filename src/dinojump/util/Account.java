package dinojump.util;

public class Account
{
    private static Account instance;

    private String macAddress;
    private int highscore;

    private Account()
    {
    }

    public static Account getInstance()
    {
        return instance;
    }

    public void initialize(String macAddress, int highscore)
    {
        this.macAddress = macAddress;
        this.highscore = highscore;
    }

    public String getMacAddress()
    {
        return macAddress;
    }

    public int getHighscore()
    {
        return highscore;
    }

    public void setHighscore(int highscore)
    {
        this.highscore = highscore;
    }
}
