import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by BenjaminPapp on 21/05/16.
 */
public class TableLayoutView {

    GridPane grid;

    //constructor - empty
    public TableLayoutView(){

    }

    public void start(Stage primaryStage, DatabaseAccessObject dbo){

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        BorderPane layout = new BorderPane();
        grid = new GridPane();

        layout.setPadding(new Insets(10, 10, 10, 10));

        for (int i = 0; i < 24; i++){

            Button button = new Button(Integer.toString(i + 1));

            if (i == 23){
                button.setText("END");
                button.setStyle("-fx-background-color: red");
            }

            button.setId(Integer.toString(i+1));
            button.setPrefSize(screenSize.getWidth()/3, screenSize.getHeight()/8);

            button.setOnAction(event -> {
                if (button.getText().equals("END")){

                    primaryStage.hide();
                }

                if (button.getText().equals("1")){

                    MainMenuView mainMenuView = new MainMenuView();
                    mainMenuView.start(primaryStage, dbo, 1);
                }
                // code here for assigning to next TableView with the main menu.
            });

            grid.add(button, i - (i/3) * 3, i / 3 + 1);
        }

        layout.setCenter(grid);

        Scene scene = new Scene(layout, screenSize.getWidth(), screenSize.getHeight() - 32);
        primaryStage.setTitle("Table Layout");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
