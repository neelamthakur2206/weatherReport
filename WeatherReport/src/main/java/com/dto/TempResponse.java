package com.dto;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class TempResponse implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private Double temp;

  //private Double temp_min;

  //private Double temp_max;

  public TempResponse(Double temp){
      this.temp = temp;
  }




}
