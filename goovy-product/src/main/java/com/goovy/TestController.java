package com.goovy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.goovy.dto.GoodsParamsDTO;
import com.goovy.entity.*;
import com.goovy.es.EsGoodsIndex;
import com.goovy.exception.GoovyException;
import com.goovy.response.Res;
import com.goovy.service.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @RequestMapping("/esTest")
    public Res test() throws IOException {
//        GetIndexRequest getIndexRequest = new GetIndexRequest("goods");
//
//        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
//        if (!exists) {
//            CreateIndexRequest createIndexRequest = new CreateIndexRequest("goods");
//            createIndexRequest.mapping("{\n" +
//                    "  \"properties\": {\n" +
//                    "     \"_class\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"attrList\": {\n" +
//                    "        \"type\": \"nested\",\n" +
//                    "        \"properties\": {\n" +
//                    "           \"name\": {\n" +
//                    "              \"type\": \"keyword\"\n" +
//                    "           },\n" +
//                    "           \"type\": {\n" +
//                    "              \"type\": \"long\"\n" +
//                    "           },\n" +
//                    "           \"value\": {\n" +
//                    "              \"type\": \"keyword\"\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"brandId\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fielddata\": true,\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"brandName\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fielddata\": true,\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"brandUrl\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fielddata\": true,\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"buyCount\": {\n" +
//                    "        \"type\": \"long\"\n" +
//                    "     },\n" +
//                    "     \"releaseTime\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fielddata\": true,\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"categoryPath\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fielddata\": true,\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"categoryNamePath\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fielddata\": true,\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"commentNum\": {\n" +
//                    "        \"type\": \"long\"\n" +
//                    "     },\n" +
//                    "     \"skuSource\": {\n" +
//                    "        \"type\": \"long\"\n" +
//                    "     },\n" +
//                    "     \"goodsId\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"goodsName\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fielddata\": true,\n" +
//                    "        \"analyzer\": \"ik_max_word\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"grade\": {\n" +
//                    "        \"type\": \"float\"\n" +
//                    "     },\n" +
//                    "     \"highPraiseNum\": {\n" +
//                    "        \"type\": \"long\"\n" +
//                    "     },\n" +
//                    "     \"id\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"intro\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"authFlag\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"marketEnable\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fielddata\": true,\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"mobileIntro\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"point\": {\n" +
//                    "        \"type\": \"long\"\n" +
//                    "     },\n" +
//                    "     \"price\": {\n" +
//                    "        \"type\": \"float\"\n" +
//                    "     },\n" +
//                    "     \"salesModel\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"recommend\": {\n" +
//                    "        \"type\": \"boolean\"\n" +
//                    "     },\n" +
//                    "     \"selfOperated\": {\n" +
//                    "        \"type\": \"boolean\"\n" +
//                    "     },\n" +
//                    "     \"sellerId\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"sellerName\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fielddata\": true,\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"shopCategoryPath\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fielddata\": true,\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"sn\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     },\n" +
//                    "     \"promotionMapJson\": {\n" +
//                    "        \"type\": \"text\"\n" +
//                    "     },\n" +
//                    "     \"thumbnail\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"fields\": {\n" +
//                    "           \"keyword\": {\n" +
//                    "              \"type\": \"keyword\",\n" +
//                    "              \"ignore_above\": 256\n" +
//                    "           }\n" +
//                    "        }\n" +
//                    "     }\n" +
//                    "  }\n" +
//                    "}", XContentType.JSON);
//            boolean acknowledged = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT).isAcknowledged();
//            if (!acknowledged) {
//                throw new GoovyException("goods index create fail!");
//            }
//        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.rangeQuery("price").gt(2000).lt(5000));

        SearchRequest searchRequest = new SearchRequest("goods");

        searchRequest.source(searchSourceBuilder);

        restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        return Res.success();

    }
}
