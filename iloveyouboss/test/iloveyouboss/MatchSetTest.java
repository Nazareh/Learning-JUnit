package iloveyouboss;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MatchSetTest {
    private MatchSet matchSet;
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

    private AnswerCollection answers;

    @Before
    public void createAnswers(){
        answers = new AnswerCollection();

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
    private MatchSet createMatchSet() {
        return new MatchSet(answers, criteria);
    }
    private void add(Answer answer){
        answers.add (answer);

    }
    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet(){
        add(answerDoesNotReimbursesTuition);
        criteria.add(new Criterion(answerReimbursesTuition,Weight.MustMatch));
        assertFalse(createMatchSet().matches());
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria(){
        add(answerDoesNotReimbursesTuition);
        criteria.add(new Criterion(answerReimbursesTuition,Weight.DontCare));
        assertTrue(createMatchSet().matches());
    }

    @Test
    public void matchAnswerTrueWhenAnyOfMultipleCriteriaMatch(){
        add(answerThereIsRelocation);
        add(answerDoesNotReimbursesTuition);
        criteria.add(new Criterion(answerThereIsRelocation,Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition,Weight.Important));
        assertTrue(createMatchSet().matches());
    }
    @Test
    public void matchAnswersFalseWhenNoneOfMultipleCriteriaMatch(){
        add(answerThereIsNoRelocation);
        add(answerDoesNotReimbursesTuition);
        criteria.add(new Criterion(answerThereIsRelocation,Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition,Weight.Important));

        assertFalse(createMatchSet().matches());
    }

    @Test
    public void scoreIsCriterionValueForSingleMatch(){
        add(answerThereIsRelocation);
        criteria.add(new Criterion(answerThereIsRelocation,Weight.Important));

        assertThat(createMatchSet().getScore(),equalTo(Weight.Important.getValue()));
    }
    @Test
    public void scoreAccumulatesCriterionValuesForMatches(){
        add(answerThereIsRelocation);
        add(answerReimbursesTuition);
        add(answerNoOnsiteDaycare);
        criteria.add(new Criterion(answerThereIsRelocation,Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition,Weight.WouldPrefer));
        criteria.add(new Criterion(answerNoOnsiteDaycare,Weight.VeryImportant));

        int expectedScore = Weight.Important.getValue() + Weight.WouldPrefer.getValue() + Weight.VeryImportant.getValue();

        assertThat(createMatchSet().getScore(),equalTo(expectedScore));

    }



}
