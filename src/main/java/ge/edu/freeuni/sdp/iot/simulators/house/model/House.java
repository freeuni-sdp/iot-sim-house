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

    public House(String houseId) {
        this.houseId = houseId;
        floorMap = new HashMap<>();
    }

    public House(String houseId, Map<String, Floor> floorMap) {
        this.houseId = houseId;
        this.floorMap = floorMap;
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

    public Iterable<Floor> getAllFloors() {
        return new ArrayList<>(floorMap.values());
    }
}
