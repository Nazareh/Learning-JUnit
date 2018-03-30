package iloveyouboss;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;


public class AddressRetrieverTest {
    @Mock private Http http;
    @InjectMocks private AddressRetriever retriever;

    @Before
    public void createRetrieve(){
        retriever = new AddressRetriever();
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void answersAppropiateAddressForValidCoordinates() throws IOException, ParseException {
        when(http.get(contains("lat=38.000000&lon=-104.000000")))
                .thenReturn("{\"address\":{"
                                            + "\"house_number\":\"324\","
                                            + "\"road\":\"North Tejon Street\","
                                            + "\"city\":\"Colorado Springs\","
                                            + "\"state\":\"Colorado\","
                                            + "\"zip\":\"80903\","
                                            + "\"country_code\":\"us\"}"
                                            + "}");

        AddressRetriever retriever = new AddressRetriever();
        Address address = retriever.retrieve(38.0,-104.0);

        assertThat(address.getHouseNumber(), equalTo("324"));
        assertThat(address.getRoad(), equalTo("North Tejon Street"));
        assertThat(address.getCity(), equalTo("Colorado Springs"));
        assertThat(address.getState(), equalTo("Colorado"));
        assertThat(address.getZip(), equalTo("80903"));
    }
}