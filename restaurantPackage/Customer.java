package restaurantPackage;

/**
 * @author Chereji Iulia
 */
public class Customer {
    private int idCustomer;
    private String customerName;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String dateOfBirth;

    /**
     * Constructor with parameters
     * @param idCustomer integer, presumed correct (there is no other existing customer with the same id), >0
     * The other parameters are Strings.
     * customerName, email and password are not allowed to be null.
     */
    public Customer(int idCustomer, String customerName, String address, String phone, String email, String password, String dateOfBirth)
    {
        this.idCustomer=idCustomer;
        this.customerName= new String(customerName);
        if(address!=null)
            this.address=new String(address);
        if(phone!=null)
            this.phone = new String(phone);
        this.email = new String(email);
        this.password = new String(password);
        if(dateOfBirth!=null)
            this.dateOfBirth = new String(dateOfBirth);
    }

    /**
     * Constructor without parameters.
     * Sets all the fields to null, and the id to 0, meaning that this user is not logged in
     */
    public Customer()
    {
        idCustomer=0;
        customerName=null;
        address=null;
        phone=null;
        email=null;
        password=null;
        dateOfBirth=null;
    }

    public int getIdCustomer()
    {
        return idCustomer;
    }
    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
