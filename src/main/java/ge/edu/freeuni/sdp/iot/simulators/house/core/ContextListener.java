package ge.edu.freeuni.sdp.iot.simulators.house.core;

import ge.edu.freeuni.sdp.iot.simulators.house.worker.TemperatureInfoSender;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Nika Doghonadze
 */
@WebListener
public class ContextListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        new TemperatureInfoSender("ASdA", 123).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
