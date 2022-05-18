package br.com.jaa.server.features.shared.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ObjectResponseModelTest {

    @Test
    void testObjectResponseModel() {
        String message = "Message";
        String data = "Data";

        ObjectResponseModel<String> responseModel = new ObjectResponseModel<>();
        responseModel.setStatus(HttpStatus.OK.value());
        responseModel.setMessage(message);
        responseModel.setData(data);

        Assertions.assertEquals(HttpStatus.OK.value(), responseModel.getStatus());
        Assertions.assertEquals(message, responseModel.getMessage());
        Assertions.assertEquals(data, responseModel.getData());
    }

    @Test
    void testObjectResponseModelChangeStatus() {
        String message = "Message";
        String data = "Data";

        ObjectResponseModel<String> responseModel = new ObjectResponseModel<>();
        responseModel.setStatus(HttpStatus.FORBIDDEN.value());
        responseModel.setMessage(message);
        responseModel.setData(data);

        Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), responseModel.getStatus());
        Assertions.assertEquals(message, responseModel.getMessage());
        Assertions.assertEquals(data, responseModel.getData());
    }

}
