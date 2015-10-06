package ru.cg.cda.scheduler.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ru.cg.cda.scheduler.service.DeviceSyncService;
import ru.cg.cda.scheduler.service.UserSyncService;

/**
 * @author Badamshin
 */
@Component
public class Scheduler {
  private static Long COUNTER = (long) 0;

  Logger logger = LoggerFactory.getLogger(Scheduler.class);
  @Autowired
  UserSyncService userSyncService;
  @Autowired
  DeviceSyncService deviceSyncService;

  @Scheduled(fixedDelay = 60000)
  public void syncUds() {
    userSyncService.sync();
    deviceSyncService.sync();
  }
}
