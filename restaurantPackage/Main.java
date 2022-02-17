package restaurantPackage;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Main extends JFrame{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected int screenHeight = (int) screenSize.getHeight();
    protected int screenWidth = (int) screenSize.getWidth();
    public static DataBase dataBase = new DataBase();

    public static HashMap<String, Integer> cart = new HashMap<>();
    public static HashMap<String, String> idName = new HashMap<>();
    public static HashMap<String, Float> idPrice = new HashMap<>();
    public static View[] views;

    public Main(String title)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,screenWidth,screenHeight);
    }
    public static void main(String[] args) {

        //get the screen dimensions to ensure that the views fit the screen


        Main mainReference = new Main("Gala Lunch");
        //the database class which handles the work with MySQL Workbench
        //DataBase database = new DataBase();

        //the model which handles the customer related operations
        ModelCustomer modelCustomer = new ModelCustomer(dataBase);
        ModelFood modelFood = new ModelFood(dataBase,"pizza");

        //an array of view, which are panels, to be added to the layered panes
        views= new View[14];
        views[0]=new View1("main page", mainReference); //this is a panel
        views[1]=new ViewFood("pizza", mainReference);
        views[2]=new ViewFood("pasta", mainReference);
        views[3]=new ViewFood("salad", mainReference);
        views[4]=new ViewFood("burger", mainReference);
        views[5]=new ViewFood("warm_dishes", mainReference);
        views[6]=new ViewFood("dessert", mainReference);
        views[7]=new ViewFood("drinks", mainReference);
        views[8]=new ViewFood("menu of the day", mainReference);
        views[9]=new ViewLogIn("login", mainReference);
        views[10]=new ViewSignUp("sign up", mainReference);
        views[11]=new ViewAccount("account", mainReference);
        views[12]=new ViewCart("Cart", mainReference);
        views[13]=new ViewCheckOut("Order", mainReference);

        mainReference.setContentPane(views[0].getPanel());
        mainReference.setLayout(null);
        views[0].setVis(true);
        mainReference.setVisible(true);

        //the controller which manages the changes in the view
        Controller c1 = new Controller(views, modelCustomer, modelFood, mainReference); //CONTROLLER****************************************************
    }

    public static DataBase getDatabase() {
        return dataBase;
    }
}
