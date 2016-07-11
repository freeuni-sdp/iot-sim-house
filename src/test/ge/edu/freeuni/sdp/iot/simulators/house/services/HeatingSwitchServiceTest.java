package ge.edu.freeuni.sdp.iot.simulators.house.services;

import ge.edu.freeuni.sdp.iot.simulators.house.core.Repository;
import ge.edu.freeuni.sdp.iot.simulators.house.core.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.simulators.house.model.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by nika on 7/11/16.
 */
public class HeatingSwitchServiceTest extends JerseyTest {

    @Mock private Repository repository;
    private House house;
    private String houseId;
    private Floor floor1;
    private Floor floor2;
    private String floorId1;
    private String floorId2;
    private HeatingSwitch switch1;
    private HeatingSwitch switch2;


    @Override
    protected Application configure() {
        return new ResourceConfig(HeatingSwitchService.class);
    }

    @Before
    public void setUpChild() throws Exception {
        houseId = "3c5afb74-2e82-4f10-9931-89187fe47adf";
        floorId1 = "1";
        floorId2 = "2";
        floor1 = new Floor(houseId, floorId1);
        floor2 = new Floor(houseId, floorId2);
        switch1 = new HeatingSwitch();
        switch2 = new HeatingSwitch();
        floor1.setHeatingSwitch(switch1);
        floor2.setHeatingSwitch(switch2);

        Map<String, Floor> floorMap = new HashMap<>();
        floorMap.put(floorId1, floor1);
        floorMap.put(floorId2, floor2);
        house = new House(houseId, floorMap);

        MockitoAnnotations.initMocks(this);
        when(repository.findHouse(houseId)).thenReturn(house);

        RepositoryFactory.setTestInstance(repository);
        RepositoryFactory.setTestMode(true);
    }

    @After
    public void tearDownChild() throws Exception {
        RepositoryFactory.setTestMode(false);
    }

    @Test
    public void testGetAllSwitches_found() throws Exception {
        HouseHeatingSwitches expected = new HouseHeatingSwitches(houseId);
        HeatingSwitchMessage message1 = new HeatingSwitchMessage(floorId1, true);
        HeatingSwitchMessage message2 = new HeatingSwitchMessage(floorId2, false);
        expected.add(message1);
        expected.add(message2);

        switch1.turnOn(1000);
        switch2.turnOff();

        Response response = target("/house/" + houseId + "/heating").request().get();
        assertEquals(200, response.getStatus());

        JSONObject respObject = new JSONObject(response.readEntity(String.class));
        HouseHeatingSwitches actual = HouseHeatingSwitches.fromJson(respObject);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllSwitches_notFound() throws Exception {
        when(repository.findHouse(houseId)).thenReturn(null);

        Response response = target("/house/" + houseId + "/heating").request().get();
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testGetSwitchStatus_found() throws Exception {
        HeatingSwitchMessage expected = new HeatingSwitchMessage(floorId1, true);
        switch1.turnOn(1000);

        Response response = target("/house/" + houseId + "/floor/" + floorId1 + "/heating")
                .request().get();
        assertEquals(200, response.getStatus());

        JSONObject respObject = new JSONObject(response.readEntity(String.class));
        HeatingSwitchMessage actual = HeatingSwitchMessage.fromJson(respObject);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSwitchStatus_notFound() throws Exception {

        Response response = target("/house/" + houseId + "/floor/"
                + floorId1 + floorId2 + "/heating")
                .request().get();
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testSwitchOn_found() throws Exception {
        switch1.turnOff();
        SwitchOnRequest request = new SwitchOnRequest(1000);
        Response response = target("/house/" + houseId + "/floor/" + floorId1 + "/heating")
                .request().put(Entity.entity(request, MediaType.APPLICATION_JSON));

        assertEquals(200, response.getStatus());
        assertTrue(switch1.isOn());
    }

    @Test
    public void testSwitchOn_notFound() throws Exception {
        SwitchOnRequest request = new SwitchOnRequest(1000);
        Response response = target("/house/" + houseId + "/floor/"
                + floorId1 + floorId2 + "/heating")
                .request().put(Entity.entity(request, MediaType.APPLICATION_JSON));
        assertEquals(404, response.getStatus());
    }

    @Test
    public void switchOff_found() throws Exception {
        switch1.turnOn(1000);
        Response response = target("/house/" + houseId + "/floor/" + floorId1 + "/heating")
                .request().delete();

        assertEquals(200, response.getStatus());
        assertFalse(switch1.isOn());
    }

    @Test
    public void switchOff_notFound() throws Exception {
        Response response = target("/house/" + houseId + "/floor/"
                + floorId1 + floorId2 + "/heating")
                .request().delete();

        assertEquals(404, response.getStatus());
    }

}