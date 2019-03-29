package io.pivotal.cronos.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public String hello() {
    	System.out.println("****inside hello****");
        return "Hello, the time at the server is now " + new Date() + "\n";
    }
}
