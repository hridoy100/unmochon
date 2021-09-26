package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDB
{

    private String username;
    private String password;
    //private final String CONN_STRING = "jdbc:oracle:thin:@localhost:1521:xe";
    //private final String CONN_STRING = "jdbc:mysql://localhost:3306/";
    //private final String CONN_STRING = "jdbc:mysql://https://thirdspace.dgp.toronto.edu:3306/";
    private final String CONN_STRING = "jdbc:mysql://localhost:3306/";

    public Connection connection = null;
    //private static OracleDBMS instance = null;

    public MySqlDB()
    {
        this.username = "root";
        this.password = "";
    }

    public MySqlDB(String username, String password)
    {
        this.username = username;
        this.password = password;
//        this.username = "root";
//        this.password = "";
    }

//    public static OracleDBMS getInstance()
//    {
//        if (instance == null)
//        {
//            instance = new OracleDBMS();
//        }
//        return instance;
//    }

    public Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(CONN_STRING, username, password);
                System.out.println("Connection Successful");
            } catch (SQLException e)
            {
                System.out.println("Connection Failed! Check it from console");
                e.printStackTrace();
            }
        }

        return connection;
    }

    public void closeConnection()
    {
        try
        {
            if (connection != null)
            {
                connection.close();
                connection = null;
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
