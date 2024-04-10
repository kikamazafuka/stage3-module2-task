package com.mjc.school.service.validator;

import com.mjc.school.service.annotation.AuthorValidate;
import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.dto.news.NewsDtoRequest;
import com.mjc.school.service.exceptions.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Component
public class AuthorValidator {

    private static final String AUTHOR_NAME = "Author name";
    private static final String AUTHOR_ID = "Author id";
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

    public void validateAuthorDto(AuthorDtoRequest dtoRequest) {
        validateString(dtoRequest.name(), AUTHOR_NAME, AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH);
//        validateAuthorId(dtoRequest.id());
    }

    public void validateAuthorId(Long authorId) {
        validateNumber(authorId, AUTHOR_ID);
    }

    private void validateNumber(Long id, String parameter) {
        if (id == null || id < 1) {
            throw new ValidatorException(
                    String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), parameter, parameter, id));
        }
    }

    private void validateString(String value, String parameter, int minLength, int maxLength) {
        if (value == null) {
            throw new ValidatorException(
                    String.format(VALIDATE_NULL_STRING.getMessage(), parameter, parameter));
        }
        if (value.trim().length() < minLength || value.trim().length() > maxLength) {
            throw new ValidatorException(
                    String.format(
                            VALIDATE_STRING_LENGTH.getMessage(),
                            parameter,
                            minLength,
                            maxLength,
                            parameter,
                            value));
        }
    }
}
