package ge.edu.freeuni.sdp.iot.simulators.house.core;

import ge.edu.freeuni.sdp.iot.simulators.house.model.House;

/**
 * Created by Nika Doghonadze
 */
public interface Repository {
    House findHouse(String houseId);
    void updateHouse(House house);
}
