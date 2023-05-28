package database;

import org.mariadb.jdbc.MariaDbPoolDataSource;
import util.Account;
import util.Skin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager
{
    private final MariaDbPoolDataSource dataSource = new MariaDbPoolDataSource();

    /**
     * Configures the data source and tests the connection
     *
     * @param url      url to the database (e. g. <code>"jdbc:mariadb://[host]:[port]/[database]</code>
     * @param user     username for accessing the database
     * @param password user credentials for accessing the database
     * @throws SQLException if the connection attempt failed
     */
    public DatabaseManager(String url, String user, String password) throws SQLException
    {
        // configuring the data source, be advised: password & user have to be set before setting the url
        dataSource.setPassword(password);
        dataSource.setUser(user);
        dataSource.setUrl(url);

        executeSql(new File("./src/resources/initialize_db.sql")); // executes the initialization script for first setup

        System.out.println("Successfully connected to database");
    }

    public Account getAccount(int accountId)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement accountStatement = connection.prepareStatement("SELECT * FROM account WHERE id = ?");
             PreparedStatement skinsStatement = connection.prepareStatement("SELECT skin_id FROM account " +
                     "JOIN account_skin ON account.id = account_skin.account_id WHERE account.id = ?"))
        {
            // sets the parameter for the query
            accountStatement.setInt(1, accountId);
            skinsStatement.setInt(1, accountId);

            Skin[] skins = getSkinsOfIds(skinsStatement.executeQuery());

            ResultSet accountResultSet = accountStatement.executeQuery(); // executes the query and retrieves a result set
            accountResultSet.next(); // go to first row

            // retrieves all attributes from the database and returns the data
            return new Account.Builder()
                    .setAccountName(accountResultSet.getString("accountName"))
                    .setPasswordHash(accountResultSet.getString("password"))
                    .setHighscore(accountResultSet.getInt("highscore"))
                    .setSkins(skins)
                    .setCoins(accountResultSet.getInt("coins"))
                    .build();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null; // return null if an error occurred
        }
    }

    public Account getAccount(String username, String passwordHash)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement accountStatement = connection.prepareStatement("SELECT * FROM account WHERE username = ? AND password = ?");
             PreparedStatement skinsStatement = connection.prepareStatement("SELECT skin_id FROM account " +
                     "JOIN account_skin ON account.id = account_skin.account_id WHERE account.id = ?"))
        {
            // sets the parameters for the update
            accountStatement.setString(1, username);
            accountStatement.setString(2, passwordHash);

            Skin[] skins = getSkinsOfIds(skinsStatement.executeQuery());

            ResultSet accountResultSet = accountStatement.executeQuery(); // executes the query and retrieves a result set
            accountResultSet.next(); // go to first row

            // retrieves all attributes from the database and returns the data
            return new Account.Builder()
                    .setAccountName(accountResultSet.getString("accountName"))
                    .setPasswordHash(accountResultSet.getString("password"))
                    .setHighscore(accountResultSet.getInt("highscore"))
                    .setSkins(skins)
                    .setCoins(accountResultSet.getInt("coins"))
                    .build();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null; // return null if an error occurred
        }
    }

    public void addAccount(Account account)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO account (username, password, highscore, coins) VALUES (?, ?, ?, ?)"))
        {
            // sets the parameters for the update
            statement.setString(1, account.getAccountName());
            statement.setString(2, account.getPasswordHash());
            statement.setInt(3, account.getHighscore());
            statement.setInt(4, account.getCoins());

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int getCoins(int accountId)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT coins FROM account WHERE id = ?"))
        {
            // sets the parameter for the query
            statement.setInt(1, accountId);

            ResultSet resultSet = statement.executeQuery(); // executes the query and retrieves a result set
            resultSet.next(); // go to first row

            return resultSet.getInt("coins"); // return the coins of the first row
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0; // return zero if an error occurred
        }
    }

    public void addCoins(int accountId, int coins)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE account SET coins = coins + ? WHERE id = ?"))
        {
            // sets the parameters for the update
            statement.setInt(1, coins);
            statement.setInt(2, accountId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deductCoins(int accountId, int coins)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE account SET coins = coins - ? WHERE id = ?"))
        {
            statement.setInt(1, coins);
            statement.setInt(2, accountId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setHighscore(int accountId, int highscore)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE account SET highscore = ? WHERE id = ?"))
        {
            // sets the parameters for the update
            statement.setInt(1, highscore);
            statement.setInt(2, accountId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int getHighscore(int accountId)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT highscore FROM account WHERE id = ?"))
        {
            // sets the parameter for the query
            statement.setInt(1, accountId);

            ResultSet resultSet = statement.executeQuery(); // executes the query and retrieves a result set
            resultSet.next(); // go to first row

            return resultSet.getInt("highscore"); // return the coins of the first row
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0; // return zero if an error occurred
        }
    }

    /**
     * Collects all skins with the ids of the result set
     *
     * @param resultSet result set with skin ids
     * @return skin array
     */
    public Skin[] getSkinsOfIds(ResultSet resultSet)
    {
        try
        {
            // stores all skin ids of the result set in a list
            List<Integer> skinIds = new ArrayList<>(); // TODO: Replace with our list
            while (resultSet.next())
            {
                skinIds.add(resultSet.getInt("skin_id"));
            }

            // goes through every skin id and stores the skins which belong to their skin ids
            List<Skin> skins = new ArrayList<>(); // TODO: Replace with our list
            for (Integer skinId : skinIds)
            {
                skins.add(Skin.ofId(skinId));
            }
            return skins.toArray(Skin[]::new);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null; // return null if an error occurred
        }
    }

    /**
     * Executes sql script
     *
     * @param sql sql script to execute
     */
    public void executeSql(String sql)
    {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement())
        {
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Executes a sql file
     *
     * @param file sql file to execute
     * @see DatabaseManager#executeSql(String)
     */
    public void executeSql(File file)
    {
        List<String> sqlCommands = new ArrayList<>(); // TODO: Replace with our list

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            StringBuilder builder = new StringBuilder();
            // sums the whole content of the file up
            reader.lines().forEach(line ->
            {
                // if a command is finished with this line, add the command to the list and clear the builder for further commands in the file
                if (line.endsWith(";"))
                {
                    builder.append(line);
                    sqlCommands.add(builder.toString());

                    builder.replace(0, builder.length(), "");
                }
                else
                {
                    builder.append(line + "\n"); // if the command is not finished yet, add the line to the current command
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        sqlCommands.forEach(this::executeSql); // executes every command of the file
    }
}
