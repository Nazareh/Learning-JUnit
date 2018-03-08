package iloveyouboss;

import com.sun.org.apache.xpath.internal.operations.And;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileTest {

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet(){
        //Arrange
        Question question = new BooleanQuestion(1,"Got bonuses?");

        Profile profile = new Profile("Bull Hockey, Inc.");
        profile.add(new Answer(question,Bool.FALSE));

        Criteria criteria = new Criteria();
        Criterion criterion = new Criterion(new Answer(question,Bool.TRUE ),Weight.MustMatch);
        criteria.add(criterion);

        //Act
        boolean matches = profile.matches(criteria);

        //Assert
        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria(){
        //Arrange
        Question question = new BooleanQuestion(1,"Got milk?");

        Profile profile = new Profile("Bull Hockey, Inc.");
        profile.add(new Answer(question,Bool.FALSE));

        Criteria criteria = new Criteria();
        Criterion criterion = new Criterion(new Answer(question,Bool.TRUE ),Weight.DontCare);
        criteria.add(criterion);

        //Act
        boolean matches = profile.matches(criteria);

        //Assert
        assertTrue(matches);

    }
}