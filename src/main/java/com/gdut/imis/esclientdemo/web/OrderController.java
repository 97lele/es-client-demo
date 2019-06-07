package com.gdut.imis.esclientdemo.web;

import com.gdut.imis.esclientdemo.form.Product;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortMode;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lulu
 * @Date 2019/6/7 18:39
 */
@RestController
public class OrderController {
    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private Gson gson;

    @GetMapping("/order/getById/{id}")
    public Map<String, Object> getOrder(@PathVariable("id") String id) {
        GetRequest getRequest = new GetRequest("order", "_doc", id);
        Map map = new HashMap();
        GetResponse response = null;
        try {
            response = client.get(getRequest, RequestOptions.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.isExists()) {
            map.put("success", true);
            map.put("data", response.getSource());
        } else {
            map.put("success", false);
        }
        return map;
    }

    @DeleteMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable("id") String id) {
        DeleteRequest request = new DeleteRequest("order", id);
        try {
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            return response.status().name();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/order/update/{id}")
    public String update(@PathVariable("id") String id,
                         @RequestParam(value = "buyer", required = false) String buyer,
                         @RequestParam(value = "status", required = false) Integer status) throws IOException {
        UpdateRequest request = new UpdateRequest("order", id);
        Map<String, Object> temp = new HashMap<>();
        if (!ObjectUtils.isEmpty(status)) {
            temp.put("status", status);

        }
        if (!ObjectUtils.isEmpty(buyer)) {
            temp.put("buyer", buyer);

        }
        request.doc(temp);
        return client.update(request, RequestOptions.DEFAULT).status().name();

    }

    @PostMapping("/order/create")
    public Map<String, Object> createOrder(@RequestParam("buyer") String buyer,
                                           @RequestParam("date") String date,
                                           @RequestParam("totalPrice") Double total,
                                           @RequestParam("products") String products,
                                           @RequestParam("id") String id
    ) {
        Map map = new HashMap();
        XContentBuilder builder = null;
        IndexRequest request = new IndexRequest("order");
        List<Product> productList = gson.fromJson(products, new TypeToken<List<Product>>() {
        }.getType());
        List<Map<String, Object>> list = productList.stream().map(e -> {
            Map<String, Object> temp = new HashMap<>();
            Field[] fields = e.getClass().getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                try {
                    temp.put(f.getName(), f.get(e));
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
            return temp;
        }).collect(Collectors.toList());
        try {
            builder = XContentFactory.jsonBuilder();
            builder.startObject()
                    .field("buyer", buyer).field("date", date)
                    .field("totalPrice", total)

                    .field("products", list)
                    .endObject()
            ;


            request.id(id).opType("create").source(builder);

            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            map.put("status", response.status());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return map;
    }

    @PostMapping("/order/query")
    public List<Map<String, Object>> query(@RequestParam(value = "buyer", required = false) String buyer,
                                           @RequestParam(value = "gtePrice", required = false) Double gtePrice,
                                           @RequestParam(value = "value=ltePrice", required = false) Double ltePrice,
                                           @RequestParam(value = "status", required = false) Integer status,
                                           @RequestParam("index") Integer index,
                                           @RequestParam("size") Integer size
    ) throws IOException {
        SearchRequest request = new SearchRequest("order");
//构造bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!ObjectUtils.isEmpty(buyer)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("buyer", buyer));
        }
        if (!ObjectUtils.isEmpty(status)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("status", status));
        }
//对应filter
        if (!ObjectUtils.isEmpty(gtePrice) && !ObjectUtils.isEmpty(ltePrice)) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("totalPrice").from(ltePrice).to(gtePrice));
        }

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //排序
        searchSourceBuilder.sort(SortBuilders.fieldSort("totalPrice").order(SortOrder.DESC));
        //分页
        searchSourceBuilder.from(index).size(size).query(boolQueryBuilder);
        request.searchType(SearchType.DEFAULT).source(searchSourceBuilder);
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit s : client.search(request, RequestOptions.DEFAULT).getHits().getHits()) {
            list.add(s.getSourceAsMap());
        }
        return list;


    }

}
