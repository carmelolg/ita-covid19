package it.carmelolagamba.ita.covid19;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static Logger log = LoggerFactory.getLogger(SpringBootApp.class);

	@Autowired
	private ApplicationProperties config;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

	@Override
	public void run(String... args) {
		log.info(" ---------------------------------------------------- ");
		log.info(" -- ");
		log.info(" --   Environment: {}", config.getEnvironment());
		log.info(" --   App name: {}", config.getName());
		log.info(" --   Rest API available at: {}:{}", config.getUrl(),
				(config.getPort() != null) ? String.valueOf(config.getPort()) : "");
		log.info(" -- ");
		log.info(" ---------------------------------------------------- ");
	}
}
