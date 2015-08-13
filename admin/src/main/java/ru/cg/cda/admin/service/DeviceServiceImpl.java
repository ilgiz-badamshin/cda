package ru.cg.cda.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.admin.dto.DeviceDTO;
import ru.cg.cda.database.bean.Device;
import ru.cg.cda.database.dao.DeviceDao;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class DeviceServiceImpl implements DeviceService {
  @Autowired
  public DeviceDao deviceDao;
  @Autowired
  private UserService userService;

  public DeviceDTO getDevice(Long deviceId) {
    return convertDevice(deviceDao.get(deviceId));
  }

  @Override
  public List<DeviceDTO> getDevices() {
    List<Device> devices = deviceDao.loadAll();
    return convertDevices(devices);
  }

  @Override
  public DeviceDTO convertDevice(Device device) {
    if (device == null) {
      return null;
    }
    DeviceDTO deviceDTO = new DeviceDTO();
    deviceDTO.setId(device.getId());
    deviceDTO.setUserId(device.getUserId());
    deviceDTO.setUdsId(device.getUdsId());
    deviceDTO.setUdsUserId(device.getUdsUserId());
    deviceDTO.setName(device.getName());
    deviceDTO.setDescription(device.getName());
    deviceDTO.setDescription(device.getDescription());
    deviceDTO.setModel(device.getModel());
    deviceDTO.setDeleted(device.getDeleted());
    deviceDTO.setUser(userService.convertUser(device.getUser()));
    return deviceDTO;
  }

  @Override
  public List<DeviceDTO> convertDevices(List<Device> devices) {
    List<DeviceDTO> result = new ArrayList<>();
    for (Device device : devices) {
      DeviceDTO deviceDTO = convertDevice(device);
      if (deviceDTO != null) {
        result.add(deviceDTO);
      }
    }
    return result;
  }

}
