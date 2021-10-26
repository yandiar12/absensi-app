package com.yandiar.absensi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author YAR
 */

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select().apis(RequestHandlerSelectors.basePackage("com.yandiar.absensi.controllers"))
            .paths(PathSelectors.any())
            .build().apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Absensi API")
            .description("Absensi API")
            .termsOfServiceUrl("yandiar.com")
            .licenseUrl("yandiar.com")
            .contact(new Contact("Dev", "", "yandiar.rohman@gmail.com"))
            .license("License")
            .version("1.0.0")
            .build();
}
}
