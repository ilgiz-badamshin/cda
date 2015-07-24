package ru.cg.cda.database.bean;

import javax.persistence.*;

/**
 * @author Badamshin
 */
@Entity
@Table(name = "tableName")
public class LinkUserRole {
  private Long id;
  private Long userId;
  private Long roleId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "user_id")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column(name = "role_id")
  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }
}
