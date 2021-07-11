package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CtoFService {

    @GetMapping("app")
    public String convertCtoF(@RequestParam(defaultValue = "10") String input) {
        Double c=Double.parseDouble(input);
        Double fahrenheit;
        fahrenheit = ((c * 9) / 5) + 32;

        String result = "Input "+ c+ ". Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
        return  result ;
    }


}

