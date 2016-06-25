package ge.edu.freeuni.sdp.iot.simulators.house.core;

import ge.edu.freeuni.sdp.iot.simulators.house.model.HouseData;

import java.util.List;

/**
 * Created by Nika Doghonadze
 */
interface HouseRegistry {
    List<HouseData> getAllHouses();
    HouseData getHouseData(String houseId);
}
