package ge.edu.freeuni.sdp.iot.simulators.house.services;

import ge.edu.freeuni.sdp.iot.simulators.house.core.Repository;
import ge.edu.freeuni.sdp.iot.simulators.house.core.RepositoryFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Nika Doghonadze
 */
@Path("/houses")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class HouseService {

    protected Repository getRepository() {
        return RepositoryFactory.inMemoryRepositoryInstance();
    }

    @GET
    @Path("/{house_id}/floors/temperature")
    public Response get(@PathParam("house_id") String houseId) {
        //TODO return real temperatures
        return Response.ok().build();
    }

    @GET
    @Path("/{house_id}/floors/{floor_id}/temperature")
    public Response put(@PathParam("house_id") String houseId, @PathParam("floor_id") String floorId) {
        //TODO return real temperature
        return Response.ok().build();
    }

}
