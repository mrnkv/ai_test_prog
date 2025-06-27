package org.neoedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TestProgApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestProgApplication.class, args);
	}

}
