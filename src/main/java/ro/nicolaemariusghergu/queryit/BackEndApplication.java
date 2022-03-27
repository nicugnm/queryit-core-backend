package ro.nicolaemariusghergu.queryit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ro.nicolaemariusghergu.queryit")
public class BackEndApplication {

    public static final String FRONTEND_CORE_ADDRESS = "http://localhost:60028";

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

}
