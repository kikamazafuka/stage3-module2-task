package com.mjc.school.service.validator;

import com.mjc.school.service.exceptions.ValidatorException;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

public class BaseValidator {

    public void validateNumber(Long id, String parameter) {
        if (id == null || id < 1) {
            throw new ValidatorException(
                    String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), parameter, parameter, id));
        }
    }

    public void validateString(String value, String parameter, int minLength, int maxLength) {
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
