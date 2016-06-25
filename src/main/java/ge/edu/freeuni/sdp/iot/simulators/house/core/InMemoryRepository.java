package ge.edu.freeuni.sdp.iot.simulators.house.core;

import ge.edu.freeuni.sdp.iot.simulators.house.model.House;

import java.util.Map;

/**
 * Created by Nika Doghonadze
 */
public class InMemoryRepository implements Repository {
    private Map<String, House> gardenMap;

    public InMemoryRepository(Map<String, House> gardenMap) {
        this.gardenMap = gardenMap;
    }

    @Override
    public House findHouse(String houseId) {
        if (gardenMap.containsKey(houseId))
            return gardenMap.get(houseId);
        return null;
    }

    @Override
    public void updateHouse(House house) {
        gardenMap.put(house.getHouseId(), house);
    }

}
