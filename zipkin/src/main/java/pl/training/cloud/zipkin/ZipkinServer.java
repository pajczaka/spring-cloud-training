package pl.training.cloud.zipkin;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zipkin2.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class ZipkinServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZipkinServer.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}