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
        if (instance == null)
        {
            instance = new Account();
        }
        return instance;
    }

    public String getMacAddress()
    {
        return macAddress;
    }

    public void setMacAddress(String macAddress)
    {
        this.macAddress = macAddress;
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
