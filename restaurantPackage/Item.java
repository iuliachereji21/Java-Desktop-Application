package restaurantPackage;

import javax.swing.*;
import java.awt.*;
import java.sql.Blob;

/**
 * Item class refers to food (pizza/pasta/salad...)
 * @author  Bucur Alexandra
 */
public class Item {
    private String name;
    private double price;
    private String type;
    private double quantity;
    private String description;
    private int numberItem;
    private Blob photo;

    /**
     * Constructor
     * @param name name of the object from the menu
     * @param price  price of a single object
     * @param type type might be pizza/pasta/salad/burger and so on(type represents those name on the buttons from the main page_
     * @param quantity the quantity of 1 portion of a dish
     * @param description contains ingredients of a certain dish
     * @param img image of the food
     */
    public Item(String name, double price, String type, double quantity, String description, Blob img) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
        this.description = description;
        this.numberItem = 0; //0 items at the beggining of the program
        this.photo = img;
    }

    /**
     * Getters
     * @return a certain field value, depending on the method name
     */
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getNumberItem() {
        return numberItem;
    }
    public String getDescription() {
        return description;
    }

    public double getQuantity() {
        return quantity;
    }

    public Blob getPhoto() {
        return photo;
    }
    //used this to show the item selected and how many pieces of a certain dish
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", numberItem=" + numberItem +
                '}';
    }

    /**
     * methods to increase the value(how many portions of a certain dish)
     * ( ex: from 1 pizza -> 2 pizza)
     */

    protected void increase(){
        this.numberItem++; //increase by 1
    }
    protected void increase(int number) //increase by a certain number
    {
        this.numberItem += number;
    }

    /**
     * Setter
     */
    protected void setNumberItem(){
        this.numberItem = 1;
    }
}
