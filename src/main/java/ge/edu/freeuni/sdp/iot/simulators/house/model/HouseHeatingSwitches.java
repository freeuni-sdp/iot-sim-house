package ge.edu.freeuni.sdp.iot.simulators.house.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

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

    public static HouseHeatingSwitches fromJson(JSONObject object) {

        HouseHeatingSwitches res = new HouseHeatingSwitches(object.getString("houseId"));

        JSONObject switches = object.getJSONObject("switches");
        for (String id : switches.keySet()) {
            HeatingSwitchMessage message = HeatingSwitchMessage.fromJson(switches.getJSONObject(id));
            res.add(message);
        }

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HouseHeatingSwitches that = (HouseHeatingSwitches) o;

        if (switches != null ? !switches.equals(that.switches) : that.switches != null) return false;
        return houseId != null ? houseId.equals(that.houseId) : that.houseId == null;

    }
}
