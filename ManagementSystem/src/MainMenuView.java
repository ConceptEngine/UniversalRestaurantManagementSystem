import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    int[] scrollerLevel = new int[4];
    ArrayList<MenuItem> menuItems;
    int selectedItemLevel1ID = 0;
    int selectedItemLevel2ID = 0;
    ArrayList<Item> receiptItems;
    TableView tableLayoutView;
    Stage primaryStage;
    DatabaseAccessObject dbo;

    final ObservableList<Item> itemsForDisplay = FXCollections.observableArrayList();

    public MainMenuView(){

    }

    public void start(Stage primaryStage, DatabaseAccessObject dbo){
        this.primaryStage = primaryStage;
        this.dbo = dbo;

        menuItems = dbo.getMenuItems();

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        BorderPane layout = new BorderPane();
        grid = new GridPane();


        ArrayList<String> mainMenuItemsTest = new ArrayList<>();


        //TO_DO - creating a test arrayList, have to delete later and reference to a local DB source
        mainMenuItemsTest.add("PAY");
        mainMenuItemsTest.add("PRINT");
        mainMenuItemsTest.add("REMOVE");
        mainMenuItemsTest.add("MESSAGE");
        mainMenuItemsTest.add("SEND");
        mainMenuItemsTest.add("END");

        //VBox mainMenuBox = addVerticalMenuBox(mainMenuItemsTest);
        //VBox subMenuBox = addVerticalMenuBox(mainMenuItemsTest);
        HBox bottomMenuBox = addHorizontalMenuBox(mainMenuItemsTest);
        VBox billBox = addItemDisplay(itemsForDisplay);


        //layout.setRight(mainMenuBox);
        //layout.setCenter(subMenuBox);
        layout.setBottom(bottomMenuBox);
        layout.setLeft(billBox);
        // setting the menu build from right-to-left.
        //layout.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        refreshMenuItemBoxes();
        //initialising the view
        layout.setCenter(grid);

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

            if (menuItem == "END")
            {
                button.setOnAction(event -> {
                    //LoginView loginMenu = new LoginView();
                    TableLayoutView tableLayout = new TableLayoutView();
                    tableLayout.start(primaryStage, dbo);
                });
            }
            if (menuItem == "REMOVE")
            {
                button.setOnAction(event -> {
                    removeSelectedRow();
                });
            }
            // adding button to the box
            horizontalMenuBox.getChildren().add(button);
            //setting button size
            button.setPrefSize((screenSize.getWidth() / 6), (screenSize.getHeight() / 8) * 1);
        }
        return horizontalMenuBox;
    }

    public VBox addVerticalMenuBox(ArrayList<String> menuItems){
        // vertical box to accommodate buttons with names, whats given at creation.
        // button names to be held in a ArrayList<String> and dynamically created from external source, can ba changed later
        // for anything that lets say that holds objects, so an item can have Name, Price, and so on....
        // number of buttons created by the size of this given String[]

        //getting screen real estate
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        //creating the menu box
        VBox verticalMenuBox = new VBox();
        //setting size width for 2/9 and height for 7/8 - (fraction) - of the whole screen
        //this is gonna be one column out of the 3 that we have on the main menu.
        verticalMenuBox.setPrefSize((screenSize.getWidth() / 9) * 2, (screenSize.getHeight() / 8) * 7);
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

    public void refreshMenuItemBoxes()
    {
        //VBox mainMenuBox = addVerticalMenuBox(mainMenuItemsTest);
        grid.getChildren().clear();
        grid.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        VBox firstColumn = addVerticalMenuBox2(menuItems, 1, 0);   //Display first level
        grid.add(firstColumn, 0, 0);

        if (selectedItemLevel1ID != 0)
        {
            //Show second column
            VBox secondColumn = addVerticalMenuBox2(menuItems, 2, selectedItemLevel1ID);   //Display second level
            grid.add(secondColumn, 1,0);

            // if (selectedItemLevel2ID != 0)
            // {
            //Show second column
            VBox thirdColumn = addVerticalMenuBox2(menuItems, 3, selectedItemLevel2ID);   //Display third level
            grid.add(thirdColumn, 2, 0);
            // }
        }

        //  layout.getChildren().removeAll();
        //  layout.setCenter(grid);
        // System.out.println(selectedItemLevel2ID);
    }
    public VBox addVerticalMenuBox2(ArrayList<MenuItem> menuItems, int level, int parentID){
        // vertical box to accommodate buttons with names, whats given at creation.
        // button names to be held in a Arraylist<String> and dynamically created from external source, can ba changed later
        // for anything that lets say that holds objects, so an item can have Name, Price, and so on....
        // number of buttons created by the size of this given String[]
        int maxButtonsInColumn = 4;
        int buttonsToSkip = scrollerLevel[level];
        int addedButtons = 0;
        //getting screen real estate
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        //creating the menu box
        VBox verticalMenuBox = new VBox();

        //setting size width for 2/9 and height for 7/8 - (fraction) - of the whole screen
        //this is gonna be one collum out of the 3 that we have on the main menu.
        verticalMenuBox.setPrefSize((screenSize.getWidth()/9)*2, (screenSize.getHeight()/8)*7);

        Button upButton = new Button("Go up");
        upButton.setOnAction(event -> {
            if (scrollerLevel[level] > 0)
            {
                scrollerLevel[level]--;
                refreshMenuItemBoxes();
            }
        });
        upButton.setStyle("-fx-background-color: yellow");
        upButton.setPrefSize((screenSize.getWidth() / 9) * 2, (screenSize.getHeight() / 32) * 1);
        verticalMenuBox.getChildren().add(upButton);



        // creating button by the number of element found in menuItem.
        for (MenuItem currentItem : menuItems) {
            if (level == 1) {
                if (currentItem.getLevel() == 1 && addedButtons <= maxButtonsInColumn) {
                    if (buttonsToSkip <= 0) {
                        //setting the name of the button ---- first in the array will be the first button's name and so on...
                        Button button = new Button(currentItem.getName());

                        button.setId(Integer.toString(currentItem.getItemID()));
                        button.setOnAction(event -> {
                            selectedItemLevel1ID = Integer.parseInt(button.getId());
                            selectedItemLevel2ID = 0;
                            scrollerLevel[2] = 0;
                            refreshMenuItemBoxes();
                        });

                        if (currentItem.getItemID() == selectedItemLevel1ID) {
                            button.setStyle("-fx-background-color: green");
                        }


                        // adding button to the box
                        verticalMenuBox.getChildren().add(button);
                        //setting button size
                        button.setPrefSize((screenSize.getWidth() / 9) * 2, (screenSize.getHeight() / 8) * 1);
                        addedButtons++;
                    }
                    else
                    {
                        buttonsToSkip--;
                    }
                }
            }
            else
            {
                //We need to search for specific parent ID
                if (level == 2) {
                    //Level 2
                    if (currentItem.getLevel() == 2 && currentItem.getParentID() == selectedItemLevel1ID && addedButtons <= maxButtonsInColumn) {
                        if (buttonsToSkip <= 0) {
                            //setting the name of the button ---- first in the array will be the first button's name and so on...
                            Button button = new Button(currentItem.getName());

                            button.setId(Integer.toString(currentItem.getItemID()));
                            button.setOnAction(event -> {
                                selectedItemLevel2ID = Integer.parseInt(button.getId());
                                scrollerLevel[3] = 0;
                                refreshMenuItemBoxes();
                            });

                            if (currentItem.getItemID() == selectedItemLevel2ID) {
                                button.setStyle("-fx-background-color: green");
                            }

                            // adding button to the box
                            verticalMenuBox.getChildren().add(button);
                            //setting button size
                            button.setPrefSize((screenSize.getWidth() / 9) * 2, (screenSize.getHeight() / 8) * 1);
                            addedButtons++;
                        }
                        else
                        {
                            buttonsToSkip--;
                        }
                    }
                }
                else
                {
                    //Level 3
                    if (currentItem.getLevel() == 3 && currentItem.getParentID() == selectedItemLevel2ID && addedButtons <= maxButtonsInColumn) {
                        if (buttonsToSkip <= 0) {
                            //setting the name of the button ---- first in the array will be the first button's name and so on...
                            Button button = new Button(currentItem.getName());

                            button.setId(Integer.toString(currentItem.getItemID()));
                            button.setOnAction(event -> {
                                ///selectedItemLevel2ID = Integer.parseInt(button.getId());
                                //refreshMenuItemBoxes();
                                //Add it to the receipt
                                boolean isInTheList = false;
                                for (Item checkoutItem : itemsForDisplay){
                                   // tableLayoutView.setItems(itemsForDisplay);
                                    if (checkoutItem.getID() == currentItem.getItemID())
                                    {
                                        isInTheList = true;
                                        checkoutItem.setQuantity(checkoutItem.getQuantity() + 1);
                                        Item replacementItem = new Item(currentItem.getItemID(), currentItem.getName(), currentItem.getPrice(), "getType?", checkoutItem.getQuantity());
                                        //itemsForDisplay.remove(currentItem);
                                        //itemsForDisplay.set(itemsForDisplay.indexOf(currentItem), checkoutItem );
                                        itemsForDisplay.set(itemsForDisplay.indexOf(checkoutItem), replacementItem);

                                    }

                                }
                                if (isInTheList == false)
                                    itemsForDisplay.add(new Item(currentItem.getItemID(), currentItem.getName(), currentItem.getPrice(), "getType?", 1));
                            });

                            // adding button to the box
                            verticalMenuBox.getChildren().add(button);
                            //setting button size
                            button.setPrefSize((screenSize.getWidth() / 9) * 2, (screenSize.getHeight() / 8) * 1);
                            addedButtons++;
                        }
                        else
                        {
                            buttonsToSkip--;
                        }
                    }
                }
            }
        }


        Button downButton = new Button("Go down");
        downButton.setOnAction(event -> {
            //Count how many items left
            //final int buttonsToSkip = scrollerLevel[level];
            int itemsLeft = 0;
            for (MenuItem currentItem : menuItems) {
                if (level == 1) {
                    itemsLeft++;
                }
                else
                {
                    if (currentItem.getLevel() == 2 && currentItem.getParentID() == selectedItemLevel1ID)
                    {
                        itemsLeft++;
                    }
                    else if (currentItem.getLevel() == 3 && currentItem.getParentID() == selectedItemLevel2ID)
                    {
                        itemsLeft++;
                    }
                }
            }

            System.out.println(itemsLeft);
            if ((itemsLeft - scrollerLevel[level]) > 7) {
                scrollerLevel[level]++;
            }
            refreshMenuItemBoxes();
        });
        downButton.setStyle("-fx-background-color: yellow");
        downButton.setPrefSize((screenSize.getWidth() / 9) * 2, (screenSize.getHeight() / 32) * 1);
        verticalMenuBox.getChildren().add(downButton);

        //setting the "down button"
        //Button downButton = new Button("Show More");
        //downButton.setStyle("-fx-background-color: yellow");
        //downButton.setPrefSize((screenSize.getWidth() / 9) * 2, (screenSize.getHeight() / 8) * 1);
        //verticalMenuBox.getChildren().add(downButton);
        return verticalMenuBox;
    }

    public VBox addItemDisplay(ObservableList<Item> items){

        // creating display layout
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        VBox itemDisplay = new VBox();
        itemDisplay.setPrefSize((screenSize.getWidth() / 3), (screenSize.getHeight()));

        //creating tableView to have rows and columns
        tableLayoutView = new TableView(items);
        tableLayoutView.setPrefSize(screenSize.getWidth() / 3, screenSize.getHeight());
        //creating the 3 columns
        TableColumn tableColumn1 = new TableColumn("Quantity");
        TableColumn tableColumn2 = new TableColumn("Item Name");
        TableColumn tableColumn3 = new TableColumn("Total price");

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        //setting column width
        tableColumn1.setMinWidth((itemDisplay.getPrefWidth() / 10) * 1);
        tableColumn2.setMinWidth((itemDisplay.getPrefWidth() / 10) * 6);
        tableColumn3.setMinWidth((itemDisplay.getPrefWidth() / 10) * 3);
        //adding columns to table

        tableLayoutView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3);

        //adding bill view to vbox
        itemDisplay.getChildren().add(tableLayoutView);
        //returning the ready view
        return itemDisplay;
    }

    private void refreshReceiptWindow()
    {
        itemsForDisplay.removeAll();
        for (Item item : receiptItems)
        {
            itemsForDisplay.add(item);
        }
    }

    private void removeSelectedRow()
    {
        Item item = (Item)tableLayoutView.getSelectionModel().getSelectedItem();
        int rowIndex = 0;
        boolean found = false;
        if (itemsForDisplay.size() > 0 && item != null) {
            for (Item checkoutItem : itemsForDisplay) {
                if (checkoutItem.getID() == item.getID()) {
                    found = true;
                    // itemsForDisplay.remove(checkoutItem);     //Cant just remove because causes a lot of errors
                }

                if (found == false)
                    rowIndex++;
            }

            if (found == true)
                itemsForDisplay.remove(rowIndex);
        }
    }

}
