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

    public ArrayList<MenuItem> getMenuItems()
    {
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        try {
            String sql = "SELECT * FROM item";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //Add the current row to the arraylist
                MenuItem currentItem = new MenuItem(resultSet.getInt(1),
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

    public void InsertMenuItem(MenuItem anItem)
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

    public boolean isTableOpen(int tableID)
    {
        try {
            String sql = "SELECT * FROM tables WHERE tableID  = '" + tableID + "'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //Add the current row to the arraylist
                System.out.println("This table is " + resultSet.getInt(2));
                if (resultSet.getInt(2) == 1)
                    return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void setTableState(int tableID, int state)
    {
        String sql="UPDATE tables SET isOpen = " + state + " WHERE tableID = '" + tableID + "'";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int numberOfRows = preparedStatement.executeUpdate();
            System.out.println("Completed update. Number of rows affected:" + numberOfRows);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> getReceiptItems(int tableID)
    {
        int startID = 0;
        ArrayList<Item> receiptItems = new ArrayList<>();

        try {
        String sql="SELECT * FROM receiptItem WHERE tableID  = '" + tableID + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            //Add the current row to the arraylist
            System.out.println("This table is " + resultSet.getString(3));

            Item item = new Item(startID, resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(7));
            startID++;
            receiptItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return receiptItems;
    }

    public void deleteAllTableReceiptItems(int tableID)
    {
        String sql = "DELETE FROM receiptItem WHERE tableID  = '" + tableID + "'";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int numberOfRows = preparedStatement.executeUpdate();
            System.out.println("Completed delete. Number of rows affected:" + numberOfRows);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void saveReceiptItem(Item item, int tableID)
    {
        String sql="INSERT INTO receiptItem VALUES (?, ?, ?, ?, ?, ?, ?)\n";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, item.getID());
            preparedStatement.setInt(2, tableID);
            preparedStatement.setString(3, item.getName());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.setString(5, item.getType());
            preparedStatement.setInt(6, item.getQuantity());
            preparedStatement.setString(7, item.getComment());

            int numberOfRows= preparedStatement.executeUpdate();
            System.out.println("Completed insert. Number of rows affected:" + numberOfRows);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}