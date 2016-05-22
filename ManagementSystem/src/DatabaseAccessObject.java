import java.util.ArrayList;

/**
 * Created by kasdi on 19.05.2016.
 */
public interface DatabaseAccessObject {

        public void ConnectToDatabase();

        public boolean tryToLogin(String password);
        public ArrayList<MenuItem> getMenuItems();
        public void InsertMenuItem(MenuItem anItem);

}