import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by BenjaminPapp on 21/05/16.
 */
public class MainMenuView {

    GridPane grid;


    public MainMenuView(){

    }

    public void start(Stage primaryStage){

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        BorderPane layout = new BorderPane();
        grid = new GridPane();


        ArrayList<String> mainMenuItemsTest = new ArrayList<>();

        //TO_DO - creating a test arraylist, have to delete later and reference to a local DB source
        mainMenuItemsTest.add("drinks");
        mainMenuItemsTest.add("Food");
        mainMenuItemsTest.add("test");
        mainMenuItemsTest.add("");
        mainMenuItemsTest.add("vfvf");
        mainMenuItemsTest.add("fdere");

        VBox mainMenuBox = addVerticalMenuBox(mainMenuItemsTest);
        VBox subMenuBox = addVerticalMenuBox(mainMenuItemsTest);
        HBox bottomMenuBox = addHorizontalMenuBox(mainMenuItemsTest);

        layout.setRight(mainMenuBox);
        layout.setCenter(subMenuBox);
        layout.setBottom(bottomMenuBox);


        //initialising the view
        Scene scene = new Scene(layout, screenSize.getWidth(),screenSize.getHeight());
        primaryStage.setTitle("Menu for Table");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public HBox addHorizontalMenuBox(ArrayList<String> menuItems) {
        //getting screen real estate
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        //creating horizontal box
        HBox horizontalMenuBox = new HBox();
        // setting the size of the box
        horizontalMenuBox.setPrefSize(screenSize.getWidth(), (screenSize.getHeight()/8)*1);
        // starting for loop to create button, held by the box as children.
        for (String menuItem : menuItems) {
            //setting the name of the button ---- first in the array will be the first button's name and so on...
            Button button = new Button(menuItem);
            //setting ID of buttons to the string names
            button.setId(menuItem);
            // adding button to the box
            horizontalMenuBox.getChildren().add(button);
            //setting button size
            button.setPrefSize((screenSize.getWidth() / 6), (screenSize.getHeight() / 8) * 1);
        }
        return horizontalMenuBox;
    }

    public VBox addVerticalMenuBox(ArrayList<String> menuItems){
        // vertical box to accommodate buttons with names, whats given at creation.
        // button names to be held in a Arraylist<String> and dynamically created from external source, can ba changed later
        // for anything that lets say that holds objects, so an item can have Name, Price, and so on....
        // number of buttons created by the size of this given String[]

        //getting screen real estate
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        //creating the menu box
        VBox verticalMenuBox = new VBox();
        //setting size width for 2/9 and height for 7/8 - (fraction) - of the whole screen
        //this is gonna be one collum out of the 3 that we have on the main menu.
        verticalMenuBox.setPrefSize((screenSize.getWidth()/9)*2, (screenSize.getHeight()/8)*7);
        // creating button by the number of element found in menuItem.
        for (String menuItem : menuItems) {
            //setting the name of the button ---- first in the array will be the first button's name and so on...
            Button button = new Button(menuItem);
            //setting ID of buttons to the string names
            button.setId(menuItem);
            // adding button to the box
            verticalMenuBox.getChildren().add(button);
            //setting button size
            button.setPrefSize((screenSize.getWidth() / 9) * 2, (screenSize.getHeight() / 8) * 1);
        }
        //setting the "down button"
        Button downButton = new Button("Show More");
        downButton.setStyle("-fx-background-color: yellow");
        downButton.setPrefSize((screenSize.getWidth() / 9) * 2, (screenSize.getHeight() / 8) * 1);
        verticalMenuBox.getChildren().add(6, downButton);
        //returning the ready menuBox.
        return verticalMenuBox;
    }


}