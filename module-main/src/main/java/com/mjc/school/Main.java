package com.mjc.school;

import com.mjc.school.config.ApplicationConfig;
import com.mjc.school.controller.NewsMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        NewsMenu newsMenu = context.getBean(NewsMenu.class);
        newsMenu.start();

    }


}
