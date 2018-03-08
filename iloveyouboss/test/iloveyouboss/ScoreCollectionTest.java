package iloveyouboss;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ScoreCollectionTest {

    @Test
    public void arithmeticMean() {
        //fail("Not yet implemented");

    }
    @Test
    public void answersArithmeticMeanOfTwoNumbers(){
        //Arrange
        ScoreCollection sc = new ScoreCollection();
        sc.add(() -> 7 );
        sc.add(() -> 5 );

        //Act
        int actualResult = sc.arithmeticMean();

        //Assert
        assertThat(actualResult,equalTo(6));

    }
}