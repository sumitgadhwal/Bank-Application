package com.learning.sumit.bankApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8086/swagger-ui/
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis( RequestHandlerSelectors.basePackage("com.learning.sumit.bankApplication"))
                .paths( PathSelectors.any() ) //will only create documentation for apis matching this regex | regex("/v1.*")
                .build();
    }
}
