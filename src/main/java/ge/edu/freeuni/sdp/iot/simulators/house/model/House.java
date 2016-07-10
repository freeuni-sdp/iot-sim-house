package ge.edu.freeuni.sdp.iot.simulators.house.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nika Doghonadze
 */
public class House {
    private String houseId;
    private Map<String, Floor> floorMap;
    private Map<String, MacObject> macMap;
    private String conditionerCondition;

    public House(String houseId) {
        this.houseId = houseId;
        floorMap = new HashMap<>();
        macMap = new HashMap<>();
        conditionerCondition = "0";
    }

    public House(String houseId, Map<String, Floor> floorMap) {
        this.houseId = houseId;
        this.floorMap = floorMap;
        macMap = new HashMap<>();
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public Floor getFloor(String floorId) {
        if (floorMap.containsKey(floorId))
            return floorMap.get(floorId);

        return null;
    }

    public String getConditionerCondition(){
        return conditionerCondition;
    }

    public void setConditionerCondition(String condition){
        this.conditionerCondition = condition;
    }

    public Iterable<Floor> getAllFloors() {
        return new ArrayList<>(floorMap.values());
    }
}
