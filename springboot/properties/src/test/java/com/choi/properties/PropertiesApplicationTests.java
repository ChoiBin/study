package com.choi.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertiesApplicationTests {

    @Autowired
    private Book book;
    @Autowired
    private Dog dog;

    @Test
    void contextLoads() {
        System.out.println(book.toString());
        System.out.println(dog.toString());
    }

}
