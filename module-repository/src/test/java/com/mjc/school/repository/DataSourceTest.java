package com.mjc.school.repository;

import com.mjc.school.repository.datasource.DataSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataSourceTest {
    DataSource dataGenerator = DataSource.getInstance();
    @Test
    public void testReadFromFile(){

        assertEquals(20, dataGenerator.generateNews().size());
    }
}
