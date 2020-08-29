package com.exception;

import com.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestControllerAdvice
@EnableWebMvc
public class CustomExceptionHandler {

  @ExceptionHandler(CustomNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ResponseDTO> handleNotFoundException(CustomNotFoundException ex) {
    return new ResponseEntity(new ResponseDTO("NO CONTENT"), HttpStatus.NOT_FOUND);
  }

  /*@ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDTO> handleException(Exception ex) {
    return ResponseEntity.unprocessableEntity().body(new ResponseDTO("NO CONTENT", "204"));
  }*/


}
