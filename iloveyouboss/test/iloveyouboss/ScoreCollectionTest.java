package iloveyouboss;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ScoreCollectionTest {
    private ScoreCollection sc;
    @Before
    public void before(){
        sc = new ScoreCollection();
    }

    @Test
    public void arithmeticMean() {
        //fail("Not yet implemented");

    }
    @Test
    public void answersArithmeticMeanOfTwoNumbers(){
        //Arrange
        sc.add(() -> 7 );
        sc.add(() -> 5 );

        //Act
        int actualResult = sc.arithmeticMean();

        //Assert
        assertThat(actualResult,equalTo(6));
    }
    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWhenAddinNull(){
        ScoreCollection sc = new ScoreCollection();
        sc.add(null);
        sc.arithmeticMean();
    }

    @Test
    public void answersZeroWhenNoElementAdded(){
        assertThat(sc.arithmeticMean(),equalTo(0));
    }
    @Test
    public void dealsWithIntergerOverflow(){
        sc.add( () -> Integer.MAX_VALUE);
        sc.add( () -> 1);
        assertThat(sc.arithmeticMean(),equalTo(1073741824));
    }
}