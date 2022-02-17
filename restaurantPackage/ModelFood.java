package restaurantPackage;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * this class will manage all operations for showing the food on the screen
 * there will be different pages for each type of food
 * @author Bucur Alexandra
 **/

public class ModelFood {
    private final ArrayList<Item> menuItems;
    private final int nrItemsOnPage;
    private Item currentItem;
    private final DataBase db;
    private String typeFood;

    /**
     * Constructor
     * @param database the database from which the data is collected
     * @param type type can be 'pizza' / 'pasta' ... => shows the type of food we are dealing with
     * will declare an arrayList of the items to be shown on screen. They will be taken from a database
     * nrItemsOnPage will store how many items of 1 kind are in the database
     **/
    public ModelFood(DataBase database, String type)
    {
        ResultSet rsFood = database.getFood(type);
        menuItems = new ArrayList<>();
        nrItemsOnPage = addFoodInMenu(rsFood, type);
        db = database;
        typeFood = type;
    }

    /**
     * @param rs is an index that shows the current position in the database
     * @param type tells which type of food it is
     * @return the number of items to be shown on the current page
     * Method that will put into an array all items that are of the same type
     */
    protected int addFoodInMenu(ResultSet rs, String type)
    {
        String name, description;
        Double price,quantity;
        Blob photo;

        try
        {
            while(rs.next())
            {
                name=rs.getString(2);
                description=rs.getString(3);
                price=rs.getDouble(4);
                quantity=rs.getDouble(5);
                photo = rs.getBlob(6);
                menuItems.add(new Item(name,price,type,quantity,description, photo));
            }
        }
        catch (SQLException exception)
        {
            System.out.println("SQL error try to get food");
            return menuItems.size();
        }
        return menuItems.size();
    }
}
