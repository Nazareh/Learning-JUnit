package iloveyouboss;

public class Address {

    private String houseNumber;
    private String road;
    private String city;
    private String state;
    private String zip;

    public Address(String houseNumber, String road, String city, String state, String zip) {
        this.houseNumber = houseNumber;
        this.road = road;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getRoad() {
        return road;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

}
