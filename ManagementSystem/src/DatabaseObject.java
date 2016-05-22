import java.sql.*;
import java.util.ArrayList;

/**
 * Created by kasdi on 19.05.2016.
 */
public class DatabaseObject implements DatabaseAccessObject {

    private Connection connection = null;

    //Creates a connection to the database
    public void ConnectToDatabase() {
        try {
            String DB_URL = "jdbc:mysql://85.10.205.173:3306/xpproject";
            String USER = "keauser";
            String PASS = "Passwordpass";
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("conn obj created " + connection + " message: ");


        } catch (SQLException e) {
            System.out.println("db error" + e.getMessage());
        }
    }

    public boolean tryToLogin(String password) {
        try {
            String sql = "SELECT * FROM users WHERE password  = '" + password + "'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<menuItem> getMenuItems()
    {
        ArrayList<menuItem> menuItems = new ArrayList<>();

        try {
            String sql = "SELECT * FROM item";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //Add the current row to the arraylist
                menuItem currentItem = new menuItem(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5)
                );

                menuItems.add(currentItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    public void InsertMenuItem(menuItem anItem)
    {
        String sql="INSERT INTO item VALUES (null, ?, ?, ?, ?)\n";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, anItem.getLevel());
            preparedStatement.setInt(2, anItem.getParentID());
            preparedStatement.setString(3, anItem.getName());
            preparedStatement.setDouble(4, anItem.getPrice());

            int numberOfRows= preparedStatement.executeUpdate();
            System.out.println("Completed insert. Number of rows affected:" + numberOfRows);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}