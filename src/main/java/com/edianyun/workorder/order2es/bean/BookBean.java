package com.edianyun.workorder.order2es.bean;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "cia-logcenter", type = "doc")
public class BookBean {
    String id;
    String type ;
    String price ;
    String name ;

}
