package ru.cg.cda.rest.exception;

/**
 * @author Badamshin
 */
public class UnknownUserException extends RuntimeException {
  private static final long serialVersionUID = -5496996225177844815L;

  public UnknownUserException(String message) {
    super(message);
  }
}
