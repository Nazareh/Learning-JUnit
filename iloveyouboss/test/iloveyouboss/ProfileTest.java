package iloveyouboss;

import com.sun.org.apache.xpath.internal.operations.And;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ProfileTest {
    private Profile profile;
    private Criteria criteria;

    private Question questionReimbursesTuition;
    private Answer answerReimbursesTuition;
    private Answer answerDoesNotReimbursesTuition;

    private Question questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNoRelocation;

    private Question questionOnsiteDaycare;
    private Answer answerHasOnsiteDaycare;
    private Answer answerNoOnsiteDaycare;

    @Before
    public void createProfile(){
        profile = new Profile("Bull Hockey, Inc.");
    }
    @Before
    public void createCriteria(){
        criteria = new Criteria();
    }
    @Before
    public void createQuestionsAndAnswers(){
        questionReimbursesTuition = new BooleanQuestion(1,"Reimburses tuition?");
        answerReimbursesTuition   = new Answer(questionReimbursesTuition,Bool.TRUE);
        answerDoesNotReimbursesTuition = new Answer(questionReimbursesTuition,Bool.FALSE);

        questionIsThereRelocation = new BooleanQuestion(1,"Relocation package?");
        answerThereIsRelocation   = new Answer(questionIsThereRelocation,Bool.TRUE);
        answerThereIsNoRelocation = new Answer(questionIsThereRelocation,Bool.FALSE);

        questionOnsiteDaycare = new BooleanQuestion(1,"Onsite daycare?");
        answerHasOnsiteDaycare   = new Answer(questionOnsiteDaycare,Bool.TRUE);
        answerNoOnsiteDaycare = new Answer(questionOnsiteDaycare,Bool.FALSE);

    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet(){
        profile.add(answerDoesNotReimbursesTuition);
        criteria.add(new Criterion(answerReimbursesTuition,Weight.MustMatch));

        boolean matches = profile.matches(criteria);

        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria(){
        profile.add(answerDoesNotReimbursesTuition);
        criteria.add(new Criterion(answerReimbursesTuition,Weight.DontCare));

        boolean matches = profile.matches(criteria);
        assertTrue(matches);
    }
    @Test
    public void matchAnswerTrueWhenAnyOfMultipleCriteriaMatch(){
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimbursesTuition);
        criteria.add(new Criterion(answerThereIsRelocation,Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition,Weight.Important));
        boolean matches = profile.matches(criteria);
        assertTrue(matches);
    }
    @Test
    public void matchAnswersFalseWhenNoneOfMultipleCriteriaMatch(){
        profile.add(answerThereIsNoRelocation);
        profile.add(answerDoesNotReimbursesTuition);
        criteria.add(new Criterion(answerThereIsRelocation,Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition,Weight.Important));

        boolean matches = profile.matches(criteria);
        assertFalse(matches);
    }

    @Test
    public void scoreIsCriterionValueForSIngleMatch(){
        profile.add(answerThereIsRelocation);
        criteria.add(new Criterion(answerThereIsRelocation,Weight.Important));

        profile.matches(criteria);
        assertThat(profile.score(),equalTo(Weight.Important.getValue()));
    }
    @Test
    public void scoreAccumulatesCriterionValuesForMatches(){
        profile.add(answerThereIsRelocation);
        profile.add(answerReimbursesTuition);
        profile.add(answerNoOnsiteDaycare);
        criteria.add(new Criterion(answerThereIsRelocation,Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition,Weight.WouldPrefer));
        criteria.add(new Criterion(answerNoOnsiteDaycare,Weight.VeryImportant));

        profile.matches(criteria);
        int expectedScore = Weight.Important.getValue() + Weight.WouldPrefer.getValue() + Weight.VeryImportant.getValue();

        assertThat(profile.score(),equalTo(expectedScore));

    }
}