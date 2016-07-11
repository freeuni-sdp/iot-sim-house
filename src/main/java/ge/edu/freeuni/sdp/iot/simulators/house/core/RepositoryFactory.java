package ge.edu.freeuni.sdp.iot.simulators.house.core;

import ge.edu.freeuni.sdp.iot.simulators.house.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nika Doghonadze
 */
public class RepositoryFactory {

    private static Repository instance;
    private static Repository testInstance;

    private static SwitchThread switchThread;
    private static boolean testMode = false;

    public static synchronized Repository inMemoryRepositoryInstance() {

        if (testMode) {
            return testInstance;
        }

        if (instance == null) {
            Map<String, House> initialMap = getInitialHouseMap();
            instance = new InMemoryRepository(initialMap);
        }

        return instance;
    }

    public static void setTestInstance(Repository repository) {
        testInstance = repository;
    }

    public static void setTestMode(boolean mode) {
        testMode = mode;
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

        switchThread = new SwitchThread(100);
        switchThread.start();

        return res;
    }

    private static House initNewHouse(HouseData house) {
        Map<String, Floor> floorMap = new HashMap<>();
        //all houses have 5 floors with id "1" to "5";
        int numFloors = 5;
        for (int i=0; i<numFloors; i++) {
            String floorId = String.valueOf(i + 1);
            Floor floor = initNewFloor(house.getHouseId(), floorId);
            floor.setHeatingSwitch(new HeatingSwitch());
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
