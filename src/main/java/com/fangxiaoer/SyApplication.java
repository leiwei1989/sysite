package com.fangxiaoer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={"classpath:mykaptcha.xml"})
public class SyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyApplication.class, args);
	}
}
