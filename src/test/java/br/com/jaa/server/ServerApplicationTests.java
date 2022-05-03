package br.com.jaa.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
class ServerApplicationTests {

	@Test
	void contextLoads() {
		String start = "OK";
		ServerApplication.main(new String[] {});
		Assertions.assertEquals("OK", start);
	}

}
