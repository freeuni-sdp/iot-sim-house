package ge.edu.freeuni.sdp.iot.simulators.house.model;

/**
 * Created by Nika Doghonadze
 */
public class Floor {
    private String houseId;
    private String floorId;
    private double temperature;


    public Floor(String houseId, String floorId) {
        this.houseId = houseId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
