package com.yuji.localfood;

import org.springframework.boot.SpringApplication;

public class TestCleanApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(CleanApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
