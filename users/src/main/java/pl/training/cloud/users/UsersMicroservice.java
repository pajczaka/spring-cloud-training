package pl.training.cloud.users;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class UsersMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(UsersMicroservice.class, args);
	}

}
