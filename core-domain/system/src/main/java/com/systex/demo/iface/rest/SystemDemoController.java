package com.systex.demo.iface.rest;

import com.systex.f68w.Library;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class SystemDemoController {

    @GetMapping("")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/lib")
    public String testLibrary() {
        return new Library().newMethodDemo();
    }



}
