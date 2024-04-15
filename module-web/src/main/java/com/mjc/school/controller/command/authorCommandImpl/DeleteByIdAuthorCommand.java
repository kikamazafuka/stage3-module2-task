package com.mjc.school.controller.command.authorCommandImpl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.BaseCommand;
import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.dto.author.AuthorDtoResponse;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteByIdAuthorCommand implements BaseCommand {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse,Long> authorController;
    private final Scanner scanner;

    public DeleteByIdAuthorCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.authorController = authorController;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println("Enter author Id: ");
        Long authorId = scanner.nextLong();
        scanner.nextLine();

        authorController.deleteById(authorId);
    }
}
