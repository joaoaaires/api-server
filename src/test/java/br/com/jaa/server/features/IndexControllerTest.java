package br.com.jaa.server.features;

import br.com.jaa.server.core.security.TestSecurityConfig;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TestSecurityConfig.class
)
public class IndexControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String url;

    @BeforeEach
    void setUp() {
        url = "http://localhost:" + port + "/";
    }

    @Test
    void index() {
        ResponseEntity<ObjectResponseModel> responseEntity = testRestTemplate.getForEntity(
                url, ObjectResponseModel.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ObjectResponseModel objectResponseModel = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK.value(), objectResponseModel.getStatus());
        Assertions.assertNotNull(objectResponseModel.getData());
        Assertions.assertNotNull(objectResponseModel.getMessage());
    }

}
