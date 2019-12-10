package com.choi.jpa;

import com.choi.jpa.bean.Book;
import com.choi.jpa.dao.BookDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaApplicationTests {

    @Autowired
    private BookDao bookDao;

    @Test
    void contextLoads() {
        Book book = new Book();
        book.setName("111");
        book.setAuthor("!11");
        bookDao.save(book);
    }

    @Test
    public void update(){
        Book book = new Book();
        book.setAuthor("2131");
        book.setName("213131");
        book.setId(1);
        bookDao.saveAndFlush(book);
    }

    @Test
    public void delete(){
        bookDao.deleteById(1);
    }

    @Test
    public void find1(){
        Optional<Book> byId = bookDao.findById(1);
        System.out.println(byId.get());
        List<Book> books = bookDao.findAll();
        System.out.println(books);
    }

    @Test
    public void find2(){
        List<Book> all = bookDao.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    @Test
    public void find3(){
        Pageable pageable = PageRequest.of(0,2);
        Page<Book> page = bookDao.findAll(pageable);
        System.out.println(page.getNumberOfElements());
        System.out.println(page.getTotalElements());
        System.out.println(page.getNumberOfElements());
        System.out.println(page.getSize());
        System.out.println(page.getTotalPages());
        System.out.println(page.getContent());
        System.out.println(page.getNumber());
    }



}
