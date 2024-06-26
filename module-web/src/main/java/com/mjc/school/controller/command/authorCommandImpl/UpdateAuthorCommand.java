package com.mjc.school.controller.command.authorCommandImpl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.BaseCommand;
import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.dto.author.AuthorDtoResponse;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UpdateAuthorCommand implements BaseCommand {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse,Long> authorController;
    private final Scanner scanner;


    public UpdateAuthorCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.authorController = authorController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println("Enter author ID to update:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter new name:");
        String name = scanner.nextLine();

        AuthorDtoRequest authorDto = new AuthorDtoRequest(id, name);
        authorController.update(authorDto);
    }
}
