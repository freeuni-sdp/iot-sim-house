package ge.edu.freeuni.sdp.iot.simulators.house;

import ge.edu.freeuni.sdp.iot.simulators.house.services.Ping;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/**
 * Created by nika on 6/25/16.
 */
public class PingTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(Ping.class);
    }

    @Test
    public void testGetPing() {
        Response actual = target("ping").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
    }

}