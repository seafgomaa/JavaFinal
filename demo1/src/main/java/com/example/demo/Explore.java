package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Explore {

    @GetMapping("/explore")
    public List<Pyramid> main() {

        PyramidCSVDAO pDAO = new PyramidCSVDAO();
        List<Pyramid> pyramids = pDAO.readFromCSV();
        
        return  pyramids;
        }

    @GetMapping("/explore1")
    public Pyramid main(@RequestParam(defaultValue="0") String i) {
        int ind = Integer.parseInt(i);
        PyramidCSVDAO pDAO = new PyramidCSVDAO();
        List<Pyramid> pyramids = pDAO.readFromCSV();

        
        return pyramids.get(ind) ;
        }
    }

