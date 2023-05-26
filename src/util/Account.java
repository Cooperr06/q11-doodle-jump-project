package util;

public class Account
{
    private final String accountName;

    private int highscore;
    private Skin[] skins;
    private int coins;

    public Account(String accountName, int highscore, Skin[] skins, int coins)
    {
        this.accountName = accountName;
        this.highscore = highscore;
        this.skins = skins;
        this.coins = coins;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public int getHighscore()
    {
        return highscore;
    }

    public void setHighscore(int highscore)
    {
        this.highscore = highscore;
    }

    public Skin[] getSkins()
    {
        return skins;
    }

    public void setSkins(Skin[] skins)
    {
        this.skins = skins;
    }

    public int getCoins()
    {
        return coins;
    }

    public void setCoins(int coins)
    {
        this.coins = coins;
    }

    public void addCoins(int coins)
    {
        this.coins += coins;
    }

    public void deductCoins(int coins)
    {
        this.coins -= coins;
    }

    public static class Builder
    {
        private String accountName;
        private int highscore;
        private Skin[] skins;
        private int coins;

        public Builder()
        {
        }

        public Builder setAccountName(String accountName)
        {
            this.accountName = accountName;
            return this;
        }

        public Builder setHighscore(int highscore)
        {
            this.highscore = highscore;
            return this;
        }

        public Builder setSkins(Skin[] skins)
        {
            this.skins = skins;
            return this;
        }

        public Builder setCoins(int coins)
        {
            this.coins = coins;
            return this;
        }

        public Account build()
        {
            return new Account(accountName, highscore, skins, coins);
        }
    }
}