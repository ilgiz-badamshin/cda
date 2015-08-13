package ru.cg.cda.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.cg.cda.admin.dto.DeviceDTO;
import ru.cg.cda.admin.service.DeviceService;


/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

  @Autowired
  private DeviceService deviceService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<DeviceDTO> getDevices() {
    return deviceService.getDevices();
  }

  @RequestMapping(value = "/{deviceId}", method = RequestMethod.GET)
  public DeviceDTO getDevice(@PathVariable Long deviceId) {
    return deviceService.getDevice(deviceId);
  }
}
