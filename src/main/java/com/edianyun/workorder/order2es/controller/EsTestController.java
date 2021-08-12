package com.edianyun.workorder.order2es.controller;

import com.edianyun.workorder.order2es.bean.BookBean;
import com.edianyun.workorder.order2es.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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

    @Autowired
    private RestHighLevelClient client;

    private final RequestOptions options = RequestOptions.DEFAULT;
    /**
     * 查询总数
     */
    @RequestMapping("count")
    public Long count (String indexName){
        // 指定创建时间
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.termQuery("task_type", "startEvent"));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);

        CountRequest countRequest = new CountRequest(indexName);
        countRequest.source(sourceBuilder);
        try {
            CountResponse countResponse = client.count(countRequest, options);
            return countResponse.getCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }


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
