package ru.smartbudject.crmbackend.config;

import ch.qos.logback.core.util.StringUtil;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.File;
import java.nio.file.Paths;

@Configuration
@Setter
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadFilesPath;

    @PostConstruct
    public void init(){
        File file = new File(uploadFilesPath);
        if(StringUtil.isNullOrEmpty(uploadFilesPath) || (file.exists() && !file.isDirectory()))
            throw new RuntimeException(String.format("Check upload.path property (%s), its empty or not directory", uploadFilesPath));
        if(!(file.exists() || file.mkdirs())) throw new RuntimeException("Could not make dir " + uploadFilesPath) ;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry
                .addResourceHandler("/upload/**")
                .addResourceLocations("file://" + uploadFilesPath + "/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
