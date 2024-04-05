package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final DataSource dataSource;
    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private final List<NewsModel> allNews;

    public NewsRepository() {
        this.dataSource = DataSource.getInstance();
        this.allNews = dataSource.generateNews();
    }


    @Override
    public List<NewsModel> readAll() {
        return this.allNews;
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        for (NewsModel news : allNews) {
            if (news.getId() == id) {
                return Optional.of(news);
            }
        }
        return Optional.empty();
    }

    @Override
    public NewsModel create(NewsModel news) {
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(TIME_FORMAT));
        Long id = (long) allNews.size() + 1;
        news.setId(id);
        news.setCreateDate(time);
        news.setLastUpdateDate(time);
        allNews.add(news);
        return news;
    }

    @Override
    public NewsModel update(NewsModel news) {
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(TIME_FORMAT));
        Optional<NewsModel> existingNews = readById(news.getId());

        if (existingNews.isPresent()) {
            NewsModel newsModel = existingNews.get();
            newsModel.setTitle(news.getTitle());
            newsModel.setContent(news.getContent());
            newsModel.setLastUpdateDate(time);
            newsModel.setAuthorId(news.getAuthorId());
            allNews.replaceAll(n -> n.getId().equals(news.getId()) ? newsModel : n);
            return newsModel;
        }

        //TODO throw not found exception
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<NewsModel> newsToDelete = readById(id);
        if (newsToDelete.isPresent()){
            NewsModel newsModel = newsToDelete.get();
            allNews.remove(newsModel);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return allNews.stream().anyMatch(news -> news.getId().equals(id));
    }
}
