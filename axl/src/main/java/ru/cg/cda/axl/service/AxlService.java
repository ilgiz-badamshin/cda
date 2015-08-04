package ru.cg.cda.axl.service;

import java.util.List;

import ru.cg.cda.axl.beans.AxlDevice;
import ru.cg.cda.axl.beans.AxlUser;

/**
 * @author Badamshin
 */
public interface AxlService {
  List<AxlDevice> getDevices();

  List<AxlUser> getUsers();
}
