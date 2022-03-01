//package xyz.slienceme.project_shop.config.interceptor;
////TODO 先不设置限制
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//
//@Configuration
//public class HeaderTokenInterceptor extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public SecurityInterceptor securityInterceptor() {
//        return new SecurityInterceptor();
//    }
//
//    @Bean
//    public SecurityInterceptorApp securityInterceptorApp() {
//        return new SecurityInterceptorApp();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration adminInterceptor = registry.addInterceptor(securityInterceptor());
//        adminInterceptor.excludePathPatterns("/admin/login")
//                .excludePathPatterns("/admin/log")
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
//        //拦截所有路径
//        adminInterceptor.addPathPatterns("/admin/**");
//
//        InterceptorRegistration appInterceptor = registry.addInterceptor(securityInterceptorApp());
//        appInterceptor.excludePathPatterns("/app/getCode")
//                .excludePathPatterns("/app/member")
//                .excludePathPatterns("/app/usertype")
//                .excludePathPatterns("/app/login")
//                .excludePathPatterns("/app/record")
//                .excludePathPatterns("/app/getRank")
//                .excludePathPatterns("/app/out")
//                .excludePathPatterns("/app/weekRank")
//                .excludePathPatterns("/app/weekEvaluate")
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
//        //拦截所有路径
//        appInterceptor.addPathPatterns("/app/**");
//
//    }
//
//}
