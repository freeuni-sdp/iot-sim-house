package ge.edu.freeuni.sdp.iot.simulators.house.core;

import ge.edu.freeuni.sdp.iot.simulators.house.worker.TemperatureInfoSender;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.ws.rs.client.ClientBuilder;

/**
 * Created by Nika Doghonadze
 */
@WebListener
public class ContextListener  implements ServletContextListener {
    private static String url = "https://iot-room-thermometer.herokuapp.com/webapi";
    private static int UPDATE_INTERVAL = 10;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new TemperatureInfoSender(url, UPDATE_INTERVAL, ClientBuilder.newClient()).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
