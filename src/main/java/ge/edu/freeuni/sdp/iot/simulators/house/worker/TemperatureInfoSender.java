package ge.edu.freeuni.sdp.iot.simulators.house.worker;

import ge.edu.freeuni.sdp.iot.simulators.house.core.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.simulators.house.model.Floor;
import ge.edu.freeuni.sdp.iot.simulators.house.model.House;
import ge.edu.freeuni.sdp.iot.simulators.house.model.TemperatureData;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * Created by Nika Doghonadze
 */
public class TemperatureInfoSender extends Thread {
    private String shadowUri;
    private int intervalSecs;
    private Client client;

    public TemperatureInfoSender(String shadowUri, int interval, Client client) {
        this.shadowUri = shadowUri;
        this.intervalSecs = interval;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            sleep(intervalSecs);
            Iterable<House> allHouses = RepositoryFactory.inMemoryRepositoryInstance().getAllHouses();
            for (House house : allHouses) {
                Iterable<Floor> allFloors = house.getAllFloors();
                for (Floor floor : allFloors) {
                    TemperatureData temperatureData = new TemperatureData(
                            house.getHouseId(),
                            floor.getFloorId(),
                            floor.getTemperature()
                    );
                    sendTemperatureData(temperatureData);
                }
            }
        }
    }

    private void sendTemperatureData(TemperatureData temperatureData) {
        String url = getUrl(temperatureData);
        client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(temperatureData.toString()));
    }

    private String getUrl(TemperatureData temperatureData) {
        return shadowUri + "/houses/" + temperatureData.getHouseId()
                + "/floors/" + temperatureData.getFloorId();
    }

    private static void sleep(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
