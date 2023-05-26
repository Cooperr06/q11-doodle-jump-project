package database;

import org.mariadb.jdbc.MariaDbPoolDataSource;

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

    public void addHighscore(int accountId, int highscore)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE account SET coins = ? WHERE id = ?"))
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
