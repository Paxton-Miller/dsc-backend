package com.ogms.dsc.config;

import com.ogms.dsc.domain.constants.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;

/**
 * @name: ResourceConfig
 * @description: 挂载静态资源
 * @author: Lingkai Shi
 * @date: 7/17/2024 8:55 PM
 * @version: 1.0
 */
@Configuration
public class ResourceConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/assets/graphJson/**").addResourceLocations("file:" + Constants.GRAPH_JSON_PATH + File.separator);
        registry.addResourceHandler("/assets/resource/**").addResourceLocations("file:" + Constants.RESOURCE_UPLOAD_PATH + File.separator);
        registry.addResourceHandler("/assets/avatar/**").addResourceLocations("file:" + Constants.AVATAR_PATH + File.separator);
        registry.addResourceHandler("/assets/mapLogo/**").addResourceLocations("file:" + Constants.MAPLOGO_PATH + File.separator);
        super.addResourceHandlers(registry);
    }
}
