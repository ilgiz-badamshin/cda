package ru.cg.cda.rest.dto;

import java.util.Date;
import java.util.List;

/**
 * @author Badamshin
 */
public class ChangesDto {
  private List<GroupDTO> groups;
  private List<UserDTO> users;
  private List<Long> deletedGroupIds;
  private List<Long> deletedUserdIds;
  private List<HistoryDTO> histories;
  private Date updatedAt;

  public List<GroupDTO> getGroups() {
    return groups;
  }

  public void setGroups(List<GroupDTO> groups) {
    this.groups = groups;
  }

  public List<UserDTO> getUsers() {
    return users;
  }

  public void setUsers(List<UserDTO> users) {
    this.users = users;
  }

  public List<Long> getDeletedGroupIds() {
    return deletedGroupIds;
  }

  public void setDeletedGroupIds(List<Long> deletedGroupIds) {
    this.deletedGroupIds = deletedGroupIds;
  }

  public List<Long> getDeletedUserdIds() {
    return deletedUserdIds;
  }

  public void setDeletedUserdIds(List<Long> deletedUserdIds) {
    this.deletedUserdIds = deletedUserdIds;
  }

  public List<HistoryDTO> getHistories() {
    return histories;
  }

  public void setHistories(List<HistoryDTO> histories) {
    this.histories = histories;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
