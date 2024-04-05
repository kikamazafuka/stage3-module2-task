package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSource {
    private static final DataSource instance = new DataSource();

    private DataSource() {
    }

    public static DataSource getInstance() {
        return instance;
    }

    public List<NewsModel> generateNews() {
        List<NewsModel> newsList = new ArrayList<>();
        List<String> authorsData = readFromFile("authors");
        List<String> contentData = readFromFile("content");

        for (int i = 1; i <= 20; i++) {
            AuthorModel author = createAuthor(authorsData.get(i % authorsData.size()));
            String content = contentData.get(i % contentData.size());
            NewsModel news = new NewsModel((long) i, "Title " + i,
                                            content, LocalDateTime.now(),
                                            LocalDateTime.now(), author.getId());
            newsList.add(news);
        }
        return newsList;
    }

    public List<AuthorModel> generateAuthor(){
        List<AuthorModel> authorList = new ArrayList<>();
        List<String> authorsData = readFromFile("authors");
        for (int i = 1; i <= 20; i++) {
            AuthorModel author = createAuthor(authorsData.get(i % authorsData.size()));
            authorList.add(author);
        }
        return authorList;
    }

    private static AuthorModel createAuthor(String authorData) {
        if (authorData != null) {
            Random random = new Random();
            long id = random.nextInt(30);
            AuthorModel authorModel = new AuthorModel();
            authorModel.setId(id);
            authorModel.setName(authorData);
            return authorModel;
        } else {
            throw new IllegalArgumentException("Invalid author data: " + authorData);
        }
    }

    private static List<String> readFromFile(String fileName) {
        try {
            Path filePath = Paths.get(DataSource.class.getClassLoader().getResource(fileName).toURI());
            return Files.readAllLines(filePath);
        } catch (IOException | NullPointerException | java.net.URISyntaxException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }
}
