package com.controller;

import com.exception.CustomNotFoundException;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dto.ResponseDTO;
import com.dto.TempResponse;
import com.services.WeatherReportService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

  @Autowired
  private WeatherReportService weatherReportService;

  /**
  *
  */
  @GetMapping("/temperature/{city}")
  public ResponseEntity<ResponseDTO> getTemperature(@PathVariable("city") String city) {

    TempResponse response = weatherReportService.getCityTemperature(city);
    if(response == null){
      return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
    }

    return ResponseEntity.accepted().body(new ResponseDTO(response, HttpStatus.ACCEPTED.getReasonPhrase(), HttpStatus.ACCEPTED.value()));
  }
}
