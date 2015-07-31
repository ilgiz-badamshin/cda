package ru.cg.cda.uds.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Badamshin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "device")
public class UdsDevice {
  @XmlElement
  private  String id;
  @XmlElement
  private  String name;
  @XmlElement
  private  String description;
  @XmlElement
  private  String model;
  @XmlElement
  private  String protocol;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }
}
