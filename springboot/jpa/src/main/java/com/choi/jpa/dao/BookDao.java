package com.choi.jpa.dao;

import com.choi.jpa.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : choibin
 * @date : 16:33  2019/12/9 0009
 */
public interface BookDao extends JpaRepository<Book,Integer> {

    Book findBookById(Integer id);

}
