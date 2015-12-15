package ru.cg.cda.admin.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Badamshin
 */
public class GroupDTO {
  private Long id;
  private String name;
  private Long sort;
  private List<UserDTO> Users = new ArrayList<>();

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

  public Long getSort() {
    return sort;
  }

  public void setSort(Long sort) {
    this.sort = sort;
  }

  public List<UserDTO> getUsers() {
    return Users;
  }

  public void setUsers(List<UserDTO> users) {
    Users = users;
  }
}
