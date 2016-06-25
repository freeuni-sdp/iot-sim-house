package ge.edu.freeuni.sdp.iot.simulators.house.services;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
/**
 * Created by nika on 6/25/16.
 */

@Path("ping")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class Ping {

    @GET
    public Response get() {
        return Response.ok().build();
    }

}
