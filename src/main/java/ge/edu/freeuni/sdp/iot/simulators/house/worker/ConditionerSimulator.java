package ge.edu.freeuni.sdp.iot.simulators.house.worker;


import ge.edu.freeuni.sdp.iot.simulators.house.core.Repository;
import ge.edu.freeuni.sdp.iot.simulators.house.core.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.simulators.house.model.Floor;
import ge.edu.freeuni.sdp.iot.simulators.house.model.House;

/**
 * Created by elene on 6/27/16.
 */
public class ConditionerSimulator extends Thread {
    private static final int SLEEP_INTERVAL = 50;
    private static final double MIN_TEMPERATURE = 10.0;
    private static final double WEAK_STREAM_RATE = 0.0215;
    private static final double STRONG_STREAM_RATE = 0.042;

    private String houseId, condition;
    public ConditionerSimulator(String houseId, String condition){
        this.houseId = houseId;
        this.condition = condition;
    }


    public void run() {
        Repository repository = RepositoryFactory.inMemoryRepositoryInstance();

        while (!interrupted()) {
            House h = repository.findHouse(this.houseId);
            for (Floor f : h.getAllFloors()) {
                double temp = f.getTemperature();
                switch(h.getConditionerCondition()){
                    case "0": break;
                    case "#": break;
                    case "*":
                        f.setTemperature((temp<MIN_TEMPERATURE ? temp+(MIN_TEMPERATURE-temp)*WEAK_STREAM_RATE : temp));
                        break;
                    case "**":
                        f.setTemperature((temp<MIN_TEMPERATURE ? temp+(MIN_TEMPERATURE-temp)*STRONG_STREAM_RATE : temp));
                        break;
                    default: break;
                }
            }
            try {
                Thread.sleep(SLEEP_INTERVAL);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
