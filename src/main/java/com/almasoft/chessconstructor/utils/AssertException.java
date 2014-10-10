package com.almasoft.chessconstructor.utils;

public class AssertException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public AssertException(String message, Throwable cause) {
    super(message, cause);
  }
  public AssertException(String message) {
    super(message);
  }
}
