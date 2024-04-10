package com.mjc.school.controller.command.newsCommandImpl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.controller.command.BaseCommand;
import com.mjc.school.service.dto.news.NewsDtoRequest;
import com.mjc.school.service.dto.news.NewsDtoResponse;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateNewsCommand implements BaseCommand {
    private final BaseController<NewsDtoRequest, NewsDtoResponse,Long> newsController;
    private final Scanner scanner;

    public CreateNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController, @CommandParam Scanner scanner) {
        this.newsController = newsController;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter news title:");
        String title = scanner.nextLine();
        System.out.println("Enter news content:");
        String content = scanner.nextLine();
        System.out.println("Enter author ID:");
        Long authorId = scanner.nextLong();

//        NewsDtoRequest newsDTO = NewsDtoRequest.builder()
//                .title(title)
//                .content(content)
//                .authorId(authorId).build();
        NewsDtoRequest newsDTO = new NewsDtoRequest(null, title,content,authorId);
        NewsDtoResponse createdNews = newsController.create(newsDTO);
        System.out.println("Created News: " + createdNews);
    }
}
