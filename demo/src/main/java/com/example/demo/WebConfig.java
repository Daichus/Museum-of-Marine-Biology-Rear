package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOriginPatterns("http://localhost:5173")  // 前端地址
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 添加 OPTIONS 方法
            .allowedHeaders("*")
            .exposedHeaders("*")  // 允許暴露所有 header
            .allowCredentials(true)
            .maxAge(3600);  // 快取預檢請求結果的時間（秒）
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:./uploads/")
            .setCachePeriod(3600)  // 設置快取時間
            .resourceChain(true);  // 開啟資源鏈優化
  }
}