package fr.vertours.assessms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AssessMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessMsApplication.class, args);
	}

}
