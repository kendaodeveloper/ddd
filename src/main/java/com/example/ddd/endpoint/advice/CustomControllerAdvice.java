package com.example.ddd.endpoint.advice;

import com.example.ddd.exception.BadRequestException;
import com.example.ddd.exception.ConflictException;
import com.example.ddd.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ExceptionDto> handleBadRequestException(BadRequestException e) {
    return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST.value(), e.toString()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException e) {
    return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND.value(), e.toString()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ExceptionDto> handleConflictException(ConflictException e) {
    return new ResponseEntity<>(new ExceptionDto(HttpStatus.CONFLICT.value(), e.toString()), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ExceptionDto> handleInternalServerErrorException(Throwable e) {
    return new ResponseEntity<>(new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
