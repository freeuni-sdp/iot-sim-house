package ge.edu.freeuni.sdp.iot.simulators.house.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Koko on 25.06.2016.
 */
public class HouseMac {
    @XmlElement
    private Map<String, MacObject> macs;

    public HouseMac() {
        macs = new HashMap<>();
    }

    public void add(MacObject m_obj) {
        macs.put(m_obj.getId(), m_obj);
    }

    public MacObject get(String mac_Id) {
        return macs.get(mac_Id);
    }
}
