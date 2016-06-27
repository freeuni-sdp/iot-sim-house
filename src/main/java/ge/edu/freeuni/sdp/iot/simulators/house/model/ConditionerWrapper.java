package ge.edu.freeuni.sdp.iot.simulators.house.model;

import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement
public class ConditionerWrapper {

    private String status;

    public ConditionerWrapper(){}
    public ConditionerWrapper(String status){
        setStatus(status);
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

}
