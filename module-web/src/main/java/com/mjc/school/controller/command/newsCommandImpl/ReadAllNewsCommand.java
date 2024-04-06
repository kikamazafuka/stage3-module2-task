package com.mjc.school.controller.command.newsCommandImpl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.BaseCommand;
import com.mjc.school.service.dto.news.NewsDtoRequest;
import com.mjc.school.service.dto.news.NewsDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class ReadAllNewsCommand implements BaseCommand {

    private final BaseController<NewsDtoRequest, NewsDtoResponse,Long> newsController;

    public ReadAllNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute() {
        newsController.readAll();
    }
}
