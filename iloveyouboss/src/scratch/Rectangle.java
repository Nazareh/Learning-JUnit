package scratch;

import java.awt.*;

public class Rectangle {
    private Point origin;
    private Point opposite;

    public Rectangle (Point origin, Point oppositeCorner){
        this.origin = origin;
        this.opposite = oppositeCorner;
    }
    public Rectangle (Point origin){
        this.opposite = this.origin = origin;
    }
    public int area(){
        return Math.abs(origin.x - opposite.x) * Math.abs(origin.y - opposite.y);
    }
    public void setOppositeCorner(Point opposite){
        this.opposite = opposite;
    }
    public Point getOrigin (){
        return origin;
    }
    public Point getOpposite(){
        return opposite;
    }
    @Override
    public String toString(){
        return "Rectangle (origin "+ origin + " opposite "+ ")";

    }


}
