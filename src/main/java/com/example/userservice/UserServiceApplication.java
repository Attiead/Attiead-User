package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
//@EnableDiscoveryClient
//@RequestMapping("/user")
//@RestController
public class UserServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }
}
//    @GetMapping("/")
//    public String index() {
//        return "string";
//    }
//
//    @GetMapping("/info")
//    public String info(@Value("${message.content}") String content) {
//
//        return "User 서비스의 기본 content는 = " + content;
//    }
//
//    @GetMapping("/test")
//    public String test(@Value("${test}") String test) {
//
//        return "User 서비스의 기본 test = " + test;
//    }

