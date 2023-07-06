package dinojump.manager;

import dinojump.util.Account;
import dinojump.util.Skin;
import org.mariadb.jdbc.MariaDbPoolDataSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager
{
    private static DatabaseManager instance;

    private final MariaDbPoolDataSource dataSource = new MariaDbPoolDataSource();

    private DatabaseManager()
    {
    }

    public static DatabaseManager getInstance()
    {
        if (instance == null)
        {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /**
     * Initializes the data source with the specific url, user and password, tests the connection to the dinojump.database<br>
     * and executes the initialization SQL script
     *
     * @param url      url to the dinojump.database (e. g. <code>"jdbc:mariadb://[host]:[port]/[dinojump.database]</code>
     * @param user     username for accessing the dinojump.database
     * @param password user credentials for accessing the dinojump.database
     */
    public void initialize(String url, String user, String password)
    {
        try
        {
            // configuring the data source, be advised: password & user have to be set before setting the url
            dataSource.setPassword(password);
            dataSource.setUser(user);
            dataSource.setUrl(url);

            System.out.println("Successfully connected to dinojump.database");

            executeSql(new File("./resources/sql/initialize_db.sql")); // executes the initialization script for first setup
            initializeAccount();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void initializeAccount()
    {
        String macAddress = getMacAddress();
        int highscore = getHighscore(macAddress);
        Account.getInstance().setMacAddress(macAddress);
        Account.getInstance().setHighscore(highscore);
        if (highscore == -1)
        {
            Account.getInstance().setHighscore(0);
            addAccount(Account.getInstance());
        }
    }

    public int getHighscore(String macAddress)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT highscore FROM account WHERE mac_address = ?"))
        {
            // sets the parameter for the query
            statement.setString(1, macAddress);

            ResultSet resultSet = statement.executeQuery(); // executes the query and retrieves a result set
            if (!resultSet.next()) // go to first row
            {
                return -1;
            }

            // retrieves all attributes from the dinojump.database and returns the data
            return resultSet.getInt("highscore");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0; // return zero if an error occurred
        }
    }

    public void updateHighscore(String macAddress, int highscore)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE account SET highscore = ? WHERE mac_address = ?"))
        {
            // sets the parameters for the update
            statement.setInt(1, highscore);
            statement.setString(2, macAddress);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account)
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO account (mac_address, highscore) VALUES (?, ?)"))
        {
            // sets the parameters for the update
            statement.setString(1, account.getMacAddress());
            statement.setInt(2, account.getHighscore());

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Skin> getSkins()
    {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM skin"))
        {
            List<Skin> skins = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(); // executes the query and retrieves the result set
            while (resultSet.next())
            {
                skins.add(Skin.of(resultSet.getInt("id")));
            }
            return skins;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null; // return null if an error occurred
        }
    }

    /**
     * Retrieves the mac address of the current localhost
     *
     * @return mac address of the current localhost
     */
    public String getMacAddress()
    {
        byte[] macAddressBytes;
        try
        {
            InetAddress localhost = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localhost);
            macAddressBytes = networkInterface.getHardwareAddress();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        String[] hexadecimal = new String[macAddressBytes.length];
        for (int i = 0; i < macAddressBytes.length; i++)
        {
            hexadecimal[i] = String.format("%02X", macAddressBytes[i]);
        }
        return String.join(":", hexadecimal);
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
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            reader.lines().forEach(builder::append); // sums the whole content of the file up
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (String sqlCommand : builder.toString().split(";"))
        {
            executeSql(sqlCommand);
        }
    }
}
