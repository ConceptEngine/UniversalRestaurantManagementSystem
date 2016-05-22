/**
 * Created by kasdi on 21.05.2016.
 */
public class MenuItem {

    private int itemID;
    private int level;
    private int parentID;
    private String name;
    private double price;


    public MenuItem()
    {

    }

    public MenuItem(int itemID, int level, int parentID, String name, double price) {
        this.itemID = itemID;
        this.level = level;
        this.parentID = parentID;
        this.name = name;
        this.price = price;
    }

    //Getters and setters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
