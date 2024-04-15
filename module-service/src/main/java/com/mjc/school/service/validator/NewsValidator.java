package com.mjc.school.service.validator;

import com.mjc.school.service.dto.news.NewsDtoRequest;
import com.mjc.school.service.exceptions.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Component
public class NewsValidator extends BaseValidator{

    private static final String NEWS_CONTENT = "News content";
    private static final String AUTHOR_ID = "Author id";
    private static final String NEWS_TITLE = "News title";
    private static final Integer NEWS_CONTENT_MIN_LENGTH = 5;
    private static final Integer NEWS_CONTENT_MAX_LENGTH = 255;
    private static final Integer NEWS_TITLE_MIN_LENGTH = 5;
    private static final Integer NEWS_TITLE_MAX_LENGTH = 30;
    private static final Integer MAX_AUTHOR_ID = 20;

    public void validateNewsDto(NewsDtoRequest dtoRequest) {
        validateString(dtoRequest.title(), NEWS_TITLE, NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
        validateString(
                dtoRequest.content(), NEWS_CONTENT, NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
        validateAuthorId(dtoRequest.authorId());
    }

    public void validateAuthorId(Long authorId) {
        validateNumber(authorId, AUTHOR_ID);
        if (authorId > MAX_AUTHOR_ID) {
            throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), authorId));
        }
    }
}
