package pl.bartlomiejpietrzyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "pl.bartlomiejpietrzyk")
public class GamingHostingApplication {
    public static void main(String[] args) {
        SpringApplication.run(GamingHostingApplication.class, args);
    }

}