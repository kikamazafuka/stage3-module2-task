package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataSource {

    private final String AUTHOR_PATH = "authors";
    private final String CONTENT_PATH = "content";
    private final String NEWS_PATH = "news";
    private static final DataSource instance = new DataSource();

    private DataSource() {
    }

    public static DataSource getInstance() {
        return instance;
    }

    public List<NewsModel> generateNews() {
        List<NewsModel> newsList = new ArrayList<>();
        List<String> authorsData = getTextListFromFile(AUTHOR_PATH);
        List<String> contentData = getTextListFromFile(CONTENT_PATH);
        List<String> titleData = getTextListFromFile(NEWS_PATH);

        for (int i = 1; i <= 20; i++) {
            Random random = new Random();
            AuthorModel author = createAuthor((long)i,authorsData.get(i % authorsData.size()));
            String content = contentData.get(i % contentData.size());
            String title = titleData.get(random.nextInt(20));
            NewsModel news = new NewsModel((long) i, title,
                                            content, LocalDateTime.now(),
                                            LocalDateTime.now(), author.getId());
            newsList.add(news);
        }
        return newsList;
    }

    public List<AuthorModel> generateAuthor(){
        List<AuthorModel> authorList = new ArrayList<>();
        List<String> authorsData = getTextListFromFile(AUTHOR_PATH);
        for (int i = 1; i <= 20; i++) {
            AuthorModel author = createAuthor((long)i, authorsData.get(i % authorsData.size()));
            authorList.add(author);
        }
        return authorList;
    }

    private static AuthorModel createAuthor(Long id, String authorData) {
        if (authorData != null) {
            AuthorModel authorModel = new AuthorModel();
            authorModel.setId(id);
            authorModel.setName(authorData);
            return authorModel;
        } else {
            throw new IllegalArgumentException("Invalid author data: " + authorData);
        }
    }

    public static List<String> getTextListFromFile(String filepath) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return bufferedReader.lines().toList();
    }
}
