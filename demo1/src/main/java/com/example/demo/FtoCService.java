package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FtoCService {

	@GetMapping("ftc")
	public String convertFtoC(@RequestParam(defaultValue = "10") String input) {
 
		Double fahrenheit = Double.parseDouble(input);
		Double celsius;
		celsius = (fahrenheit - 32) * 5 / 9;
 
		String result = "Input : "+fahrenheit +". Output: \n\nF to C Converter Output: \n\n" + celsius;
		return result;
	}
 

}
