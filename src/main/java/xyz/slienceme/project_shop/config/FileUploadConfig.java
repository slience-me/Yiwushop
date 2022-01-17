package xyz.slienceme.project_shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@Configuration
public class FileUploadConfig extends WebMvcConfigurationSupport {

    @Value("${file.staticAccessPath}")
    String staticAccessPath;
    @Value("${file.fileupload}")
    String fileupload;

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

    /*@Bean
    MultipartConfigElement multipartConfigElement() {
        ApplicationHome ah = new ApplicationHome(getClass());
        File parentPathStringLinux = ah.getSource().getParentFile();
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(String.valueOf(parentPathStringLinux));
        return factory.createMultipartConfig();
    }*/


    /**
     * 设置上传文件的类型
     * 注意MultipartConfigElement中的方法MaxFileSize和MaxRequestSize都是DataSize中的类型，需要转换，可以用ctrl来查看MultipartConfigFactory和DataSize中的方法
     *
     * @return
     */
    /*@Bean
    public MultipartConfigElement multpartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件大小200mb
        factory.setMaxFileSize(DataSize.ofMegabytes(200L));
        //设置总上传数据大小1GB
        factory.setMaxRequestSize(DataSize.ofGigabytes(1L));
        return factory.createMultipartConfig();

    }*/
}
