package com.edianyun.workorder.order2es.test;

import com.edianyun.workorder.order2es.bean.BookBean;
import com.edianyun.workorder.order2es.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootApplicationTests {


    @Autowired
    private BookRepository bookRepository;

    @Test
    public void Test1(){
        List<BookBean> byName = bookRepository.findByNameLike("a");
        List<BookBean> byNames = bookRepository.findByNameLikes();
        log.info("'''''''''''{}",byName);
        log.info("names::::{}",byNames);

        Iterable<BookBean> all = bookRepository.findAll();

        System.out.println();
    }

}
