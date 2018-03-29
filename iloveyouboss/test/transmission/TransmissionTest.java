package transmission;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class TransmissionTest {
    private Transmission transmission;
    private Car car;

    @Before
    public void before(){
        car = new Car();
        transmission = new Transmission(car);
    }
    @Test
    public void remainsInDriveAfterAcceleration() throws InterruptedException {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(35);
        assertThat(transmission.getGear(),equalTo(Gear.DRIVE));
    }
    @Test
    public void ignoresShiftToParkWhileInDrive() throws InterruptedException {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(30);
        transmission.shift(Gear.PARK);
        assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
    }
    @Test
    public void allowsShiftToParkWhenNotDriving() throws InterruptedException {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(40);
        car.brakeToStop();
        transmission.shift(Gear.PARK);

        assertThat(transmission.getGear(),equalTo(Gear.PARK));
    }
    @Test
    public void testBrakeToStop() throws InterruptedException {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(40);
        car.brakeToStop();

        assertThat(car.currentSpeedInKph(),equalTo(0));
    }

}
