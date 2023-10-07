package com.groupD.server.config;

import com.groupD.server.security.AuthArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public AuthArgumentResolver authArgumentResolver() {
        return new AuthArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

//    private @Inject RequestMappingHandlerAdapter adapter;
//
//    @PostConstruct
//    public void prioritizeCustomArgumentMethodHandlers () {
//        List<HandlerMethodArgumentResolver> argumentResolvers =
//                new ArrayList<>(adapter.getArgumentResolvers ());
//        List<HandlerMethodArgumentResolver> customResolvers =
//                adapter.getCustomArgumentResolvers ();
//        argumentResolvers.removeAll (customResolvers);
//        argumentResolvers.addAll (0, customResolvers);
//        adapter.setArgumentResolvers (argumentResolvers);
//    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE");
//    }

}
