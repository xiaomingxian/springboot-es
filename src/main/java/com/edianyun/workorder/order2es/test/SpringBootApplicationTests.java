package com.edianyun.workorder.order2es.test;

import com.edianyun.workorder.order2es.bean.BookBean;
import com.edianyun.workorder.order2es.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.spanOrQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootApplicationTests {


    @Autowired
    private BookRepository bookRepository;



    @Test
    public void Test1(){


        for (int i = 1; i <100 ; i++) {
            BookBean bookBean = new BookBean();
            bookBean.setId(i+"");
            bookBean.setName("name"+UUID.randomUUID().toString().replaceAll("-",""));
            bookBean.setType("T"+i);
            bookBean.setPrice(12+i+"");
            BookBean save = bookRepository.save(bookBean);
            log.info("save bean:{}",save);

        }
    }

    @Test
    public  void  query(){


        Iterable<BookBean> all = bookRepository.findAll();
        System.out.println("all:::::"+all);
    }

}
