package ge.edu.freeuni.sdp.iot.simulators.house.core;

import ge.edu.freeuni.sdp.iot.simulators.house.model.Floor;
import ge.edu.freeuni.sdp.iot.simulators.house.model.HeatingSwitchMessage;
import ge.edu.freeuni.sdp.iot.simulators.house.model.House;
import ge.edu.freeuni.sdp.iot.simulators.house.model.HouseData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nika Doghonadze
 */
public class RepositoryFactory {
    private static Repository instance;

    public static synchronized Repository inMemoryRepositoryInstance() {
        if (instance == null) {
            Map<String, House> initialMap = getInitialHouseMap();
            instance = new InMemoryRepository(initialMap);
        }

        return instance;
    }

    private static Map<String, House> getInitialHouseMap() {
        HouseRegistry houseRegistry = new HouseRegistryProxy();
        List<HouseData> allHouses = houseRegistry.getAllHouses();
        if (allHouses == null)
            return new HashMap<>();

        Map<String, House> res = new HashMap<>();
        for (HouseData houseData : allHouses) {
            House newHouse = initNewHouse(houseData);
            res.put(houseData.getHouseId(), newHouse);
        }

        return res;
    }

    private static House initNewHouse(HouseData house) {
        Map<String, Floor> floorMap = new HashMap<>();
        //all houses have 5 floors with id "1" to "5";
        int numFloors = 5;
        for (int i=0; i<numFloors; i++) {
            String floorId = String.valueOf(i + 1);
            Floor floor = initNewFloor(house.getHouseId(), floorId);
            floor.setHeatingSwitch(new HeatingSwitchMessage(floorId, false));
            floorMap.put(floorId, floor);
        }
        return new House(house.getHouseId(), floorMap);
    }

    private static Floor initNewFloor(String houseId, String floorId) {
        Floor res = new Floor(houseId, floorId);
        res.setTemperature(25.5);
        return res;
    }

}
