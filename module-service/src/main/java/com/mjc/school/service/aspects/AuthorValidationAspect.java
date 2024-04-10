package com.mjc.school.service.aspects;

import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.validator.AuthorValidator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorValidationAspect {

    private final AuthorValidator authorValidator;

    public AuthorValidationAspect(AuthorValidator authorValidator) {
        this.authorValidator = authorValidator;
    }

    @Before("@annotation(com.mjc.school.service.annotation.AuthorValidate) && args(authorDtoRequest)")
    public void validateAuthorDtoRequest(AuthorDtoRequest authorDtoRequest) {
        authorValidator.validateAuthorDto(authorDtoRequest);
    }
}
