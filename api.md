#### 查询某个字段
```
GET /work_flow1/_search
{
  "_source":["workOrderId"],
    "query": {
        "match_all": {}
    }
    
    
    ,
   "sort": [
    {
        "workOrderId": "desc"
    }
    ],
    "from":2000,
    "size":1000
}
```
#### 精确查询
```
GET /work_flow1/_search
{
 
    "query": {
      "match":{
        "workOrderId":"796859"
      }
    }
    
  
}
```
















