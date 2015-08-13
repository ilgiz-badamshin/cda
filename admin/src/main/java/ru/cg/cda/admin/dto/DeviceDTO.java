package ru.cg.cda.admin.dto;

/**
 * @author Badamshin
 */
public class DeviceDTO {
  private Long id;
  private String udsId;
  private String udsUserId;
  private Long userId;
  private String name;
  private String description;
  private String model;
  private Boolean deleted;
  private UserDTO user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUdsId() {
    return udsId;
  }

  public void setUdsId(String udsId) {
    this.udsId = udsId;
  }

  public String getUdsUserId() {
    return udsUserId;
  }

  public void setUdsUserId(String udsUserId) {
    this.udsUserId = udsUserId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }
}
