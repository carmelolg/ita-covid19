package it.carmelolagamba.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.carmelolagamba.ita.covid19.SpringBootApp;
import it.carmelolagamba.ita.covid19.controller.SystemController;

@SpringBootTest(classes = SpringBootApp.class)
public class SystemControllerTest {

	@Autowired
	private SystemController systemController;
	
	@Test
	public void testPing() {
		String pong = systemController.ping();
		assertEquals("pong", pong, "Ping failed");
	}
	
}
