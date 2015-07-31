package ru.cg.cda.scheduler.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ru.cg.cda.scheduler.service.UserSyncService;

/**
 * @author Badamshin
 */
@Component
public class Sheduler {
  private static Long COUNTER = (long) 0;

  Logger logger = LoggerFactory.getLogger(Sheduler.class);
  @Autowired
  UserSyncService userSyncService;

  @Scheduled(fixedDelay = 10000)
  public void syncUds() {
    logger.error("Test message {} - {}", COUNTER, new Date());
    COUNTER++;
//    userSyncService.sync();
  }
}
