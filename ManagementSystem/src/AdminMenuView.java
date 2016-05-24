import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by kasdi on 24.05.2016.
 */
public class AdminMenuView {

    GridPane grid;

    //constructor - empty
    public AdminMenuView(){

    }

    public void start(Stage primaryStage, DatabaseAccessObject dbo){

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        BorderPane layout = new BorderPane();
        grid = new GridPane();

        layout.setPadding(new Insets(10, 10, 10, 10));


        Button addTables = new Button("Add Tables");
        Button editItems = new Button("Edit items");


        addTables.setPrefSize(screenSize.getWidth()/3, screenSize.getHeight()/8);
        editItems.setPrefSize(screenSize.getWidth() / 3, screenSize.getHeight() / 8);

        addTables.setOnAction(event -> {
            //Create add table pop up window
        });
        editItems.setOnAction(event -> {
            //Go to edit item view
        });

        grid.add(addTables, 0, 0);
        grid.add(editItems, 0, 1);

        layout.setCenter(grid);

        Scene scene = new Scene(layout, screenSize.getWidth(), screenSize.getHeight() - 32);
        primaryStage.setTitle("Table Layout");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
