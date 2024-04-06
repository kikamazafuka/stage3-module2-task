package com.mjc.school.controller.command.newsCommandImpl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.BaseCommand;
import com.mjc.school.service.dto.news.NewsDtoRequest;
import com.mjc.school.service.dto.news.NewsDtoResponse;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteByIdNewsCommand implements BaseCommand {
    private final BaseController<NewsDtoRequest, NewsDtoResponse,Long> newsController;
    private final Scanner scanner;

    public DeleteByIdNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController, Scanner scanner) {
        this.newsController = newsController;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter news ID to delete:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        newsController.deleteById(id);
    }
}
