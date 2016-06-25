package ge.edu.freeuni.sdp.iot.simulators.house.model;

/**
 * Created by Nika Doghonadze
 */
public class Floor {
    private String houseId;
    private String floorId;
    private double temperature;
    private HeatingSwitch heatingSwitch;


    public Floor(String houseId, String floorId) {
        this.houseId = houseId;
        this.floorId = floorId;
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

    public HeatingSwitch getHeatingSwitch() {
        return heatingSwitch;
    }

    public void setHeatingSwitch(HeatingSwitch heatingSwitch) {
        this.heatingSwitch = heatingSwitch;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
