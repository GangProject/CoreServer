package com.gang.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.util.StringUtils;

import javax.servlet.MultipartConfigElement;

/**
 * Created by Junwoo on 2017-04-27.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.http.multipart",ignoreUnknownFields = false)
public class MultipartProperties {
    /* MultipartConfigElement에서 사용할 속성을 관리하는 클래스*/
    private boolean enabled = true;
    private String location;
    private String maxFileSize = "20000MB";
    private String maxRequestSize = "10MB";
    private String fileSizeThreshold ="0";
    private boolean resolveLazily = false;

    public MultipartConfigElement createMultipartConfig(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        if(StringUtils.hasText(this.fileSizeThreshold)){
            factory.setFileSizeThreshold(this.fileSizeThreshold);
        }
        if(StringUtils.hasText(this.location)){
            factory.setLocation(this.location);
        }
        if(StringUtils.hasText(this.maxRequestSize)){
            factory.setMaxRequestSize(this.maxRequestSize);
        }
        if(StringUtils.hasText(this.maxFileSize)){
            factory.setMaxFileSize(this.maxFileSize);
        }
        return factory.createMultipartConfig();
    }
}