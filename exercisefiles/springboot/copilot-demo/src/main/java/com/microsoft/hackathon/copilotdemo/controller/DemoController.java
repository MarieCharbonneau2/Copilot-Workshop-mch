package com.microsoft.hackathon.copilotdemo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping; // Add this import statement
/* 
* Create a GET operation to return the value of a key passed as query parameter. 
* If the key is not passed, return "key not passed".
* If the key is passed, return "hello <key>".
*/

@RestController
public class DemoController {

    /**
     * Controller class for handling demo requests.
     */
    
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "key", required = false) String key) {
        if (key == null) {
            return "key not passed";
        }
        return "hello " + key;
    }

    /**
     * Calculates the difference between two dates passed in the dd-MM-yyyy format.
     * 
     * @param date1 The first date in dd-MM-yyyy format.
     * @param date2 The second date in dd-MM-yyyy format.
     * @return The difference between the two dates in days.
     */
    @GetMapping("/diffdates")
    public long calculateDateDifference(@RequestParam("date1") String date1, @RequestParam("date2") String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate1 = LocalDate.parse(date1, formatter);
        LocalDate localDate2 = LocalDate.parse(date2, formatter);
        return ChronoUnit.DAYS.between(localDate1, localDate2);
    }

    /**
     * Valide le numéro de téléphone.
     *
     * @param phone Le numéro de téléphone à valider.
     * @return true si le numéro de téléphone est valide, sinon false.
     */
    @GetMapping("/validatephone")
    public boolean validatePhone(@RequestParam("phone") String phone) {
        return phone.matches("^\\+34[6|7|9]\\d{8}$");
    }

    //Based on existing colors.json file under resources, given the name of the color as path parameter, return the hexadecimal code. If the color is not found, return 404
    @GetMapping("/color/{name}")
    public ResponseEntity<String> color(@PathVariable("name") String name) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("colors.json");
        ObjectMapper objectMapper = new ObjectMapper();
        // create JsonNode from mapper
        JsonNode rootNode = objectMapper.readTree(inputStream);
        for (JsonNode color : rootNode) {
            // if color name is found, return the hex code
            if (color.get("color").asText().equals(name)) {
                return new ResponseEntity<String>(color.get("code").get("hex").asText(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("Color not found", HttpStatus.NOT_FOUND);
    }

    // Create a function that will call the api https://api.chucknorris.io/jokes/random and return a joke
    @GetMapping("/joke")
    public ResponseEntity<String> joke() throws IOException {
        // call https://api.chucknorris.io/jokes/random
        String url = "https://api.chucknorris.io/jokes/random";
        // create a new RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        // make the request, expect a String response
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        // return the response
        return response;
    }

    //Given a url as query parameter, parse it and return the protocol, host, port, path and query parameters. The response should be in Json format.
    @GetMapping("/parseurl")
    public String parseUrl(@RequestParam("url") String url) throws IOException {
        if (url == null || url.isEmpty()) {
            return "url not passed";
        }
        URL urlObj = new URL(url);
        String protocol = urlObj.getProtocol();
        String host = urlObj.getHost();
        int port = urlObj.getPort();
        String path = urlObj.getPath();
        String query = urlObj.getQuery();
        return "{ \"protocol\": \"" + protocol + "\", \"host\": \"" + host + "\", \"port\": \"" + port + "\", \"path\": \"" + path + "\", \"query\": \"" + query + "\" }";
    
    }
}
