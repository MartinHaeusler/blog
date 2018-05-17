package org.example.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @RequestMapping(method = RequestMethod.GET, path = "/api/hello-world")
    @ResponseBody
    public String sayHello(){
        return "Hello World!";
    }

}
