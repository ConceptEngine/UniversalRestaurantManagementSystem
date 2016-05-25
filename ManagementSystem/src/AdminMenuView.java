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
        //getting sxcreen real estate
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        //creating layout and grid
        BorderPane layout = new BorderPane();
        grid = new GridPane();
        //padding for layout
        layout.setPadding(new Insets(10, 10, 10, 10));

        //create buttons
        Button addTables = new Button("Add Tables");
        Button editItems = new Button("Edit items");
        Button endButton = new Button("END");

        //color style for end button
        endButton.setStyle("-fx-background-color: red");
        //set button sizes
        addTables.setPrefSize(screenSize.getWidth()/3, screenSize.getHeight()/8);
        editItems.setPrefSize(screenSize.getWidth()/3, screenSize.getHeight()/8);
        endButton.setPrefSize(screenSize.getWidth()/3, screenSize.getHeight()/8);
        //set buttons on action
        addTables.setOnAction(event -> {
            //Create add table pop up window
        });
        editItems.setOnAction(event -> {
            //Go to edit item view
        });
        endButton.setOnAction(event -> {
            TableLayoutView tableLayout = new TableLayoutView();
            tableLayout.start(primaryStage, dbo);
        });
        //add butttons to the grid
        grid.add(addTables, 0, 0);
        grid.add(editItems, 0, 1);
        grid.add(endButton, 0, 2); //later set it to bottom right corner on the grid
        //set grid to align in center
        layout.setCenter(grid);
        //set the primary view
        Scene scene = new Scene(layout, screenSize.getWidth(), screenSize.getHeight() - 32);
        primaryStage.setTitle("Table Layout");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
