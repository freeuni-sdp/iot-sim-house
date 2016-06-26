package ge.edu.freeuni.sdp.iot.simulators.house.core;

import ge.edu.freeuni.sdp.iot.simulators.house.model.Floor;
import ge.edu.freeuni.sdp.iot.simulators.house.model.HeatingSwitch;
import ge.edu.freeuni.sdp.iot.simulators.house.model.House;

/**
 * Created by nika on 6/27/16.
 */
public class SwitchThread extends Thread {

    private static final double MAX_TEMPERATURE = 30.0;
    private static final double MIN_TEMPERATURE = 10.0;
    private static final double INCREASE_RATE = 0.05;
    private static final double DECREASE_RATE = 0.02;

    private long frequency;

    public SwitchThread(long frequency) {
        this.frequency = frequency;
    }

    @Override
    public void run() {
        while (!interrupted()) {

            Repository repository = RepositoryFactory.inMemoryRepositoryInstance();
            for (House h : repository.getAllHouses()) {
                for (Floor f : h.getAllFloors()) {
                    HeatingSwitch heatingSwitch = f.getHeatingSwitch();

                    if (heatingSwitch.isOn()) {
                        f.setTemperature(f.getTemperature() +
                                (f.getTemperature() - MAX_TEMPERATURE) * INCREASE_RATE);
                    } else {
                        f.setTemperature(f.getTemperature() +
                                (f.getTemperature() - MIN_TEMPERATURE) * DECREASE_RATE);
                    }

                }
            }

            try {
                Thread.sleep(frequency);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
