package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SpringBootApplication(scanBasePackages = "com.example.demo")
public class GucciProjectApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(GucciProjectApplication.class, args);
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/summernoteImage/**")
                .addResourceLocations("file:///opt/tomcat9/webapps/ROOT/WEB-INF/classes/static/summernote2/");
    }
}
