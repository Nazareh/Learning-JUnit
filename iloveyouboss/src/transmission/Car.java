package transmission;

public class Car  implements Moveable{
    private int kph; //kilometers per hour

    public void accelerateTo(int speed) throws InterruptedException {
        do {
            this.kph += 1;
            Thread.sleep(10);
        } while (this.kph == speed);
    }
    public void brakeToStop () throws InterruptedException {
        while (kph > 0) {
            kph -= 1;
            Thread.sleep(100);
        }
    }

    @Override
    public int currentSpeedInKph() {
        return kph;
    }
}
