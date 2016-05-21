import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by BenjaminPapp on 21/05/16.
 */
public class Item {

    private String name;
    private double price;
    private String timeOfCreation;
    private String type;

    //constructor
    public Item(String name, double price, String type){
        this.name = name;
        this.price = price;
        this.type = type;
        timeOfCreation();

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
