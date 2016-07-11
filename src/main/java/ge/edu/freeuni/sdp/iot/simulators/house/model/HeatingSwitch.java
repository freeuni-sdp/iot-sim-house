package ge.edu.freeuni.sdp.iot.simulators.house.model;

/**
 * Created by nika on 6/25/16.
 */
public class HeatingSwitch {

    private long turnOnTime;

    private int onDuration;

    public HeatingSwitch() {
        this.turnOnTime = 0;
        this.onDuration = 0;
    }

    public synchronized void turnOn(int duration) {
        this.turnOnTime = System.currentTimeMillis();
        this.onDuration = duration;
    }

    public synchronized void turnOff() {
        this.onDuration = 0;
    }

    public synchronized boolean isOn() {
        return (System.currentTimeMillis() - turnOnTime) < onDuration * (long) 1000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeatingSwitch that = (HeatingSwitch) o;

        if (turnOnTime != that.turnOnTime) return false;
        return onDuration == that.onDuration;

    }

}
