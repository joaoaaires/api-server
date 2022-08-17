package br.com.jaa.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
/*
@OpenAPIDefinition(
        info = @Info(
                title = "ApiServer"
        )
)
@SecurityScheme(
        name = "authorization",
        scheme = "token",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER
)
*/
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // It will set UTC timezone
    }

}
