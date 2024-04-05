package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.news.NewsDtoRequest;
import com.mjc.school.service.dto.news.NewsDtoResponse;
import com.mjc.school.service.exceptions.NewsServiceException;
import com.mjc.school.service.mapper.NewsModelMapper;
import com.mjc.school.service.validator.NewsValidator;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse,Long> {
    private BaseRepository<NewsModel, Long> newsRepository;
    private NewsValidator newsValidator;
    private final NewsModelMapper newsModelMapper = Mappers.getMapper(NewsModelMapper.class);

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository, NewsValidator newsValidator) {
        this.newsRepository = newsRepository;
        this.newsValidator = newsValidator;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsModelMapper.modelListToDtoList(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return newsRepository.readById(id).map(newsModelMapper::modelToDto)
                .orElseThrow(() -> new NewsServiceException("Error getting news by id", "GET_BY_ID_ERROR"));
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        List<String> errors = newsValidator.validate(createRequest);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Create validation failed: " + errors);
        }
        try {
            NewsModel newsModel = newsRepository.create(newsModelMapper.dtoToModel(createRequest));
            return newsModelMapper.modelToDto(newsModel);
        } catch (Exception e){
            throw new NewsServiceException("Error creating news", "CREATE_ERROR");
        }
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        List<String> errors = newsValidator.validate(updateRequest);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Update validation failed: " + errors);
        }
        try {
            Optional<NewsModel> existingNews = newsRepository.readById(updateRequest.id());
            if (existingNews.isPresent()) {
                NewsModel newsModel = existingNews.get();
                newsModel.setTitle(updateRequest.title());
                newsModel.setContent(updateRequest.content());
                newsModel.setAuthorId(updateRequest.authorId());
                NewsModel updatedNews = newsRepository.update(newsModel);
                return newsModelMapper.modelToDto(updatedNews);
            }
        } catch (Exception e){
            throw new NewsServiceException("Error updating news", "UPDATE_ERROR");
        }
        throw new NewsServiceException("Updated news doesn't exist", "UPDATE_ERROR");
    }

    @Override
    public boolean deleteById(Long id) {
        if (newsRepository.existById(id)){
            return newsRepository.deleteById(id);
        } else {
            throw new NewsServiceException("Deleted news doesn't exist", "DELETE_ERROR");
        }
    }
}
