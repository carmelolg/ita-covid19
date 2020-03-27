package it.carmelolagamba.ita.covid19.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@Configuration
public class LoggerConfig {

    static Logger root = (Logger) LoggerFactory
            .getLogger("org.mongodb.driver");

    static {
        root.setLevel(Level.ERROR);
    }
}
