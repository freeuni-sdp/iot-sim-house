package ge.edu.freeuni.sdp.iot.simulators.house.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by nika on 6/25/16.
 */
public class SwitchOnRequest {

    @XmlElement
    private int period;

    public SwitchOnRequest(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

}
