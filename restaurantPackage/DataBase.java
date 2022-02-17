package restaurantPackage;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class DataBase used to work with the data
 * @author Chereji Iulia and Bucur Alexandra
 */
public class DataBase
{
    private String url;
    private String user;
    private String password;
    private Connection connection;


    /**
     * Constructor.
     * Creates the connection.
     * @author Chereji Iulia
     */
    public DataBase()
    {
        url = "jdbc:mysql://localhost:3306/restaurant_database";
        user = "root";
        password = "Wfaqa3wundz52*";
        connection = null;

        try{
            connection = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException exception) {

            System.out.println("An error occurred while connecting SQLite databse");
            exception.printStackTrace();
        }
    }

    /**
     * @return a ResultSet object containing the customers from database
     * @author Chereji Iulia
     */
    public ResultSet getCustomers()
    {
        ResultSet rs = null;
        try
        {
            Statement st=connection.createStatement();
            rs = st.executeQuery("SELECT * from restaurant_database.customer");
        }
        catch (SQLException exception)
        {
            System.out.println("SQL error");
        }
        finally {
            return rs;
        }
    }

    /**
     * Introduces a new customer into the database
     * @param customer Customer object, whose data to be introduce, not null
     * @author Chereji Iulia
     */
    public void introduceNewCustomerAccount(Customer customer)
    {
        try
        {
            Statement st = connection.createStatement();
            String statement = new String("INSERT INTO restaurant_database.customer (id_customer, customer_name");
            if(customer.getAddress()!=null)
                statement = statement + ", address";
            if(customer.getPhone()!=null)
                statement=statement + ", phone";
            statement=statement+", email, password";
            if(customer.getDateOfBirth()!=null)
                statement=statement+", date_of_birth";
            statement=statement+") VALUES ('"+customer.getIdCustomer()+"', '"+customer.getCustomerName()+"'";
            if(customer.getAddress()!=null)
                statement=statement+", '"+customer.getAddress()+"'";
            if(customer.getPhone()!=null)
                statement=statement + ", '"+customer.getPhone()+"'";
            statement=statement+ ", '"+customer.getEmail()+"', '"+customer.getPassword()+"'";
            if(customer.getDateOfBirth()!=null)
                statement=statement+", '"+customer.getDateOfBirth()+"'";
            statement=statement+");";

            st.executeUpdate(statement);
        }
        catch (SQLException e)
        {
            System.out.println("SQL problem when trying to insert new customer");
        }
    }

    /**
     * Updates the data of customer in database
     * @param customer Customer object whose data to be updated, not null, index in the database
     * @author Chereji Iulia
     */
    public void updateCustomerAccount(Customer customer)
    {
        try
        {
            Statement st = connection.createStatement();
            String statement = new String("update restaurant_database.customer set customer_name = '"+customer.getCustomerName()+"'");

            if(customer.getAddress()!=null)
                statement = statement + ", address= '"+customer.getAddress() +"'";
            else statement = statement + ", address = NULL";

            if(customer.getPhone()!=null)
                statement=statement + ", phone= '" + customer.getPhone()+"'";
            else statement=statement + ", phone= NULL";

            statement=statement+", email= '" + customer.getEmail()+"', password= '"+ customer.getPassword()+"'";

            if(customer.getDateOfBirth()!=null)
                statement=statement+", date_of_birth= '"+customer.getDateOfBirth()+"'";
            else statement=statement+", date_of_birth= NULL";

            statement= statement + " where id_customer = "+customer.getIdCustomer();

            st.executeUpdate(statement);
        }
        catch (SQLException e)
        {
            System.out.println("SQL problem when trying to insert new customer");
        }
    }


    public ResultSet getPizza()
    {
        ResultSet rs = null;
        try
        {
            Statement st=connection.createStatement();
            rs = st.executeQuery("SELECT item.* " +
                    "FROM restaurant_database.item , restaurant_database.pizza " +
                    "where item.id_item = pizza.id_pizza");
        }
        catch (SQLException exception)
        {
            System.out.println("SQL error getting data from pizza table");
        }
        finally {
            return rs;
        }
    }
    public ResultSet getPasta()
    {
        ResultSet rs = null;
        try
        {
            Statement st=connection.createStatement();
            rs = st.executeQuery("SELECT item.* " +
                    "FROM restaurant_database.item , restaurant_database.pasta " +
                    "where item.id_item = pasta.id_pasta");
        }
        catch (SQLException exception)
        {
            System.out.println("SQL error getting data from pasta table");
        }
        finally {
            return rs;
        }
    }
    public ResultSet getSalad()
    {
        ResultSet rs = null;
        try
        {
            Statement st=connection.createStatement();
            rs = st.executeQuery("SELECT item.* " +
                    "FROM restaurant_database.item , restaurant_database.salad " +
                    "where item.id_item = salad.id_salad");
        }
        catch (SQLException exception)
        {
            System.out.println("SQL error getting data from salad table");
        }
        finally {
            return rs;
        }
    }
    public ResultSet getBurger()
    {
        ResultSet rs = null;
        try
        {
            Statement st=connection.createStatement();
            rs = st.executeQuery("SELECT item.* " +
                    "FROM restaurant_database.item , restaurant_database.burger " +
                    "where item.id_item = burger.id_burger");
        }
        catch (SQLException exception)
        {
            System.out.println("SQL error getting data from burger table");
        }
        finally {
            return rs;
        }
    }
    public ResultSet getDessert()
    {
        ResultSet rs = null;
        try
        {
            Statement st=connection.createStatement();
            rs = st.executeQuery("SELECT item.* " +
                    "FROM restaurant_database.item , restaurant_database.dessert " +
                    "where item.id_item = dessert.id_dessert");
        }
        catch (SQLException exception)
        {
            System.out.println("SQL error getting data from dessert table");
        }
        finally {
            return rs;
        }
    }
    public ResultSet getDrink()
    {
        ResultSet rs = null;
        try
        {
            Statement st=connection.createStatement();
            rs = st.executeQuery("SELECT item.* " +
                    "FROM restaurant_database.item , restaurant_database.drinks " +
                    "where item.id_item = drinks.id_drinks");
        }
        catch (SQLException exception)
        {
            System.out.println("SQL error getting data from drinks table");
        }
        finally {
            return rs;
        }
    }
    public ResultSet getWarmDish()
    {
        ResultSet rs = null;
        try
        {
            Statement st=connection.createStatement();
            rs = st.executeQuery("SELECT item.* " +
                    "FROM restaurant_database.item , restaurant_database.warm_dish " +
                    "where item.id_item = warm_dish.id_warm_dish");
        }
        catch (SQLException exception)
        {
            System.out.println("SQL error getting data from warm_dish table");
        }
        finally {
            return rs;
        }
    }
    public ResultSet getFood(String type){
        if(type.equals("pizza"))
            return getPizza();
        if(type.equals("pasta"))
            return getPasta();
        if(type.equals("burger"))
            return getBurger();
        if(type.equals("salad"))
            return getSalad();
        if(type.equals("warm_dishes"))
            return getWarmDish();
        if(type.equals("drinks"))
            return getDrink();
        if(type.equals("dessert"))
            return getDessert();
        return null;
    }

}



