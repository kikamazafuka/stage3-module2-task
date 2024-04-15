package com.mjc.school.service.validator;

import com.mjc.school.service.dto.author.AuthorDtoRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthorValidator extends BaseValidator{

    private static final String AUTHOR_NAME = "Author name";
    private static final String AUTHOR_ID = "Author id";
    private static final Integer AUTHOR_NAME_MIN_LENGTH = 3;
    private static final Integer AUTHOR_NAME_MAX_LENGTH = 15;

    public void validateAuthorDto(AuthorDtoRequest dtoRequest) {
        validateString(dtoRequest.name(), AUTHOR_NAME, AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH);
    }

    public void validateAuthorId(Long authorId) {
        validateNumber(authorId, AUTHOR_ID);
    }

}
