import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by kasdi on 19.05.2016.
 */

//This is the view class for the login screen.
//In the login screen, the user will be able to enter his/her pin code.
public class LoginView {

    //Variables
    GridPane grid;      //The gridpane, which will hold the pin number buttons and text field
    TextField pinCode = new TextField();      //Field which displays the entered pin code
    String currentPinCode = "";               //Will contain the pin code

    public LoginView()
    {

    }

    public void start(Stage primaryStage, DatabaseAccessObject dbo)
    {
        //Get window size, so we can calculate other stuff later
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        BorderPane layout = new BorderPane();   //Make the layout
        grid = new GridPane();                  //Grid pane to hold all the JavFX elements
        //Set the login grid at the exact center of the screen
        layout.setPadding(new Insets(screenSize.getHeight()/2 - 125, 10, 10, screenSize.getWidth()/2 - 150));
        //Create the field which contains the pin code, it's not editable
        pinCode = new TextField("");
        pinCode.setEditable(false);

        // setting keyboard for listening to all chars and DELETe and ENTER buttons
        pinCode.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //if we press "delet" button on keyboard
                //this doesnt work for me some reason ----- TO_DO
                if (event.getCode() == KeyCode.BACK_SPACE){
                    currentPinCode = currentPinCode.substring(0, currentPinCode.length()-1);
                    pinCode.setText(currentPinCode);
                    System.out.println("delete pressed");
                }
                //if we press "enter" button on keyboard
                if (event.getCode() == KeyCode.ENTER){
                    if (dbo.tryToLogin(pinCode.getText()) == true) {
                        //primaryStage.hide();
                        TableLayoutView tableLayoutView = new TableLayoutView();
                        tableLayoutView.start(primaryStage, dbo);
                        System.out.println("enter pressed");
                        //Try to login
                        //Add code here....
                    }
                    else {
                        currentPinCode = "invalid pin, try again!";
                        pinCode.setText(currentPinCode);
                    }
                }
                currentPinCode += event.getText();
                pinCode.setText(currentPinCode);
            }
        });

        //Add 12 buttons to the pin code screen.
        //10 buttons for numbers, one for entering, and one for deleting.
        for (int i = 0; i < 12; i++) {
            //Create a new button
            Button button = new Button(Integer.toString(i+1));
            //Check if it's a special button (entering or deleting)
            if (i == 9)
            {
                button.setText("Delete");
            }
            if (i == 10)
            {
                button.setText("0");
            }
            if (i == 11)
            {
                button.setText("Login");
            }
            //Set button sizes
            button.setId(Integer.toString(i+1));
            button.setMinHeight(55);
            button.setMinWidth(100);
            button.setMaxWidth(100);
            //Add functionality on button press
            button.setOnAction(event -> {
                //Button is pressed, add the number to the pinCode field
                if (Integer.parseInt(button.getId()) < 10) {
                    //Then it contains a number
                    currentPinCode += button.getId();
                    pinCode.setText(currentPinCode);
                } else if (Integer.parseInt(button.getId()) == 10) {
                    //Delete last character
                    if (currentPinCode.length() > 0) {
                        currentPinCode = currentPinCode.substring(0, currentPinCode.length()-1);
                        pinCode.setText(currentPinCode);
                    }
                } else if (Integer.parseInt(button.getId()) == 11) {
                    //Add a zero
                    currentPinCode += "0";
                    pinCode.setText(currentPinCode);
                } else {
                    if (dbo.tryToLogin(pinCode.getText()) == true) {
                        //primaryStage.hide();
                        TableLayoutView tableLayoutView = new TableLayoutView();
                        tableLayoutView.start(primaryStage, dbo);
                        //Try to login
                        //Add code here....
                    }
                }
            });
            //Add the button to the grid
            grid.add(button, i - (i / 3) * 3, i / 3 + 1);
        }
        //Add the pin code field at the top, taking up 3 columns
        grid.add(pinCode, 0, 0, 3, 1);
        //Set the pin code grid as the center element
        layout.setCenter(grid);

        //Make the window and set the layout
        Scene scene = new Scene(layout, screenSize.getWidth(), screenSize.getHeight() - 32);
        primaryStage.setTitle("Login Menu");
        primaryStage.setScene(scene);
        primaryStage.show();

        // setting focus on pinField for quick type in on keyboard.
        // it has to be set after setting the scene.
        pinCode.requestFocus();

    }
}
