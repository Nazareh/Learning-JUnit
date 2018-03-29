package transmission;

public class Transmission {
    private Gear gear;
    private Moveable moveable;

    public Transmission(Moveable moveable){
        this.moveable = moveable;
    }

    public void shift(Gear gear) {

            if(moveable.currentSpeedInKph() == 0 )  {
                this.gear = gear;
            }

    }
    public Gear getGear(){
        return gear;
    }


}
