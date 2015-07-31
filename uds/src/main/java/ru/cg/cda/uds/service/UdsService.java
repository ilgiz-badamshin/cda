package ru.cg.cda.uds.service;

import java.util.List;

import ru.cg.cda.uds.beans.UdsDevice;
import ru.cg.cda.uds.beans.UdsUser;

/**
 * @author Badamshin
 */
public interface UdsService {
  List<UdsUser> getUsers() ;
  UdsUser  getUser(String userId) ;
  List<UdsDevice> getDevices(String userId) ;
  UdsDevice getDevice(String userId, String deviceId) ;
}
