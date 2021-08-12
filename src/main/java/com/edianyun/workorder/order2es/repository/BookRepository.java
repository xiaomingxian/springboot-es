package com.edianyun.workorder.order2es.repository;

import com.edianyun.workorder.order2es.bean.BookBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public   interface BookRepository extends ElasticsearchRepository<BookBean, String> {

    Page<BookBean> findByPrice(String author, Pageable pageable);

    Page<BookBean> findByName(String title, Pageable pageable);


    List<BookBean> findByNameLike(String name);
}
