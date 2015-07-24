package ru.cg.cda.database.enums;

/**
 * @author Badamshin
 */
public enum AccessLevel {
  INVISIBLE(1),
  VISIBLe(2),
  CALLABLE(3);

  private int accessLevel;

  AccessLevel(int accessLevel) {
    this.accessLevel = accessLevel;
  }

  public int getAccessLevel() {
    return accessLevel;
  }
}