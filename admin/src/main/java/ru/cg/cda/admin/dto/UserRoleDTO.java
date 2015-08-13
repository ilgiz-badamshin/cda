package ru.cg.cda.admin.dto;

/**
 * @author Badamshin
 */
public class UserRoleDTO {
  private Long id;
  private Long userId;
  private Long roleId;
  private String roleName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getRole() {
    return roleName;
  }

  public void setRole(String roleName) {
    this.roleName = roleName;
  }
}
