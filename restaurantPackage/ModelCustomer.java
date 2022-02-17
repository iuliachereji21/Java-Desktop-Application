package restaurantPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class ModelCustomer handles the operations needed in order to work with customers.
 * @author Chereji Iulia
 */
public class ModelCustomer {

    private Customer[] customers;
    private int maxNrCustomers;
    private int nrCustomers;
    private Customer currentCustomer;
    private DataBase db;

    /**
     * Constructor.
     * Takes the nr of customers from the DataBase object database, sets the maximum nr of cusromers to 100 and creates the customers
     * @param database DataBase object used to take the data of the customers.
     */
    public ModelCustomer(DataBase database)
    {
        maxNrCustomers=100;
        ResultSet rsCustomers= database.getCustomers();
        customers=new Customer[maxNrCustomers];
        nrCustomers = addCustomers(rsCustomers);
        currentCustomer = new Customer(); //not logged in, id = 0
        db=database;
    }

    /**
     * Takes the data from the ResultSet given and creates customers, and then it adds them to the "customers" array.
     * @param rs ResulSet object of the needed type
     * @return the nr of added customers
     */
    private int addCustomers(ResultSet rs)
    {
        int id;
        String customerName, address, phone, email, password, dateOfBirth;
        int index=0; //position to add the next customer in the array
        try
        {
            if(rs!=null)
                while(rs.next()) //there is more data to read
                {
                    id=rs.getInt(1);
                    customerName=rs.getString(2);
                    address=rs.getString(3);
                    phone=rs.getString(4);
                    email=rs.getString(5);
                    password=rs.getString(6);
                    dateOfBirth=rs.getString(7);
                    customers[index]=new Customer(id,customerName,address,phone,email,password, dateOfBirth);
                    index++;

                }
        }
        catch (SQLException exception)
        {
            System.out.println("SQL error");
            return index;
        }
        return index;
    }

    /**
     * Checks to see if the log in information is correct. If it is, then the currentCustomer is given the reference to that customer
     * @param email String, the email typed in by the user.
     * @param password String, the password typed in by the user.
     * @return 0 if the log in information is INCORECT, or the id of the customer which mathed the data if CORET
     */
    public int checkLogIn(String email, String password)
    {
        if(email==null || password==null)
            return 0;
        for(int i=0;i<nrCustomers;i++)
            if(customers[i].getEmail().compareTo(email)==0 && customers[i].getPassword().compareTo(password)==0)
            {
                currentCustomer=customers[i];
                return customers[i].getIdCustomer();
            }
        return 0;
    }

    /**
     * Checks to see if the provided data for creating a new account is corect. If it is, then a new account with the information is created, added into the "customers" array
     * and a refference to it is put in currentCustomer.
     * The exceptions are handled here.
     * Parameters are the Strings given by the user.
     * @return -1 if it is ok and a new account has been created, or a number representing the number of the error (which wrong label from ViewSigUp to be showed - index of the
     * label in ViewSignUp "wrongLabels" array).
     */
    public int checkSignUp(String name, String email, String address, String phone, String password, String repeatedPassword, String dateOfBirth)
    {
        if(name==null || name.isEmpty())
            return 0;

        char[] chars = name.toCharArray();
        for(char c : chars)
        {
            if(!Character.isLetter(c) && c!=' ')
                return 7;
        }
        if(dateOfBirth!= null && !dateOfBirth.isEmpty())
        {
            if(dateOfBirth.length()!=10)
                return 1;
            char[] chars2 = dateOfBirth.toCharArray();
            if (chars2[4]!='-' || chars2[7]!='-')
                return 1;
            try
            {
                int year=Integer.parseInt(dateOfBirth.substring(0,4));
                int month=Integer.parseInt(dateOfBirth.substring(5,7));
                int day=Integer.parseInt(dateOfBirth.substring(8,10));
                if(year>2020 || year<1920)
                    return 1;
                if(month>12 || month<1)
                    return 1;
                if(day>31 || day<1)
                    return 1;
            }
            catch (NumberFormatException e)
            {
                return 1;
            }
        }

        if(phone != null && !phone.isEmpty())
        {
            if(phone.length()!=10)
                return 3;
            for(int i=0;i<9;i++)
                if(!Character.isDigit(phone.charAt(i)))
                    return 3;
        }

        if(email==null)
            return 4;

        if(email.isEmpty())
            return 4;

        if(!this.valEmail(email))
            return 4;

        for(int i=0;i<this.nrCustomers;i++)
            if(customers[i].getEmail().compareTo(email)==0)
                return 8;

        if(password==null)
            return 5;

        if(password.isEmpty())
            return 5;

        if(repeatedPassword==null)
            return 6;

        if(repeatedPassword.isEmpty())
            return 6;

        if(password.compareTo(repeatedPassword)!=0)
            return 6;

        Customer customer = new Customer(nrCustomers+1, name, address, phone, email, password, dateOfBirth);
        customers[nrCustomers]=customer;
        nrCustomers++;
        currentCustomer=customer;
        db.introduceNewCustomerAccount(currentCustomer);

        return -1; //ok
    }

