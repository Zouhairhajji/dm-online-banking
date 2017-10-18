/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil;

import com.google.common.base.Predicate;
import static com.google.common.base.Predicates.or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author zouhairhajji
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                             
          .build();        
    }

    private Predicate<String> postPaths() {
        return or(regex("/users/*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Users Management API")
                .description("Users management")
                .termsOfServiceUrl("https://github.com/Zouhairhajji/test-ing3")
                .contact("hajji.zouhair@outlook.fr").license("Zouhair License")
                .licenseUrl("hajji.zouhair@outlook.fr").version("1.0").build();
    }

}
