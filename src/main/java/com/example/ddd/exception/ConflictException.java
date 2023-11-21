package com.example.ddd.exception;

public class ConflictException extends RuntimeException {
  public ConflictException(String message) {
    super(message);
  }
}
