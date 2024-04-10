package com.mjc.school.repository.aspects;

import com.mjc.school.repository.datasource.DataSource;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorAspect {

    private final DataSource dataSource;

    public AuthorAspect() {
        this.dataSource = DataSource.getInstance();
    }

    @Pointcut("isRepositoryLayer() && @annotation(com.mjc.school.repository.annotation.OnDelete)")
    public void isOnDelete(){

    }

    @AfterReturning(value = "isOnDelete() && args(id)", returning="result", argNames = "result,id")
    public void onDelete(Boolean result, Long id){
        if(result){
            dataSource.generateNews().stream()
                    .filter(news -> id.equals(news.getAuthorId()))
                    .forEach(news -> news.setAuthorId(null));
        }
    }
    @Pointcut("@within(org.springframework.stereotype.Repository))")
    public void isRepositoryLayer(){

    }
}
