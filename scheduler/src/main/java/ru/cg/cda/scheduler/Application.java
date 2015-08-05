package ru.cg.cda.scheduler;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import ru.cg.cda.scheduler.service.DeviceSyncService;
import ru.cg.cda.scheduler.service.UserSyncService;

/**
 * @author Badamshin
 */
@Component
public class Application {

  public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/scheduler.xml");

    DeviceSyncService deviceSyncService = ctx.getBean("deviceSyncService", DeviceSyncService.class);
    deviceSyncService.sync();
    System.out.append("deviceSyncService ok");

    UserSyncService userSyncService = ctx.getBean("userSyncService", UserSyncService.class);
    userSyncService.sync();
    System.out.append("userSyncService ok");
  }
}
