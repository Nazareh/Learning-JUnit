package scratch;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ConstrainsSidesTo extends TypeSafeMatcher<Rectangle>{
    private int length;

    public ConstrainsSidesTo (int length){
        this.length = length;
    }
    @Override
    protected boolean matchesSafely(Rectangle rect) {
        return  Math.abs(rect.getOrigin().x - rect.getOpposite().x) <= length &&
                Math.abs(rect.getOrigin().y - rect.getOpposite().y) <= length;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("both sides must be <= " + length);

    }
    @Factory
    public static <T>Matcher<Rectangle> constrainsSideTo(int length){
        return new ConstrainsSidesTo(length);
    }
}
