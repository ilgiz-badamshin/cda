package ru.cg.cda.database.bean;

import javax.persistence.*;

/**
 * @author Badamshin
 */
@Entity
@Table(name = "access_to_group")
public class AccessToGroup {
  private Long id;
  private Long roleId;
  private Long groupId;
  private Long accessLevelId;
  private Role role;
  private Group group;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "role_id")
  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  @Column(name = "group_id")
  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  @Column(name = "access_level_id")
  public Long getAccessLevelId() {
    return accessLevelId;
  }

  public void setAccessLevelId(Long accessLevelId) {
    this.accessLevelId = accessLevelId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", insertable = false, updatable = false)
  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }
}
