package ge.edu.freeuni.sdp.iot.simulators.house.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Nikoloz on 06/25/16.
 */
public class MacObject {

        @XmlElement
        private String deviceName;

        @XmlElement
        private String deviceMacAddress;

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceMacAddress() {
            return deviceMacAddress;
        }

        public void setDeviceMacAddress(String deviceMacAddress) {
            this.deviceMacAddress = deviceMacAddress;
        }

        private String quot(String str) {
            return "\"" + str + "\"";
        }

        @Override
        public String toString() {
            return "{\"deviceName\":" + quot(deviceName) + ", \"deviceMacAddress\":" + quot(deviceMacAddress) + ", \"mac_id\":" + quot(id) + "}";
        }
}
