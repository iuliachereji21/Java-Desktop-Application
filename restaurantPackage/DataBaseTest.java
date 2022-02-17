package restaurantPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class DataBaseTest provides unit testing for the DataBase class
 * @author Chereji Iulia
 */
public class DataBaseTest {
    private DataBase db; //the database on which the tests will be performed
    private int nextId; //next id to be added to database


    @org.junit.Test
    public void getCustomers()
    {
        ResultSet rs = db.getCustomers();
        assert rs!=null: "Result Set returned by getCustomers is null";
    }

    @org.junit.Test
    public void introduceNewCustomerAccount() {
        Customer c1 = new Customer(nextId,"Maria Rotar", null, null, "mr@yahoo.com", "0000", null);
        nextId++;
        db.introduceNewCustomerAccount(c1);

        Customer c2=getLastCustomer();
        assert c2!=null: "introduceNewCustomerAccount Failed";

        assert equalCustomers(c1,c2): "introduceNewCustomerAccount Failed";
    }

    @org.junit.Test
    public void updateCustomerAccount() {
        Customer c1 = new Customer(nextId,"Ana Rotar", null, null, "ar@yahoo.com", "1111", null);
        nextId++;
        db.introduceNewCustomerAccount(c1);
        Customer c2 = new Customer(c1.getIdCustomer(), "Lorena Rotar", null, null, "lr@yahoo.com", "aaaa", null);
        db.updateCustomerAccount(c2);

        Customer c3 = getLastCustomer();
        assert c3!=null: "updateCustomerAccount Failed";

        assert !equalCustomers(c1,c3): "updateCustomerAccount Failed";
    }


    @org.junit.Test
    public void getFood() {
        ResultSet rs = db.getFood("pizza");
        assert rs!=null : "getFood(pizza) Failed";

        rs=db.getFood("pasta");
        assert rs!=null : "getFood(pasta) Failed";

        rs=db.getFood("burger");
        assert rs!=null : "getFood(burger) Failed";

        rs=db.getFood("salad");
        assert rs!=null : "getFood(salad) Failed";

        rs=db.getFood("warm_dishes");
        assert rs!=null : "getFood(warm_dishes) Failed";

        rs=db.getFood("drinks");
        assert rs!=null : "getFood(drinks) Failed";

        rs=db.getFood("dessert");
        assert rs!=null : "getFood(dessert) Failed";
    }


    public DataBaseTest(int nextId)
    {
        db= new DataBase();
        this.nextId=nextId;
    }

    public static void main(String[] args)
    {
        boolean performTesting=false; //set to true if you want to run the test

        if(performTesting)
        {
            DataBaseTest dbTest = new DataBaseTest(12); //modify after every test or delete modifications from database!!!!!!!!!!!!
            dbTest.getCustomers();
            dbTest.introduceNewCustomerAccount();
            dbTest.updateCustomerAccount();
            dbTest.getFood();
        }
    }

    //returns the last customer in the database
    private Customer getLastCustomer()
    {
        ResultSet rs= db.getCustomers();
        if(rs!=null)
        {
            int id;
            String customerName, address, phone, email, password, dateOfBirth;
            Customer customer=null;
            try{
                while(rs.next()) //there is more data to read
                {
                    id=rs.getInt(1);
                    customerName=rs.getString(2);
                    address=rs.getString(3);
                    phone=rs.getString(4);
                    email=rs.getString(5);
                    password=rs.getString(6);
                    dateOfBirth=rs.getString(7);
                    customer=new Customer(id,customerName,address,phone,email,password, dateOfBirth);
                }
                return customer;
            }
            catch (SQLException exception)
            {
                return null;
            }
        }
        else return null;
    }

    //compares the data between two customers
    private boolean equalCustomers(Customer c1, Customer c2)
    {
        if(c1.getIdCustomer()!=c2.getIdCustomer()) return false;
        if(c1.getCustomerName().compareTo(c2.getCustomerName())!=0) return false;
        if(c1.getPassword().compareTo(c2.getPassword())!=0) return false;
        if(c1.getEmail().compareTo(c2.getEmail())!=0) return false;

        if(c1.getDateOfBirth()==null && c2.getDateOfBirth()!= null) return false;
        if(c1.getDateOfBirth()!=null && c2.getDateOfBirth()== null) return false;
        if(c1.getDateOfBirth()!=null && c2.getDateOfBirth()!= null && (c1.getDateOfBirth().compareTo(c2.getDateOfBirth())!=0)) return false;

        if(c1.getPhone()==null && c2.getPhone()!= null) return false;
        if(c1.getPhone()!=null && c2.getPhone()== null) return false;
        if(c1.getPhone()!=null && c2.getPhone()!= null && (c1.getPhone().compareTo(c2.getPhone())!=0)) return false;

        if(c1.getAddress()==null && c2.getAddress()!= null) return false;
        if(c1.getAddress()!=null && c2.getAddress()== null) return false;
        if(c1.getAddress()!=null && c2.getAddress()!= null && (c1.getAddress().compareTo(c2.getAddress())!=0)) return false;

        return true;
    }
}