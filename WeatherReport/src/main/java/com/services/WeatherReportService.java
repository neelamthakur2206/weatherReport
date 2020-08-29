package com.services;

import com.dto.ResponseDTO;
import com.dto.TempResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.dto.TempResponse;

@Service
//@ConfigurationProperties("app")
public class WeatherReportService {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${app.endpointUrl}")
  public String endpointUrl;

  @Value("${app.apiKey}")
  public String apiKey;

  /**
   *
   */

  public TempResponse getCityTemperature(String cityName) {
    ResponseEntity<Map> response = callOpenWeatherMapApi(endpointUrl,cityName, apiKey);

    ObjectMapper objectMapper = new ObjectMapper();
    TempResponse tempResponse = null;
    if (response != null && response.getStatusCode().value() == 200) {
      Map item = (Map)response.getBody().get("main");
      tempResponse = new TempResponse(Double.valueOf(item.get("temp").toString()));
    }

    //tempResponse = objectMapper.readValue(response.getBody().get("main").toString(), TempResponse.class);

    return tempResponse;
  }


  private ResponseEntity<Map> callOpenWeatherMapApi(String endpointUrl, String cityName, String apiKey) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    ResponseEntity<Map> response = null;
    try {
          UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpointUrl)
              .queryParam("q", cityName)
              .queryParam("appid", apiKey);

          HttpEntity<?> entity = new HttpEntity<>(headers);
          response = restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          entity,
          Map.class);
    }catch(Exception ex){
        System.out.println("Exception occured..");
    }
    return response;
  }
}
