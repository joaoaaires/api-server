package br.com.jaa.server.features;

import br.com.jaa.server.features.shared.models.ObjectResponseModel;
import br.com.jaa.server.features.shared.utils.ObjectResponseModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private ObjectResponseModelUtil objectResponseModelUtil;

    @GetMapping(value = "")
    public ResponseEntity<ObjectResponseModel<Map<String, Object>>> index() {
        ObjectResponseModel<Map<String, Object>> responseModel = objectResponseModelUtil.getObjectResponse(
                HttpStatus.OK,
                "Server Application!",
                new HashMap<>()
        );
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<ObjectResponseModel<Map<String, Object>>> test() {
        ObjectResponseModel<Map<String, Object>> responseModel = objectResponseModelUtil.getObjectResponse(
                HttpStatus.OK,
                "Server Application Test!",
                new HashMap<>()
        );
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

}
