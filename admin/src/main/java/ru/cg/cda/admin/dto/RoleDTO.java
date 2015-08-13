package ru.cg.cda.admin.dto;

import java.util.List;

/**
 * @author Badamshin
 */
public class RoleDTO {
  private Long id;
  private String name;
  private List<GroupDTO> groups;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<GroupDTO> getGroups() {
    return groups;
  }

  public void setGroups(List<GroupDTO> groups) {
    this.groups = groups;
  }
}
