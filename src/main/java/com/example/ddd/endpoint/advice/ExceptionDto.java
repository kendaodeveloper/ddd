package com.example.ddd.endpoint.advice;

public class ExceptionDto {
  private Integer code;
  private String message;

  public ExceptionDto(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }
}
