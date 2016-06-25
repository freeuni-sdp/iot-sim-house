package ge.edu.freeuni.sdp.iot.simulators.house.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Nika Doghonadze
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemperatureData {
    @JsonProperty("house_id")
    private String houseId;

    @JsonProperty("floor_id")
    private String floorId;

    @JsonProperty("temperature")
    private double temperature;


    public TemperatureData(String houseId, String floorId, double temperature) {
        this.houseId = houseId;
        this.floorId = floorId;
        this.temperature = temperature;
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

    @Override
    public String toString() {
        return "{" +
                "\"house_id\": \"" + getHouseId() +"\"," +
                "\"floor_id\": \"" + getFloorId() + "\"," +
                "\"temperature\": "+ getTemperature() +"" +
                "}";
    }
}
