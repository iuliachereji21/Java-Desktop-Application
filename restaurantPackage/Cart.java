package restaurantPackage;

import javax.swing.*;
import java.sql.Blob;
import java.util.ArrayList;

/**
 * @author Alexandra Bucur
 * Class that deals with Cart methods such as add food/ remove food
 * some methods from here are not used anymore because at the begining the project was just a console app,
 * so they were used there to check if methods are correct
 * ex=> GetTotal method was used to calculate the total of the cart
 */
public class Cart {
    private ArrayList<Item> items;
    private double totalPrice;

    /**
     * Constructor sets final price to 0 and creates a list of objects
     */
    public Cart() {
        this.totalPrice = 0;
        this.items = new ArrayList<Item>();
    }

    /** Method
     * @param (name price  type  quantity description photo) describe the item to be added
     * @return if the item was or not successfully added to the list
     */
    public boolean addItem(String name, double price, String type, double quantity, String description, Blob photo) {
        return addIt(new Item( name,  price,  type,  quantity,  description, photo));
    }

    private boolean addIt(Item item) {
        Item check = finditem(item.getName()); //sees if the item already exists in list
        if(check != null){ //if yes => more 'pieces' of the same item (ex: 2 pizza peperoni)
            check.increase();
            this.totalPrice += check.getPrice();
            return true;
        }
        else {  //if not add to the list
            item.setNumberItem();
            this.totalPrice += item.getPrice();
            this.items.add(item);
            return true;
        }
    }

    /**
     * Method, only for internal class functions
     * @param name name of the item to be searched
     * @return true/false depending if the item was found or not
     */
    private Item finditem(String name) {
        for(Item checkeditem: this.items) {
            if(checkeditem.getName().equals(name)) {
                return checkeditem;
            }
        }
        return null;
    }

    /**
     * Method
     * @return total price of the cart
     */
    public double getTotal(){
        this.totalPrice = 0;
        for(Item item: this.items){
            this.totalPrice +=  item.getNumberItem()*item.getPrice();
        }
        return totalPrice;
    }

    /**
     * Method
     * shows the items in the list
     */
    public void showCart(){

        for(Item it:items){
            System.out.println(it.toString());
        }
        System.out.println();
        System.out.println("Total price is: " + this.totalPrice);
    }

    /**
     *
     * @param (name price type quantity description photo ) paramt of the item
     * @return true/false => if the item was removed from the list
     */
    public boolean removeItem(String name, double price, String type, double quantity, String description, Blob photo){
        return removeIt(new Item( name,  price,  type,  quantity,  description, photo));
    }
    private boolean removeIt(Item item) {
        Item check = finditem(item.getName());
        if(check != null){
            this.totalPrice -= check.getNumberItem()*check.getPrice();
            items.remove(check);
        }
        return true;
    }
    /**
     *  this method will increases the number of products of the same item if
     *  the customer wants to by variable howMuch
     *  */
    public boolean moreItem(String name, int howMuch){
        Item aux = finditem(name);
        if (aux != null){
            aux.increase(howMuch);
            this.totalPrice += howMuch * aux.getPrice();
            return true;
        }
        else {
            System.out.println("Item does not exist in cart");
            return false;
        }
    }
    /**
     * this method will decrease the number of products of the same
     * item if the customer wants to by howMuch variable
     */
    public boolean lessItem(String name, int howMuch){
        Item aux = finditem(name);
        if (aux != null){
            if(aux.getNumberItem() > howMuch){
                aux.increase(-howMuch);
                this.totalPrice-= howMuch* aux.getPrice();
                return true;
            }
            else{
                if(aux.getNumberItem() == howMuch) {
                    return removeItem(aux.getName(),aux.getPrice(),aux.getType(),aux.getQuantity(), aux.getDescription(), aux.getPhoto());
                }
                else {
                    System.out.println("Cart does not contain so many items like that");
                    return false;
                }
            }
        }
        else {
            System.out.println("Item does not exist in cart");
            return false;
        }
    }
}