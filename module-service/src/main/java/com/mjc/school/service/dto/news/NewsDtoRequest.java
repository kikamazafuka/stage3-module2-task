package com.mjc.school.service.dto.news;

import lombok.Builder;

@Builder
public record NewsDtoRequest(
         Long id,
         String title,
         String content,
         Long authorId
) {
}
