package com.uuhnaut69.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeter")
public class GreeterController {

  @GetMapping
  public String greeter() {
    return "Hello";
  }
}
