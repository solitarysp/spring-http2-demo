package com.lehtanh98.http2.h2c;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @GetMapping("/")
  public String hello() {
    return "hello";
  }
}
