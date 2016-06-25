package ge.edu.freeuni.sdp.iot.simulators.house.core;

import ge.edu.freeuni.sdp.iot.simulators.house.model.Floor;
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
        //TODO INIT HOUSE
        return new House(house.getHouseId());
    }

}
