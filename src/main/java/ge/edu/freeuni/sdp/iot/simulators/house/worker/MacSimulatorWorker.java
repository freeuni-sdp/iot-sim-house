package ge.edu.freeuni.sdp.iot.simulators.house.worker;

import ge.edu.freeuni.sdp.iot.simulators.house.core.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.simulators.house.model.*;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Koko on 25.06.2016.
 */
public class MacSimulatorWorker extends Thread {
    private String uri;
    private int interval;
    private Client client;
    private Map<String, HouseMac> houseMacMap;

    public MacSimulatorWorker(String uri, int interval, Client client) {
        this.uri = uri;
        this.interval = interval;
        this.client = client;
        houseMacMap = new HashMap<>();
    }

    @Override
    public void run() {
        boolean add = false;
        while (true) {
            sleep(interval);
            Iterable<House> allHouses = RepositoryFactory.inMemoryRepositoryInstance().getAllHouses();
            for (House house : allHouses) {
                Random random = new Random();
                add = random.nextBoolean();
                if (add){
                    String addr = getRandomStringWithLength(8);
                    String name = getRandomStringWithLength(10);
                    String id = "id";
                    MacObject m_obj = new MacObject(addr,name,id);
                    String url = getUrl(house.getHouseId());
                    Response res = client.target(url  + "/connect")
                            .request(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .post(Entity.json(m_obj.toStringWithoutID()));
                    JSONObject m_json = new JSONObject(res);
                    String mac_id = m_json.getString("mac_id");
                    m_obj.setId(mac_id);
                    HouseMac houseMac;
                    if (!houseMacMap.containsKey(house.getHouseId())) {
                        houseMac = new HouseMac();
                        houseMacMap.put(house.getHouseId(), houseMac);
                    } else {
                        houseMac = houseMacMap.get(house.getHouseId());
                    }
                    houseMac.add(m_obj);
                } else {

                }
            }
        }
    }

    private String getRandomStringWithLength(int length){
        char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789-".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private String getUrl(String houseId) {
        return uri + "/houses/" + houseId;
    }

    private static void sleep(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
