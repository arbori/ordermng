package io.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-07T11:52:41.130101+01:00[Europe/Lisbon]")
@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.ordermng.api"))
                    .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Swagger Order Manager - OpenAPI 3.0")
            .description("Decskill - Marcelo Arbori  This is a simple exercise, a simple order manager. You should develop an API where users can create and manage orders. Items can be ordered and orders are automatically fulfilled as soon as the item stock allows it.  Specification  The system should be able to provide the following features:      1 - create, read, update and delete and list all entities;      2 - when an order is created, it should try to satisfy it with the current stock.;      3 - when a stock movement is created, the system should try to attribute it to an order that isn't complete;      4 - when an order is complete, send a notification by email to the user that created it;      5 - trace the list of stock movements that were used to complete the order, and vice-versa;      6 - show current completion of each order;      7 - Write a log file with: orders completed, stock movements, email sent and errors.    Entities   - Item                              > name      - StockMovement                              > creationDate               > Item               > quantity      - Order                              > creationDate               > Item               > quantity               > User (who created the order)      - User                              > name               > email  Requirements: - The API should make by java 8 with Spring Boot + Spring JPA or Jave EE + Hibernate, PostgreSQL, GIT, log4j (or other); - You should provide instructions on how to run the project and how to call the routes;")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.11")
            .contact(new Contact("","", "marcelo.arbori@gmail.com"))
            .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Swagger Order Manager - OpenAPI 3.0")
                .description("Decskill - Marcelo Arbori  This is a simple exercise, a simple order manager. You should develop an API where users can create and manage orders. Items can be ordered and orders are automatically fulfilled as soon as the item stock allows it.  Specification  The system should be able to provide the following features:      1 - create, read, update and delete and list all entities;      2 - when an order is created, it should try to satisfy it with the current stock.;      3 - when a stock movement is created, the system should try to attribute it to an order that isn't complete;      4 - when an order is complete, send a notification by email to the user that created it;      5 - trace the list of stock movements that were used to complete the order, and vice-versa;      6 - show current completion of each order;      7 - Write a log file with: orders completed, stock movements, email sent and errors.    Entities   - Item                              > name      - StockMovement                              > creationDate               > Item               > quantity      - Order                              > creationDate               > Item               > quantity               > User (who created the order)      - User                              > name               > email  Requirements: - The API should make by java 8 with Spring Boot + Spring JPA or Jave EE + Hibernate, PostgreSQL, GIT, log4j (or other); - You should provide instructions on how to run the project and how to call the routes;")
                .termsOfService("")
                .version("1.0.11")
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                .contact(new io.swagger.v3.oas.models.info.Contact()
                    .email("marcelo.arbori@gmail.com")));
    }

}
