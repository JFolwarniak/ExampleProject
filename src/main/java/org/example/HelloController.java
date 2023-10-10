package org.example;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private WeatherMapper mapper;

    public HelloController(WeatherMapper mapper) {
        this.mapper = mapper;
    }


    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/alt")
    public String index2() {
        return "Test";
    }

    @GetMapping("/alt2")
    public String index3(@RequestParam("parametr") String tekst) {
        return tekst;
    }

    @PostMapping("/alt3")
    public Temperature index4(@RequestBody Temperature temperatura2) {
        return temperatura2;
    }

    @GetMapping(value = "/alt4", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test1() {
        RestTemplate restTemplate = new RestTemplate();

        String fooResourceUrl
                = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true&hourly=temperature_2m,relativehumidity_2m,windspeed_10m";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }

    @GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    public String weather() {
        RestTemplate restTemplate = new RestTemplate();

        String fooResourceUrl
                = "https://danepubliczne.imgw.pl/api/data/synop/station/lodz";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }

    @PostMapping("/temp")
    public Double temperature() {
        JSONObject jo = new JSONObject(weather());
        Double temp = jo.getDouble("temperatura");
        Temperature temperature = new Temperature(temp);
        mapper.save(temperature);
        return temp;

    }


}
