package ru.cg.cda.database.enums;

/**
 * @author Badamshin
 */
public enum UserStatus {
  AVAILABLE(1),
  BUSY(2),
  DO_NOT_DISTURB(3),
  AWAY(4),
  UNAVAILABLE(5),
  VACATION(6),
  UNKNOWN(7);

  private int statusId;

  UserStatus(int statusId) {
    this.statusId = statusId;
  }

  public int getStatusId() {
    return statusId;
  }
}