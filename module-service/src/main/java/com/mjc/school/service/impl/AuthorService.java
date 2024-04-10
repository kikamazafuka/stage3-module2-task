package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.AuthorValidate;
import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.dto.author.AuthorDtoResponse;
import com.mjc.school.service.exceptions.NewsServiceException;
import com.mjc.school.service.mapper.AuthorModelMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse,Long> {

    private final BaseRepository<AuthorModel, Long> authorRepository;

    private final AuthorModelMapper authorModelMapper = Mappers.getMapper(AuthorModelMapper.class);

    public AuthorService(BaseRepository<AuthorModel, Long> authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorModelMapper.modelListToDtoList(authorRepository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        return authorRepository.readById(id).map(authorModelMapper::modelToDto)
                .orElseThrow(() -> new NewsServiceException("Error getting news by id", "GET_BY_ID_ERROR"));
    }

    @Override
    @AuthorValidate
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        try {
            AuthorModel authorModel = authorRepository.create(authorModelMapper.dtoToModel(createRequest));
            return authorModelMapper.modelToDto(authorModel);
        } catch (Exception e){
            throw new NewsServiceException("Error creating news", "CREATE_ERROR");
        }
    }

    @Override
    @AuthorValidate
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        try {
            Optional<AuthorModel> existingAuthor = authorRepository.readById(updateRequest.id());
            if (existingAuthor.isPresent()) {
                AuthorModel authorModel = existingAuthor.get();
                authorModel.setName(updateRequest.name());
                AuthorModel updatedAuthor = authorRepository.update(authorModel);
                return authorModelMapper.modelToDto(updatedAuthor);
            }
        } catch (Exception e){
            throw new NewsServiceException("Error updating news", "UPDATE_ERROR");
        }
        throw new NewsServiceException("Updated news doesn't exist", "UPDATE_ERROR");
    }

    @Override
    public boolean deleteById(Long id) {
        if (authorRepository.existById(id)){
            return authorRepository.deleteById(id);
        } else {
            throw new NewsServiceException("Deleted news doesn't exist", "DELETE_ERROR");
        }
    }
}
