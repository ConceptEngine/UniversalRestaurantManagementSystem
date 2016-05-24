import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by BenjaminPapp on 21/05/16.
 */
public class Item {

    private int id;
    private String name;
    private Double price;
    private String timeOfCreation;
    private String type;
    private String comment;
    private int quantity;
    private double totalPrice;

    //constructor
    public Item(int id, String name, Double price, String type, int quantity, String comment){
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        timeOfCreation();
        this.quantity = quantity;
        this.comment = comment;
        totalPrice = 0;
        calculateTotalPrice();
    }
    public Item(int id, String name, Double price, String type, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        timeOfCreation();
        this.quantity = quantity;
        this.comment = "";
        totalPrice = 0;
        calculateTotalPrice();
    }

    public String timeOfCreation(){
        Date date = GregorianCalendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        timeOfCreation = sdf.format(date);
        return timeOfCreation;
    }
    //setter and getter methods
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        calculateTotalPrice();
    }

    public String getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Date newDate) {
        Date date = newDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        timeOfCreation = sdf.format(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotalPrice();
    }

    public void calculateTotalPrice()
    {
        totalPrice = price * quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setID(int id) { this.id = id; }
    public int getID() { return id; }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
