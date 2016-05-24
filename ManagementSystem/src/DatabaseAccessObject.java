import java.util.ArrayList;

/**
 * Created by kasdi on 19.05.2016.
 */
public interface DatabaseAccessObject {

        public void ConnectToDatabase();

        public int getLoginAccessLevel(String password);
        public ArrayList<MenuItem> getMenuItems();
        public void InsertMenuItem(MenuItem anItem);
        public boolean isTableOpen(int tableID);
        public void setTableState(int tableID, int state);
        public ArrayList<Item> getReceiptItems(int tableID);
        public void deleteAllTableReceiptItems(int tableID);
        public void saveReceiptItem(Item item, int tableID);
}