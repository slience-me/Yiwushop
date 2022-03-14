package xyz.slienceme.project_shop.config;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import xyz.slienceme.project_shop.config.interceptor.SecurityInterceptor;

import java.io.File;

/**
 * @author slience_me
 * @since 2022-01-15
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${file.staticAccessPath}")
    String staticAccessPath;
    @Value("${file.fileupload}")
    String fileupload;
    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("易物平台接口文档")
                .description("易物平台接口文档")
                .contact(
                        new Contact("slience_me", "", "")
                )
                .version("1.0.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        ApplicationHome ah = new ApplicationHome(getClass());
        File parentPathStringLinux = ah.getSource().getParentFile();
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + parentPathStringLinux + fileupload);
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration adminInterceptor = registry.addInterceptor(securityInterceptor);
        adminInterceptor.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/rolelist/**")
                .excludePathPatterns("/app/getCode")
                .excludePathPatterns("/admin/upload")
                .excludePathPatterns("/image/file/**");
        adminInterceptor.addPathPatterns("/admin/**", "/app/**");

    }
}