package util;
import java.io.*;
import java.net.*;
import org.junit.*;

import java.util.logging.*;

import static org.junit.Assert.*;
import static util.ContainsMatches.containsMatches;

public class SearchTest {
    private static final String ANY_TITLE = "1";
    private InputStream stream;

    @Before
    public void turnOffLogging(){
        Search.LOGGER.setLevel(Level.OFF);
    }

    @After
    public void closeResources() throws IOException {
        stream.close();
    }

    @Test
    public void returnsMatchesShowingContextWhenSearchStringInContent() throws IOException{
             stream = streamOn("rest of text here"
                                +"1234567890search term1234567890"
                                +"more rest of text");

            Search search = new Search(stream, "search term", ANY_TITLE);
            search.setSurroundingCharacterCount(10);

            search.execute();

            assertThat(search.getMatches(), containsMatches(new Match[] {
                 new Match(ANY_TITLE,
                         "search term",
                         "1234567890search term1234567890") }));

    }
    @Test
    public void noMatchesReturnedWhenSearchStringNotInContent() {

        stream = streamOn("any text");
        Search search= new Search(stream, "text that does not match", ANY_TITLE);

        search.execute();

        assertTrue(search.getMatches().isEmpty());
    }

    public InputStream streamOn(String pageContent){
        return new ByteArrayInputStream(pageContent.getBytes());
    }

    @Test
    public void returnsErroredWhenUnableToReadStream(){
        stream = createStreamThrowingErrorWhenRead();
        Search search= new Search(stream,"","");

        search.execute();

        assertTrue(search.errored());
    }

    private InputStream createStreamThrowingErrorWhenRead() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        };
    }

    @Test
    public void erroredReturnsFalseWhenReadSucceds(){
        stream = streamOn("");
        Search search = new Search(stream,"","");

        search.execute();

        assertFalse(search.errored());
    }

}