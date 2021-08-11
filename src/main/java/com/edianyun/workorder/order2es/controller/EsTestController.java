package com.edianyun.workorder.order2es.controller;

import com.edianyun.workorder.order2es.bean.BookBean;
import com.edianyun.workorder.order2es.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EsClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("es")
@Slf4j
public class EsTestController {

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("insert")
    public Iterable<BookBean> insert() {

        BookBean bookBean = new BookBean();
        bookBean.setId("1");
        bookBean.setType("t1");
        bookBean.setName("name1");
        bookBean.setPrice("11");

        BookBean save = bookRepository.save(bookBean);
        log.info("======>>{}", save);
        Iterable<BookBean> all = bookRepository.findAll();


        return all;
    }

}
