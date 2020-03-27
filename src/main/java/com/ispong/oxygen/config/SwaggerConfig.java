package com.ispong.oxygen.config;

import io.swagger.models.auth.In;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * swagger 配置
 * http://localhost:8888/spring-oxygen/swagger-ui.html
 * 要做到的效果 指定的模块划分
 *
 * @author ispong
 * @since 0.0.1
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig{


//    @Autowired
//    private TypeResolver typeResolver;

    /**
     * 设置生成接口的地址
     */
    private String controllerPath = "com.ispong.oxygen.module";

    /**
     * 创建apiInfo
     *
     * @since 0.0.1
     */
    private ApiInfo initApiInfo() {
        
        return new ApiInfoBuilder()
                .title("spring-oxygen documents")
                .build();
    }

    /**
     * 创建apiKey
     *
     * @since 0.0.1
     */
    private ApiKey initApiKey() {

        return new ApiKey("Authorization", HttpHeaders.AUTHORIZATION, In.HEADER.name());
    }

    @Bean
    public Docket petApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("spring-oxygen")
                .apiInfo(initApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(controllerPath))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
//                .alternateTypeRules(
//                        newRule(typeResolver.resolve(DeferredResult.class,
//                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
//                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET,
//                        newArrayList(new ResponseMessageBuilder()
//                                .code(500)
//                                .message("500 message")
//                                .responseModel(new ModelRef("Error"))
//                                .build()))
                .securitySchemes(newArrayList(initApiKey()))
                .securityContexts(newArrayList(securityContext()))
                .enableUrlTemplating(false)
//                .globalOperationParameters(
//                        newArrayList(new ParameterBuilder()
//                                .name("someGlobalParameter")
//                                .description("Description of someGlobalParameter")
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("query")
//                                .required(true)
//                                .build()))
//                .tags(new Tag("Pet Service", "All apis relating to pets"))
//                .additionalModels(typeResolver.resolve(AdditionalModel.class))
                ;
    }

    /**
     * @since 0.0.1
     */
    List<SecurityReference> defaultAuth() {

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference("Authorization", authorizationScopes));
    }


    /**
     * 上下文路径选择器  这是哪些路劲拦截
     * 使用java的正则表达式
     * 目前不拦截
     * 
     * @since 0.0.1
     */
    private SecurityContext securityContext() {

        List<String> excludeUrlPaths = Arrays.asList(
                "(/user/userSignIn)",
                "(/user/userSignUp)",
                "(/file/show/)",
                "(/file/download/)",
                "(/freecode)");

        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("(?!" + Strings.join(excludeUrlPaths, '|') + ").*"))
                .build();
    }

//    @Bean
//    SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//                .clientId("test-app-client-id")
//                .clientSecret("test-app-client-secret")
//                .realm("test-app-realm")
//                .appName("test-app")
//                .scopeSeparator(",")
//                .additionalQueryStringParams(null)
//                .useBasicAuthenticationWithAccessCodeGrant(false)
//                .build();
//    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(-1)
                .defaultModelExpandDepth(-1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }
}
