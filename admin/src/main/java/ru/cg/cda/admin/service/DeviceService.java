package ru.cg.cda.admin.service;

import java.util.List;

import ru.cg.cda.admin.dto.DeviceDTO;
import ru.cg.cda.database.bean.Device;


/**
 * @author Badamshin
 */
public interface DeviceService {
  DeviceDTO getDevice(Long deviceId);

  List<DeviceDTO> getDevices();

  DeviceDTO convertDevice(Device device);

  List<DeviceDTO> convertDevices(List<Device> devices);
}
