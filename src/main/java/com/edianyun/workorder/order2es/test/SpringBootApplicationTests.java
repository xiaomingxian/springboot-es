package com.edianyun.workorder.order2es.test;

import com.edianyun.workorder.order2es.bean.BookBean;
import com.edianyun.workorder.order2es.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.IndexTemplateMetaData;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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

import java.io.IOException;
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

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private RestClient restClient;

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
    public  void  query() {

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        sourceBuilder.fetchSource(new String[]{"*"}, Strings.EMPTY_ARRAY);

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "rabbit");
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("YunZhiHui", "address", "company");
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("interest", "game steak");
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("birthday");
        rangeQueryBuilder.gte("2018-01-26");
        rangeQueryBuilder.lte("2019-01-26");

        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(matchQueryBuilder);
        boolBuilder.must(termQueryBuilder);
        boolBuilder.must(rangeQueryBuilder);
        boolBuilder.should(multiMatchQueryBuilder);

        sourceBuilder.query(boolBuilder);
        SearchRequest searchRequest = new SearchRequest("index1");
        searchRequest.types("doc");
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
