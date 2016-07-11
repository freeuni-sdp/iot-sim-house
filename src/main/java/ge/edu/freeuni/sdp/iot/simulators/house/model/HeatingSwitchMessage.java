package ge.edu.freeuni.sdp.iot.simulators.house.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nika on 6/25/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeatingSwitchMessage {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("available")
    private boolean available;

    public HeatingSwitchMessage(String id, boolean status) {
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

    public static HeatingSwitchMessage fromJson(JSONObject object) {
        HeatingSwitchMessage heatingSwitchMessage = new HeatingSwitchMessage(
                object.getString("id"),
                object.getBoolean("status")
        );
        heatingSwitchMessage.setAvailable(object.getBoolean("available"));
        return heatingSwitchMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeatingSwitchMessage message = (HeatingSwitchMessage) o;

        if (status != message.status) return false;
        if (available != message.available) return false;
        return id != null ? id.equals(message.id) : message.id == null;

    }

}
