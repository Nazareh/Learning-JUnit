package scratch;

import org.junit.After;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static scratch.ConstrainsSidesTo.constrainsSideTo;

public class RectangleTest {
    private Rectangle rectangle;
    @After
    public void ensureInvariant(){
        assertThat(rectangle, constrainsSideTo(100));

    }
    @Test
    public void answerArea(){
        rectangle = new Rectangle(new Point(5,5),new Point (15,10));
        assertThat(rectangle.area(),equalTo(50));
    }
    @Test
    public void  allowDynamicallyChangingSize(){
        rectangle = new Rectangle(new Point(5,5));
        rectangle.setOppositeCorner(new Point(130,130));
        assertThat(rectangle.area(),equalTo(15625));

    }

}