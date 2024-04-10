package com.mjc.school.controller.command.authorCommandImpl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.BaseCommand;
import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.dto.author.AuthorDtoResponse;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateAuthorCommand implements BaseCommand {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse,Long> authorController;
    private final Scanner scanner;

    public CreateAuthorCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> newsController, Scanner scanner) {
        this.authorController = newsController;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter author name:");
        String name = scanner.nextLine();

//        AuthorDtoRequest authorDto = AuthorDtoRequest.builder()
//                .name(name)
//                .build();
        AuthorDtoRequest authorDto = new AuthorDtoRequest(null,name);
        AuthorDtoResponse authorDtoResponse = authorController.create(authorDto);
        System.out.println("Created News: " + authorDtoResponse);
    }
}

