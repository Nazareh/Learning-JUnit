package iloveyouboss;

import com.sun.org.apache.xpath.internal.operations.And;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileTest {
    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    @Before
    public void create(){
        question = new BooleanQuestion(1,"Got bonuses?");
        profile = new Profile("Bull Hockey, Inc.");
        criteria = new Criteria();

    }

    @Test
    //kill gets set to true because match is false and criterion.getWeight() equals Weight.MustMatch
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet(){
        //Arrange
        profile.add(new Answer(question,Bool.FALSE));
        criteria.add(new Criterion(new Answer(question,Bool.TRUE ),Weight.MustMatch));
        //Act
        boolean matches = profile.matches(criteria);
        //Assert
        assertFalse(matches);
    }

    @Test
    //match resolves to true because criterion.getWeight() returns Weight.DontCare.
    public void matchAnswersTrueForAnyDontCareCriteria(){
        //Arrange
        profile.add(new Answer(question,Bool.FALSE));
        criteria.add(new Criterion(new Answer(question,Bool.TRUE ),Weight.DontCare));
        //Act
        boolean matches = profile.matches(criteria);
        //Assert
        assertTrue(matches);
    }
}