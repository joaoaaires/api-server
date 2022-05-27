package br.com.jaa.server.core.util;

import br.com.jaa.server.core.exceptio.ApiServerException;
import br.com.jaa.server.core.exceptio.ApiServerRuntimeException;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtil {

    public void getRuntimeException(String message) {
        throw new ApiServerRuntimeException(message);
    }

}
