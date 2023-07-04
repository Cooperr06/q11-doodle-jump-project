package util;

public class Account
{
    private final String macAddress;

    private int highscore;

    public Account(String macAddress, int highscore)
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

    public static class Builder
    {
        private String macAddress;
        private int highscore;

        public Builder()
        {
        }

        public Builder setMacAddress(String macAddress)
        {
            this.macAddress = macAddress;
            return this;
        }

        public Builder setHighscore(int highscore)
        {
            this.highscore = highscore;
            return this;
        }

        public Account build()
        {
            return new Account(macAddress, highscore);
        }
    }
}
