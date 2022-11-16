package ai.rezo.mshr.ManiHRBOOT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ManiHrbootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ManiHrbootApplication.class, args);
	}

}