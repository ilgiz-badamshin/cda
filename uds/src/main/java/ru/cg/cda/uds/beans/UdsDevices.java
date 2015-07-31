package ru.cg.cda.uds.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Badamshin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "devices")
public class UdsDevices {
  @XmlElement(name = "device")
  private List<UdsDevice> devices;

  public List<UdsDevice> getDevices() {
    return devices;
  }

  public void setDevices(List<UdsDevice> devices) {
    this.devices = devices;
  }
}
