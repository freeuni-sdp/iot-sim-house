package ge.edu.freeuni.sdp.iot.simulators.house.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Koko on 25.06.2016.
 */
public class HouseMac {
    private Map<String, MacObject> macs;

    public HouseMac() {
        macs = new HashMap<>();
    }

    public void add(MacObject macObject) {
        macs.put(macObject.getId(), macObject);
    }

    public MacObject get(String macId) {
        return macs.get(macId);
    }

    public void delete(String macID) {
        macs.remove(macID);
    }


    public boolean isEmpty() {
        return macs.isEmpty();
    }

    public String[] getIds() {
        return (String[]) macs.keySet().toArray();
    }
}
