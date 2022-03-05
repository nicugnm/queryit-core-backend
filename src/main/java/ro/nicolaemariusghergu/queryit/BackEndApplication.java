package ro.nicolaemariusghergu.queryit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ro.nicolaemariusghergu.queryit")
public class BackEndApplication {

    public static final String LOCALHOST = "http://localhost:8080";

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

}
