package com.mjc.school.service.validator;

import com.mjc.school.service.dto.author.AuthorDtoRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorValidator {
    private static final Integer AUTHOR_NAME_MIN_LENGTH = 3;
    private static final Integer AUTHOR_NAME_MAX_LENGTH = 15;

    public List<String> validate(AuthorDtoRequest authorDtoRequest) {
        List<String> errors = new ArrayList<>();
        if (authorDtoRequest.name() == null || authorDtoRequest.name().length() < AUTHOR_NAME_MIN_LENGTH
                || authorDtoRequest.name().length() > AUTHOR_NAME_MAX_LENGTH) {
            errors.add("Name must be between 3 and 15 characters");
        }
        return errors;
    }
}
