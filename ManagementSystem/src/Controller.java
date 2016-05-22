/**
 * Created by kasdi on 19.05.2016.
 */
import javafx.application.Application;
import javafx.stage.Stage;




public class Controller extends Application{
    //I'm improvising
    DatabaseAccessObject dbo = new DatabaseObject();

    //Sets up and starts the application
    public static void main(String[] args) {
        launch(args);
    }


    //@Override
    public void start(Stage primaryStage)
    {
        dbo.ConnectToDatabase();

        //Creates the first view class
        LoginView loginMenu = new LoginView();
        loginMenu.start(primaryStage, dbo);

    }


}
