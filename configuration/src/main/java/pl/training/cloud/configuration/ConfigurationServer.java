package pl.training.cloud.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// copy java jce jars to jre/lib/security
// export ENCRYPT_KEY=SYMMETRICKEY
@SpringBootApplication
public class ConfigurationServer {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationServer.class, args);
	}

}