package com.mjc.school.controller.command.authorCommandImpl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.BaseCommand;
import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.dto.author.AuthorDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class ReadAllAuthorCommand implements BaseCommand {
    public ReadAllAuthorCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.authorController = authorController;
    }

    private final BaseController<AuthorDtoRequest, AuthorDtoResponse,Long> authorController;


    @Override
    public void execute() {
        authorController.readAll();
    }
}
