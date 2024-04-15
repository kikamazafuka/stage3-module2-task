package com.mjc.school.controller;

import com.mjc.school.controller.command.BaseCommand;
import com.mjc.school.controller.command.authorCommandImpl.*;
import com.mjc.school.controller.command.newsCommandImpl.*;
import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.dto.author.AuthorDtoResponse;
import com.mjc.school.service.dto.news.NewsDtoRequest;
import com.mjc.school.service.dto.news.NewsDtoResponse;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class NewsMenu {

    private final BaseController<NewsDtoRequest, NewsDtoResponse,Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse,Long> authorController;
    private final Scanner scanner;

    public NewsMenu(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                    BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.newsController = newsController;
        this.authorController = authorController;
        this.scanner = new Scanner(System.in);
    }


    public void start() {
        while (true){
            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> executeCommand(new ReadAllNewsCommand(newsController));
                case 2 -> executeCommand(new ReadByIdNewsCommand(newsController));
                case 3 -> executeCommand(new CreateNewsCommand(newsController));
                case 4 -> executeCommand(new UpdateNewsCommand(newsController));
                case 5 -> executeCommand(new DeleteByIdNewsCommand(newsController));
                case 6 -> executeCommand(new ReadAllAuthorCommand(authorController));
                case 7 -> executeCommand(new ReadByIdAuthorCommand(authorController));
                case 8 -> executeCommand(new CreateAuthorCommand(authorController));
                case 9 -> executeCommand(new UpdateAuthorCommand(authorController));
                case 10 -> executeCommand(new DeleteByIdAuthorCommand(authorController));
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice! Please enter a number between 0 and 10.");
            }
        }
    }

    private void executeCommand(BaseCommand command) {
        command.execute();
    }

    private static void printMenu(){
        System.out.println("Enter the number of operation:");
        System.out.println("1 - Get all news.");
        System.out.println("2 - Get news by id.");
        System.out.println("3 - Create news.");
        System.out.println("4 - Update news.");
        System.out.println("5 - Remove news by id.");
        System.out.println("6 - Get all authors.");
        System.out.println("7 - Get author by id.");
        System.out.println("8 - Create author.");
        System.out.println("9 - Update author.");
        System.out.println("10 - Remove author by id.");
        System.out.println("0 - Exit.");
    }
}
