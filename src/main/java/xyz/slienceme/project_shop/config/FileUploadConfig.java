package xyz.slienceme.project_shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileUploadConfig extends WebMvcConfigurationSupport {

    @Value("${file.staticAccessPath}")
    String staticAccessPath;
    @Value("${file.fileupload}")
    String fileupload;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取项目所在位置
        Path path = Paths.get("");
        String parentPathString = path.toAbsolutePath().toString();
        //                          访问路径                         图片本地地址
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:"+parentPathString+fileupload);
        //这两个是配置swagger的，如果项目使用了swagger就需要配置，否则无法使用swagger
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/","classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/","classpath:/META-INF/resources/webjars/");
    }
}
