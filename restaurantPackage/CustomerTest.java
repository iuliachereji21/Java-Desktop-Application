package restaurantPackage;

/**
 * Class CustomerTest provides unit testing for the Customer class
 * @author Chreji Iulia
 */
public class CustomerTest {
    private Customer customer;

    public CustomerTest()
    {
        customer=new Customer(17, "Iulia Chereji", null, null, "ic@yahoo.com", "0000", null);
        assert customer.getIdCustomer()==17 : "getIdCustomer Failed";
        assert customer.getCustomerName().compareTo("Iulia Chereji")==0 : "getCustomerName Failed";
        assert customer.getAddress()==null: "getAddress Failed";
        assert customer.getPhone()==null: "getPhone Failed";
        assert customer.getEmail().compareTo("ic@yahoo.com")==0: "getEmail Failed";
        assert customer.getPassword().compareTo("0000")==0: "getPassword Failed";
        assert customer.getDateOfBirth()==null: "getDateOfBirth Failed";

        customer.setCustomerName("Adela Chereji");
        customer.setAddress("Com. Baciu, nr. 5");
        customer.setPhone("0755228943");
        customer.setEmail("ic@gmail.com");
        customer.setPassword("1234");
        customer.setDateOfBirth("2000-03-21");
        assert customer.getCustomerName().compareTo("Adela Chereji")==0 : "setCustomerName Failed";
        assert customer.getAddress().compareTo("Com. Baciu, nr. 5")==0: "setAddress Failed";
        assert customer.getPhone().compareTo("0755228943")==0: "setPhone Failed";
        assert customer.getEmail().compareTo("ic@gmail.com")==0: "setEmail Failed";
        assert customer.getPassword().compareTo("1234")==0: "setPassword Failed";
        assert customer.getDateOfBirth().compareTo("2000-03-21")==0: "setDateOfBirth Failed";

    }

    public static void main(String[] args)
    {
        CustomerTest customerTest = new CustomerTest();
    }

}