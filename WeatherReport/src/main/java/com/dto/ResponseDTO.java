package com.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(value = Include.NON_ABSENT, content = Include.NON_ABSENT)
public class ResponseDTO {

  private Object data;

  private String message;

  private int code;


  public ResponseDTO(Object data) {
    this.data = data;
  }

  public ResponseDTO(Object data, String message, int code) {
    this.data = data;
    this.message = message;
    this.code = code;
  }

  public ResponseDTO(String message, int code) {
    this.message = message;
    this.code = code;
  }


}
