package ge.edu.freeuni.sdp.iot.simulators.house.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nika on 6/25/16.
 */
@XmlRootElement
public class HeatingSwitch {

    @XmlElement
    private String id;

    @XmlElement
    private boolean status;

    @XmlElement
    private boolean available;

    public HeatingSwitch(String id, boolean status) {
        this.id = id;
        this.status = status;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean value) {
        this.available = value;
    }

}
