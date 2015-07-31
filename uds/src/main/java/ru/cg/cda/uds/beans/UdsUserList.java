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
@XmlRootElement(name = "users")
public class UdsUserList {
  @XmlElement(name = "user")
  private List<UdsUser> users;

  public List<UdsUser> getUsers() {
    return users;
  }

  public void setUsers(List<UdsUser> users) {
    this.users = users;
  }
}
