package it.carmelolagamba.ita.covid19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import it.carmelolagamba.ita.covid19.config.ApplicationProperties;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class }, scanBasePackages = { "it.carmelolagamba" })
@EnableConfigurationProperties
public class SpringBootApp implements CommandLineRunner {

	@Autowired
	private ApplicationProperties config;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println(" ---------------------------------------------------- ");
		System.out.println(" -- ");
		System.out.println(" --   Environment: " + config.getEnvironment());
		System.out.println(" --   App name: " + config.getName());
		System.out.println(" --   Rest API available at: " + config.getUrl() + ":" + String.valueOf(config.getPort()));
		System.out.println(" -- ");
		System.out.println(" ---------------------------------------------------- ");
	}
}
