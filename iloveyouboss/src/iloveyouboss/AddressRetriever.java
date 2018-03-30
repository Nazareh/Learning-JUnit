package iloveyouboss;

import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class AddressRetriever {
    private Http http = new HttpImpl();

    public Address retrieve(double latitude, double longitude) throws IOException,ParseException{
        String parms = String.format("lat=%.6f&lon=%.6f",latitude,longitude);
        String response = http.get("http://open.mapquestapi.com/nominatim/v1/reverse?format=json&" + parms);
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(response);
        JSONObject address = (JSONObject) jsonObject.get("address");

        String country = (String)address.get("country_code");
        if(!country.equals("us"))
            throw new UnsupportedOperationException("cannot support non-US addresses at this time");

        String houseNumber  = (String) address.get("house_number");
        String road         = (String) address.get("road");
        String city         = (String) address.get("city");
        String state        = (String) address.get("state");
        String zip          = (String) address.get("zip");

        return new Address(houseNumber,road,city,state,zip);
    }
}
