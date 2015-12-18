package ru.cg.cda.security.exception;

/**
 * @author Badamshin
 */
public class UserAlreadyExistsException extends Exception {
  private static final long serialVersionUID = -5907637137282295460L;

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
