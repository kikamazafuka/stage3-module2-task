package com.mjc.school.controller.command.newsCommandImpl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.BaseCommand;
import com.mjc.school.service.dto.news.NewsDtoRequest;
import com.mjc.school.service.dto.news.NewsDtoResponse;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UpdateNewsCommand implements BaseCommand {
    private final BaseController<NewsDtoRequest, NewsDtoResponse,Long> newsController;
    private final Scanner scanner;

    public UpdateNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController, Scanner scanner) {
        this.newsController = newsController;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter news ID to update:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter new title:");
        String title = scanner.nextLine();
        System.out.println("Enter new content:");
        String content = scanner.nextLine();
        System.out.println("Enter new author ID:");
        Long authorId = scanner.nextLong();

//        NewsDtoRequest newsDTO = NewsDtoRequest.builder()
//                .id(id)
//                .title(title)
//                .content(content)
//                .authorId(authorId)
//                .build();
        NewsDtoRequest newsDTO = new NewsDtoRequest(id, title,content,authorId);
        NewsDtoResponse updatedNews = newsController.update(newsDTO);
        System.out.println("Updated news: " + updatedNews);
    }
}
