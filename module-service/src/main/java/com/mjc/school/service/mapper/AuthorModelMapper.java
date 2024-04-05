package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.dto.author.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorModelMapper {
    List<AuthorDtoResponse> modelListToDtoList(List<AuthorModel> authorModelList);
    AuthorDtoResponse modelToDto(AuthorModel authorModel);
    @Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)
    })
    AuthorModel dtoToModel(AuthorDtoRequest authorDtoRequest);

}
