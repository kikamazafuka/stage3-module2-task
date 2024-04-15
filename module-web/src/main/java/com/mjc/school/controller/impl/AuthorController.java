package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.dto.author.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse,Long> {

    private final BaseService<AuthorDtoRequest,AuthorDtoResponse,Long> authorService;

    @Autowired
    public AuthorController(BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService) {
        this.authorService = authorService;
    }

    @Override
    @CommandHandler
    public List<AuthorDtoResponse> readAll() {
        return authorService.readAll();
    }

    @Override
    @CommandHandler
    public AuthorDtoResponse readById(@CommandParam Long id) {
        return authorService.readById(id);
    }

    @Override
    @CommandHandler
    public AuthorDtoResponse create(@CommandBody AuthorDtoRequest createRequest) {
        return authorService.create(createRequest);
    }

    @Override
    @CommandHandler
    public AuthorDtoResponse update(@CommandBody AuthorDtoRequest updateRequest) {
        return authorService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return authorService.deleteById(id);
    }
}
