package com.mjc.school.service.aspects;

import com.mjc.school.service.dto.news.NewsDtoRequest;
import com.mjc.school.service.validator.NewsValidator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NewsValidationAspect {
    private final NewsValidator newsValidator;

    public NewsValidationAspect(NewsValidator newsValidator) {
        this.newsValidator = newsValidator;
    }

    @Before("@annotation(com.mjc.school.service.annotation.NewsValidate) && args(newsDtoRequest)")
    public void validateNewsDtoRequest(NewsDtoRequest newsDtoRequest) {
        newsValidator.validateNewsDto(newsDtoRequest);
    }
}
