package com.mjc.school.service.dto.author;

import lombok.Builder;

@Builder
public record AuthorDtoRequest(
        Long id,
        String name
) {
}
