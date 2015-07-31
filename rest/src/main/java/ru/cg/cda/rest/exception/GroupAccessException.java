package ru.cg.cda.rest.exception;

/**
 * @author Badamshin
 */
public class GroupAccessException extends RuntimeException {
  private static final long serialVersionUID = -5496996225177844815L;

  public GroupAccessException(String message) {
    super(message);
  }
}
