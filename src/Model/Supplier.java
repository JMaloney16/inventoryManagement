package Model;

public class Supplier {
    private int supplierID;
    private String name;
    private String address;
    private String city;
    private String phoneNo;
    private String email;
    private String postcode;

    public Supplier(int supplierID, String name, String address, String city, String phoneNo, String email, String postcode) {
        this.supplierID = supplierID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNo = phoneNo;
        this.email = email;
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    @Override
    public String toString() {
        return supplierID + ", " + name + ", " + address + ", " + city + ", " + postcode + ", " + phoneNo + ", "
                + email;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
