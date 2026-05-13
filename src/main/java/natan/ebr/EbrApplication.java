package natan.ebr;

import natan.ebr.config.MainEnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EbrApplication {

    public static void main(String[] args) {

        MainEnvLoader.load();

        SpringApplication.run(EbrApplication.class, args);
    }
}
