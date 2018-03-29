package iloveyouboss;

public class Profile {
    private AnswerCollection answers = new AnswerCollection();
    private String name;

    public Profile(String name ){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void add (Answer answer) {
        answers.add(answer);
    }

    public MatchSet getMatchSet(Criteria criteria){
        return new MatchSet(answers,criteria);

    }
    @Override
    public String toString(){
        return name;
    }

}
