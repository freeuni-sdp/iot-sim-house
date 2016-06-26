package ge.edu.freeuni.sdp.iot.simulators.house.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nika on 6/25/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HouseHeatingSwitches {

    @JsonProperty("switches")
    private Map<String, HeatingSwitchMessage> switches;

    @JsonProperty("houseId")
    private String houseId;

    public HouseHeatingSwitches(String houseId) {
        this.houseId = houseId;
        switches = new HashMap<>();
    }

    public String getHouseId() {
        return houseId;
    }

    public void add(HeatingSwitchMessage s) {
        switches.put(s.getId(), s);
    }

    public HeatingSwitchMessage get(String floorId) {
        return switches.get(floorId);
    }

}
