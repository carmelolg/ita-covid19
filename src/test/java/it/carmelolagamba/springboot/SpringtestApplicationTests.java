package it.carmelolagamba.springboot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import it.carmelolagamba.ita.covid19.SpringBootApp;

@SpringBootTest(classes = SpringBootApp.class)
class SpringtestApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
