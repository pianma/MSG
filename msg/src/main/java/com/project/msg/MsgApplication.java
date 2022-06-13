package com.project.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan("com.project")
public class MsgApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgApplication.class, args);
	}

}
