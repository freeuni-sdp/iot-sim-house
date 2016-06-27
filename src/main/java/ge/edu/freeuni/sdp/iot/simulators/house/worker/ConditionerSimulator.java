package ge.edu.freeuni.sdp.iot.simulators.house.worker;


/**
 * Created by elene on 6/27/16.
 */
public class ConditionerSimulator extends Thread {
    private static final int INTERVAL = 50;

    private String houseId, condition;
    public ConditionerSimulator(String houseId, String condition){
        this.houseId = houseId;
        this.condition = condition;
    }


    public void run() {
        while (true) {
            //turn temperature down
            //
        }
    }
}
