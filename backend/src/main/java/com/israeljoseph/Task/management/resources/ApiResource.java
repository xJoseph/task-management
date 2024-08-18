package com.israeljoseph.Task.management.resources;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiResource {

    @RequestMapping("/")
    public String index() {
        return "Java API is up and running";
    }
}
