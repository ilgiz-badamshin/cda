package ru.cg.cda.rest.exception;

/**
 * @author Badamshin
 */
public class UserAccessException extends RuntimeException {
  private static final long serialVersionUID = -5496996225177844815L;

  public UserAccessException(String message) {
    super(message);
  }
}
