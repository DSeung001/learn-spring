package com.example.jpabookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpabookshopApplication {

	public static void main(String[] args) {
		Hello hello = new Hello();
		hello.setName("1234");
		System.out.println("hello.getName() = " + hello.getName());

		SpringApplication.run(JpabookshopApplication.class, args);
	}
}
