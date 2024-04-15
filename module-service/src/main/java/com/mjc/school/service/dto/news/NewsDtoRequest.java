package com.mjc.school.service.dto.news;
public record NewsDtoRequest(
         Long id,
         String title,
         String content,
         Long authorId
) {
}
