package ge.edu.freeuni.sdp.iot.simulators.house.services;

import ge.edu.freeuni.sdp.iot.simulators.house.core.Repository;
import ge.edu.freeuni.sdp.iot.simulators.house.core.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.simulators.house.model.SwitchOnRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by nika on 6/25/16.
 */
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class HeatingSwitchService {

    protected Repository getRepository() {
        return RepositoryFactory.inMemoryRepositoryInstance();
    }

    @GET
    @Path("heating")
    public Response getAllSwitches() {
        /* TODO: heating */
        return Response.ok().build();
    }

    @GET
    @Path("heating/{floor_id}")
    public Response getSwitchStatus(@PathParam("floor_id") String floorId) {
        /* TODO: heating on floor */
        return Response.ok().build();
    }

    @PUT
    @Path("heating/{floor_id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response switchOn(@PathParam("floor_id") String floorId, SwitchOnRequest request) {
        /* TODO: turn on heating on floor */
        return Response.ok().build();
    }

    @DELETE
    @Path("heating/{floor_id}")
    public Response switchOff(@PathParam("floor_id") String floorId) {
        /* TODO: turn off heating on floor */
        return Response.ok().build();
    }

}
