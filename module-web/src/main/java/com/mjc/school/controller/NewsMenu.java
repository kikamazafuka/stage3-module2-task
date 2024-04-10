package com.mjc.school.controller;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.command.BaseCommand;
import com.mjc.school.controller.command.authorCommandImpl.*;
import com.mjc.school.controller.command.newsCommandImpl.*;
import com.mjc.school.service.dto.author.AuthorDtoRequest;
import com.mjc.school.service.dto.author.AuthorDtoResponse;
import com.mjc.school.service.dto.news.NewsDtoRequest;
import com.mjc.school.service.dto.news.NewsDtoResponse;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Log
@Component
public class NewsMenu {

    public NewsMenu(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController,
                    BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController, Scanner scanner) {
        this.newsController = newsController;
        this.authorController = authorController;
        this.scanner = scanner;
    }

    private final BaseController<NewsDtoRequest, NewsDtoResponse,Long> newsController;
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse,Long> authorController;
    private final Scanner scanner;



    public void start() {
        log.info("News menu start");
        printMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice!=0){
            printMenu();

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> executeCommand(new ReadAllNewsCommand(newsController));
                case 2 -> executeCommand(new ReadByIdNewsCommand(newsController, scanner));
                case 3 -> executeCommand(new CreateNewsCommand(newsController, scanner));
                case 4 -> executeCommand(new UpdateNewsCommand(newsController, scanner));
                case 5 -> executeCommand(new DeleteByIdNewsCommand(newsController, scanner));
                case 6 -> executeCommand(new ReadAllAuthorCommand(authorController));
                case 7 -> executeCommand(new ReadByIdAuthorCommand(authorController, scanner));
                case 8 -> executeCommand(new CreateAuthorCommand(authorController, scanner));
                case 9 -> executeCommand(new UpdateAuthorCommand(authorController, scanner));
                case 10 -> executeCommand(new DeleteByIdAuthorCommand(authorController, scanner));
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Please enter a number between 0 and 10.");
            }
        }
    }

    @CommandHandler
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
