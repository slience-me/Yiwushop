package xyz.slienceme.project_shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import xyz.slienceme.project_shop.config.interceptor.SecurityInterceptor;
import xyz.slienceme.project_shop.config.interceptor.SecurityInterceptorApp;

import java.io.File;

@Configuration
public class FileUploadConfig extends WebMvcConfigurationSupport {

    @Value("${file.staticAccessPath}")
    String staticAccessPath;
    @Value("${file.fileupload}")
    String fileupload;
    @Autowired
    private SecurityInterceptor securityInterceptor;
    @Autowired
    private SecurityInterceptorApp securityInterceptorApp;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        ApplicationHome ah = new ApplicationHome(getClass());
        File parentPathStringLinux = ah.getSource().getParentFile();
        //获取项目所在位置
        /*Path path = Paths.get("");
        String parentPathString = path.toAbsolutePath().toString();*/
        //                          访问路径                         图片本地地址
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + parentPathStringLinux + fileupload);
        //这两个是配置swagger的，如果项目使用了swagger就需要配置，否则无法使用swagger
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration adminInterceptor = registry.addInterceptor(securityInterceptor);
        adminInterceptor.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/rolelist/**");
        adminInterceptor.addPathPatterns("/**");

        InterceptorRegistration appInterceptor = registry.addInterceptor(securityInterceptorApp);
        appInterceptor.excludePathPatterns("/app/getCode")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        appInterceptor.addPathPatterns("/app/**");
    }

}
