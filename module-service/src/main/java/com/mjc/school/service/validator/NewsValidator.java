package com.mjc.school.service.validator;

import com.mjc.school.service.dto.news.NewsDtoRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsValidator {
    private static final Integer NEWS_TITLE_MIN_LENGTH = 5;
    private static final Integer NEWS_TITLE_MAX_LENGTH = 30;
    private static final Integer NEWS_CONTENT_MIN_LENGTH = 5;
    private static final Integer NEWS_CONTENT_MAX_LENGTH = 255;

    public List<String> validate(NewsDtoRequest newsDTO) {
        List<String> errors = new ArrayList<>();
        if (newsDTO.title() == null || newsDTO.title().length() < NEWS_TITLE_MIN_LENGTH
                || newsDTO.title().length() > NEWS_TITLE_MAX_LENGTH) {
            errors.add("Title must be between 5 and 30 characters");
        }
        if (newsDTO.content() == null || newsDTO.content().length() < NEWS_CONTENT_MIN_LENGTH
                || newsDTO.content().length() > NEWS_CONTENT_MAX_LENGTH) {
            errors.add("Content must be between 5 and 255 characters");
        }
        return errors;
    }
}
