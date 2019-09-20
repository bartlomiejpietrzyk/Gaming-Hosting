package com.github.bartlomiejpietrzyk;

import io.swagger.models.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.github.bartlomiejpietrzyk")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Server API")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/*"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact();
        contact.setName("Bartlomiej Pietrzyk");
        contact.setEmail("bapietrzyk@gmail.com");
        contact.setUrl("http://github.com/bartlomiejpietrzyk");
        return new ApiInfoBuilder()
                .title("Gaming Hosting Service")
                .description("Pro players gaming platform")
                .contact(String.valueOf(contact))
                .build();
    }


}