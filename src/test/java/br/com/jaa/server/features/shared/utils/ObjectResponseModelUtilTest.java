package br.com.jaa.server.features.shared.utils;

import br.com.jaa.server.core.util.ExceptionUtil;
import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ObjectResponseModelUtilTest {

    @Autowired
    ObjectResponseModelUtil objectResponseModelUtil;

    @Test
    void testObjectResponseModelMessage() {
        String message = "TESTE";
        HttpStatus httpStatus = HttpStatus.OK;

        ObjectResponseModel<Object> responseModel = objectResponseModelUtil
                .getObjectResponse(httpStatus, message);

        Assertions.assertEquals(httpStatus.value(), responseModel.getStatus());
        Assertions.assertEquals(message, responseModel.getMessage());
        Assertions.assertNull(responseModel.getData());
    }

    @Test
    void testObjectResponseModelData() {
        Long data = 100L;
        HttpStatus httpStatus = HttpStatus.OK;

        ObjectResponseModel<Long> responseModel = objectResponseModelUtil
                .getObjectResponse(httpStatus, data);

        Assertions.assertEquals(httpStatus.value(), responseModel.getStatus());
        Assertions.assertEquals(data, responseModel.getData());
        Assertions.assertNull(responseModel.getMessage());
    }

    @Test
    void testObjectResponseModelMessageData() {
        Long data = 100L;
        String message = "TESTE";
        HttpStatus httpStatus = HttpStatus.OK;

        ObjectResponseModel<Long> responseModel = objectResponseModelUtil
                .getObjectResponse(httpStatus, message, data);

        Assertions.assertEquals(httpStatus.value(), responseModel.getStatus());
        Assertions.assertEquals(message, responseModel.getMessage());
        Assertions.assertEquals(data, responseModel.getData());
    }

}
