package ge.edu.freeuni.sdp.iot.simulators.house.services;

import ge.edu.freeuni.sdp.iot.simulators.house.core.Repository;
import ge.edu.freeuni.sdp.iot.simulators.house.core.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.simulators.house.model.*;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Nikoloz on 06/25/16.
 */
@Path("/house/{house_id}")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class MacService {
    private static String URL = "https://iot-router.herokuapp.com/webapi";

    protected Repository getRepository() {
        return RepositoryFactory.inMemoryRepositoryInstance();
    }

    @GET
    @Path("/available")
    public Response isOwnerAtHome(@PathParam("house_id") String houseId) {
        Repository repository = getRepository();

        House house = repository.findHouse(houseId);
        if (house == null)
            throw new NotFoundException();

        Client client = ClientBuilder.newClient();
        Response resp = client.target(URL + "/houses/" + houseId + "/available")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();

        return resp;
    }
}
