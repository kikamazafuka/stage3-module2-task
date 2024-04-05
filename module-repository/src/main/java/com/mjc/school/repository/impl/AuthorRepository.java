package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotation.OnDelete;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.AuthorModel;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    private final DataSource dataSource;
    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private final List<AuthorModel> allAuthors;

    public AuthorRepository() {
        this.dataSource = DataSource.getInstance();
        this.allAuthors = dataSource.generateAuthor();
    }

    @Override
    public List<AuthorModel> readAll() {
        return this.allAuthors;
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
         return allAuthors.stream()
                 .filter(authorModel -> authorModel.getId().equals(id))
                 .findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel author) {
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(TIME_FORMAT));
        Long id = (long) allAuthors.size() + 1;
        author.setId(id);
        author.setCreateDate(time);
        author.setName(author.getName());
        author.setLastUpdateDate(time);
        allAuthors.add(author);
        return author;
    }

    @Override
    public AuthorModel update(AuthorModel author) {
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(TIME_FORMAT));
        Optional<AuthorModel> existingNews = readById(author.getId());
        if (existingNews.isPresent()) {
            AuthorModel authorModel = existingNews.get();
            authorModel.setName(author.getName());
            authorModel.setLastUpdateDate(time);
            allAuthors.replaceAll(n -> n.getId().equals(author.getId()) ? authorModel : n);
            return authorModel;
        }
        return null;
    }

    @Override
    @OnDelete
    public boolean deleteById(Long id) {
        Optional<AuthorModel> authorToDelete = readById(id);
        if (authorToDelete.isPresent()){
            AuthorModel authorModel = authorToDelete.get();
            allAuthors.remove(authorModel);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return allAuthors.stream()
                .anyMatch(authorModel -> authorModel.getId().equals(id));
    }
}
