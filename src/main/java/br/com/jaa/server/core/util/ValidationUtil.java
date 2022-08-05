package br.com.jaa.server.core.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationUtil {

    public boolean isNotNullNotEmpty(String value) {
        return this.isNotNull(value) && isNotEmpty(value);
    }

    public boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public boolean isNotNull(Object object) {
        return object != null;
    }

    public <T> boolean isNotEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }

}
