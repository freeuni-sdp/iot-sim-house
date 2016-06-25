package ge.edu.freeuni.sdp.iot.simulators.house.services;

import ge.edu.freeuni.sdp.iot.simulators.house.core.Repository;
import ge.edu.freeuni.sdp.iot.simulators.house.core.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.simulators.house.model.*;

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
    @Path("house/{house_id}/heating")
    public HouseHeatingSwitches getAllSwitches(@PathParam("house_id") String houseId) {
        Repository repository = getRepository();

        House house = repository.findHouse(houseId);
        if (house == null)
            throw new NotFoundException();

        HouseHeatingSwitches res = new HouseHeatingSwitches(houseId);

        for (Floor f : house.getAllFloors()) {
            HeatingSwitch heatingSwitch = f.getHeatingSwitch();
            res.add(new HeatingSwitchMessage(f.getFloorId(), heatingSwitch.isOn()));
        }

        return res;
    }

    @GET
    @Path("/house/{house_id}/floor/{floor_id}/heating")
    public HeatingSwitchMessage getSwitchStatus(@PathParam("house_id") String houseId,
                                                @PathParam("floor_id") String floorId) {
        Repository repository = getRepository();

        House house = repository.findHouse(houseId);
        if (house == null)
            throw new NotFoundException();

        Floor floor = house.getFloor(floorId);

        if (floor == null)
            throw new NotFoundException();

        HeatingSwitch heatingSwitch = floor.getHeatingSwitch();
        return new HeatingSwitchMessage(floorId, heatingSwitch.isOn());
    }

    @PUT
    @Path("/house/{house_id}/floor/{floor_id}/heating")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response switchOn(@PathParam("house_id") String houseId,
                             @PathParam("floor_id") String floorId,
                             SwitchOnRequest request) {
        Repository repository = getRepository();

        House house = repository.findHouse(houseId);
        if (house == null)
            throw new NotFoundException();

        Floor floor = house.getFloor(floorId);
        if (floor == null)
            throw new NotFoundException();

        floor.getHeatingSwitch().turnOn(request.getPeriod());

        return Response.ok().build();
    }

    @DELETE
    @Path("/house/{house_id}/floor/{floor_id}/heating")
    public Response switchOff(@PathParam("house_id") String houseId,
                              @PathParam("floor_id") String floorId) {
        Repository repository = getRepository();

        House house = repository.findHouse(houseId);
        if (house == null)
            throw new NotFoundException();

        Floor floor = house.getFloor(floorId);
        if (floor == null)
            throw new NotFoundException();

        floor.getHeatingSwitch().turnOff();

        return Response.ok().build();
    }

}