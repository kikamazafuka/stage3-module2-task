package com.mjc.school.service.dto.news;

//@Builder
public record NewsDtoRequest(
         Long id,
         String title,
         String content,
         Long authorId
) {
}
