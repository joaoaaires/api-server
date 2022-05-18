package br.com.jaa.server.fixtures;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import org.springframework.http.HttpStatus;

public class ObjectResponseFixture {

    public static  <T> ObjectResponseModel<T> getObjectResponse(HttpStatus status, String message) {
        return getObjectResponse(status, message, null);
    }

    public static <T> ObjectResponseModel<T> getObjectResponse(HttpStatus status, T data) {
        return getObjectResponse(status, null, data);
    }

    public static <T> ObjectResponseModel<T> getObjectResponse(HttpStatus status, String message, T data) {
        ObjectResponseModel<T> response = new ObjectResponseModel<>();
        response.setStatus(status.value());
        response.setMessage(message);
        response.setData(data);
        return response;
    }

}
