package br.com.jaa.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class  ApplicationTests {

	@Test
	void contextLoads() {
		String start = "OK";
		Application.main(new String[] {});
		Assertions.assertEquals("OK", start);
	}

}
