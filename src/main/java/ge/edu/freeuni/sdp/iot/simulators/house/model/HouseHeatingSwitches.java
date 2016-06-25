package ge.edu.freeuni.sdp.iot.simulators.house.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nika on 6/25/16.
 */
@XmlRootElement
public class HouseHeatingSwitches {

    @XmlElement
    private Map<String, HeatingSwitch> switches;

    @XmlElement
    private String houseId;

    public HouseHeatingSwitches(String houseId) {
        this.houseId = houseId;
        switches = new HashMap<>();
    }

    public String getHouseId() {
        return houseId;
    }

    public void add(HeatingSwitch s) {
        switches.put(s.getId(), s);
    }

    public HeatingSwitch get(String floorId) {
        return switches.get(floorId);
    }

    public Collection<HeatingSwitch> getValues() {
        return switches.values();
    }

}
