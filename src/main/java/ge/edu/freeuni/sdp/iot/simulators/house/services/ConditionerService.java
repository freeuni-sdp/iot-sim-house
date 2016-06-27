package ge.edu.freeuni.sdp.iot.simulators.house.services;

import ge.edu.freeuni.sdp.iot.simulators.house.model.ConditionerWrapper;
import ge.edu.freeuni.sdp.iot.simulators.house.worker.ConditionerSimulator;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/")
public class ConditionerService {

    @POST
    @Path("/conditioner/{house_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertSwitch(@PathParam("house_id") String id, ConditionerWrapper cw){
        ConditionerSimulator ventilator = new ConditionerSimulator(id, cw.getStatus());
        ventilator.start();
        return Response.ok().build();
    }

}
