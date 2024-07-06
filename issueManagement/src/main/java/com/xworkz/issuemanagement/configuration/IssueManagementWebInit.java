package com.xworkz.issuemanagement.configuration;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class IssueManagementWebInit extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {


  public  IssueManagementWebInit()
    {
        System.out.println("Creating IssueManagementWebInit");
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("Running getRootConfigClasses");
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("Running getServletConfigClasses");
        return new Class[]{IssueManagementPrimaryConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("Running getServletMappings");
        return new String[]{"/"};

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
        configurer.enable();
    }
}