    /**
     * Checks to see if the provided data for updating the account is corect. If it is, then the information in currentCustomer and in the database is updated.
     * The exceptions are handled here.
     * Parameters are the Strings given by the user.
     *
     * @return -1 if it is ok and the account has been updated, or a number representing the number of the error (which wrong label from ViewAccount to be showed - index of the
     * label in ViewAccount "wrongLabels" array).
     */
    public int checkUpdateAccount(String name, String email, String address, String phone, String password, String newPassword, String dateOfBirth)
    {
        if(name!=null && !name.isEmpty())
        {
            char[] chars = name.toCharArray();
            boolean existsLetter=false;
            for(char c : chars)
            {
                if(!Character.isLetter(c) && c!=' ')
                    return 0;
                if(Character.isLetter(c))
                    existsLetter=true;
            }
            if(!existsLetter)
                return 0;
        }

        if(dateOfBirth!= null && !dateOfBirth.isEmpty())
        {
            boolean onlyBlanks=true;
            for(int i=0;i<dateOfBirth.length();i++)
                if(!Character.isWhitespace(dateOfBirth.charAt(i)))
                    onlyBlanks=false;
            if(onlyBlanks)
                dateOfBirth="null";
            else
            {
                if(dateOfBirth.length()!=10)
                    return 1;
                char[] chars2 = dateOfBirth.toCharArray();
                if (chars2[4]!='-' || chars2[7]!='-')
                    return 1;
                try
                {
                    int year=Integer.parseInt(dateOfBirth.substring(0,4));
                    int month=Integer.parseInt(dateOfBirth.substring(5,7));
                    int day=Integer.parseInt(dateOfBirth.substring(8,10));
                    if(year>2020 || year<1920)
                        return 1;
                    if(month>12 || month<1)
                        return 1;
                    if(day>31 || day<1)
                        return 1;
                }
                catch (NumberFormatException e)
                {
                    return 1;
                }
            }
        }

        if(phone != null && !phone.isEmpty())
        {
            boolean onlyBlanks=true;
            for(int i=0;i<phone.length();i++)
                if(!Character.isWhitespace(phone.charAt(i)))
                    onlyBlanks=false;
            if(onlyBlanks)
                phone="null";
            else
            {
                if(phone.length()!=10)
                    return 3;
                for(int i=0;i<9;i++)
                    if(!Character.isDigit(phone.charAt(i)))
                        return 3;
            }
        }

        if(address!=null && !address.isEmpty())
        {
            boolean onlyBlanks=true;
            for(int i=0;i<address.length();i++)
                if(!Character.isWhitespace(address.charAt(i)))
                    onlyBlanks=false;
            if(onlyBlanks)
                address="null";
        }

        if(email!=null && !email.isEmpty())
        {
            for(int i=0;i<nrCustomers;i++)
                if(customers[i].getEmail().compareTo(email)==0 && customers[i]!=currentCustomer)
                    return 4;

            if(!this.valEmail(email))
                return 2;
        }

        if(newPassword!=null && !newPassword.isEmpty())
        {
            if(password==null || password.isEmpty())
                return 5;
            if(currentCustomer.getPassword().compareTo(password)!=0)
                return 6; //wrong password
        }

        if(name!=null && !name.isEmpty())
            currentCustomer.setCustomerName(name);

        if(dateOfBirth!=null && !dateOfBirth.isEmpty())
            if(dateOfBirth.compareTo("null")==0)
                currentCustomer.setDateOfBirth(null);
            else
                currentCustomer.setDateOfBirth(dateOfBirth);

        if(address!=null && !address.isEmpty())
            if(address.compareTo("null")==0)
                currentCustomer.setAddress(null);
            else
                currentCustomer.setAddress(address);

        if(phone!=null && !phone.isEmpty())
            if(phone.compareTo("null")==0)
                currentCustomer.setPhone(null);
            else
                currentCustomer.setPhone(phone);
        if(email!=null && !email.isEmpty())
            currentCustomer.setEmail(email);
        if(newPassword!=null && !newPassword.isEmpty())
            currentCustomer.setPassword(newPassword);


        db.updateCustomerAccount(currentCustomer);

        return -1; //ok
    }

    public Customer getCurrentCustomer()
    {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer customer)
    {
        currentCustomer=customer;
    }

    private boolean valEmail(String input)
    {
        String emailFormat = "^[A-Z0-9._]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailFormat,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(input);
        return matcher.find();
    }
}
