package br.com.jaa.server.features.shared.utils;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ObjectResponseModelUtil {

    public <T> ObjectResponseModel<T> getObjectResponse(HttpStatus status, String message) {
        return this.getObjectResponse(status, message, null);
    }

    public <T> ObjectResponseModel<T> getObjectResponse(HttpStatus status, T data) {
        return this.getObjectResponse(status, null, data);
    }

    public <T> ObjectResponseModel<T> getObjectResponse(HttpStatus status, String message, T data) {
        ObjectResponseModel<T> response = new ObjectResponseModel<>();
        response.setStatus(status.value());
        response.setMessage(message);
        response.setData(data);
        return response;
    }

}
