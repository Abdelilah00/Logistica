////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                //.consumes(Set.of(MediaType.MULTIPART_FORM_DATA_VALUE))
                .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token", "Authorization", "Header"))));

    }

    private ApiInfo apiInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("Logistica API");
        apiInfoBuilder.description("Logistica E-commerce REST API");
        apiInfoBuilder.version("0.0.1-SNAPSHOT");
        apiInfoBuilder.contact(new Contact("Abdelilah Dehaoui", "https://www.linkedin.com/in/abdelilah-dehaoui-4a1a29190", "abdelilah0dehaoui@gmail.com"));
        return apiInfoBuilder.build();
    }
}
