package com.goovy.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.goovy.dto.GoodsParamsDTO;
import com.goovy.entity.*;
import com.goovy.es.EsGoodsIndex;
import com.goovy.response.Res;
import com.goovy.service.*;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InitEsProductServiceImpl implements InitEsProductService {

    @Resource
    private RestHighLevelClient restHighLevelClient;
    @Resource
    private IGoodsService goodsService;

    @Resource
    private IGoodsSkuService goodsSkuService;
    @Resource
    private IBrandService brandService;
    @Resource
    private ILiStoreGoodsLabelService storeGoodsLabelService;
    @Resource
    private ICategoryService categoryService;


    public Res initEsProduct() throws IOException {
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


        Page<GoodsSku> page2 = goodsSkuService.page(new Page<>(0, 10));
        List<GoodsSku> goodsSkuList = page2.getRecords();
        List<EsGoodsIndex> goodsIndex = this.createGoodsIndex(goodsSkuList);
        BulkRequest bulkRequest = new BulkRequest();
        for (EsGoodsIndex index : goodsIndex) {
            bulkRequest.add(new IndexRequest("goods", "_doc", index.getId()).source(JSON.toJSONString(index), XContentType.JSON));
        }
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return Res.success();

    }


    private List<EsGoodsIndex> createGoodsIndex(List<GoodsSku> goodsSkuList) {
        // 提取出每个sku对应goods的goodsId、brandId、category信息
        Set<String> goodsIdList = goodsSkuList.stream().map(GoodsSku::getGoodsId).collect(Collectors.toSet());
        Set<String> goodIdSet = new HashSet<>();
        Set<String> brandIdSet = new HashSet<>();
        Set<String> categoryIdSet = new HashSet<>();
        Set<String> storeCotegoryIdSet = new HashSet<>();
        goodsSkuList.forEach((a) -> {
            if (StrUtil.isNotBlank(a.getBrandId())) {
                brandIdSet.add(a.getBrandId());
            }
            if (StrUtil.isNotBlank(a.getStoreCategoryPath())) {
                storeCotegoryIdSet.addAll(Arrays.asList(a.getStoreCategoryPath().split(",")));
            }
            if (StrUtil.isNotBlank(a.getCategoryPath())) {
                categoryIdSet.addAll(Arrays.asList(a.getCategoryPath().split(",")));
            }
            goodIdSet.add(a.getGoodsId());
        });


        Map<String, String> goodsIdAndParamMap = goodsService.lambdaQuery()
                .eq(Goods::getId, goodsIdList)
                .list()
                .stream()
                .collect(Collectors.toMap((b) -> String.valueOf(b.getId()), Goods::getParams));

        Map<Long, Brand> brandIdAndDataMap = brandService.lambdaQuery().in(Brand::getId, brandIdSet).list().stream().collect(Collectors.toMap(Brand::getId, Function.identity()));
        Map<Long, LiStoreGoodsLabel> storeGoodsLabelIdAndData = storeGoodsLabelService.lambdaQuery().in(LiStoreGoodsLabel::getId, storeCotegoryIdSet).list().stream().collect(Collectors.toMap(LiStoreGoodsLabel::getId, Function.identity()));
        Map<Long, Category> categoryIdAndDataMap = categoryService.lambdaQuery().in(Category::getId, categoryIdSet).list().stream().collect(Collectors.toMap(Category::getId, Function.identity()));

        List<EsGoodsIndex> resList = CollUtil.newArrayList();
        for (GoodsSku goodsSku : goodsSkuList) {
            String goodsId = goodsSku.getGoodsId();
            String brandId = goodsSku.getBrandId();
            String storeCategoryPath = goodsSku.getStoreCategoryPath();
            String categoryPath = goodsSku.getCategoryPath();

            String params = goodsIdAndParamMap.get(goodsId);
            List<GoodsParamsDTO> goodsParamsDTOS = JSONUtil.toList(params, GoodsParamsDTO.class);
            EsGoodsIndex esGoodsIndex = new EsGoodsIndex(goodsSku, goodsParamsDTOS);


            if (StrUtil.isNotBlank(storeCategoryPath)) {
                StringBuilder labelNames = new StringBuilder();
                for (String s : storeCategoryPath.split(",")) {
                    if (StrUtil.isBlank(s)) {
                        continue;
                    }
                    LiStoreGoodsLabel liStoreGoodsLabel = storeGoodsLabelIdAndData.get(Long.valueOf(s));
                    if (Objects.isNull(liStoreGoodsLabel)) {
                        continue;
                    }
                    labelNames.append(liStoreGoodsLabel.getLabelName()).append(",");
                }
                String labelNamesStr = labelNames.toString();
                if (labelNamesStr.endsWith(",")) {
                    labelNamesStr = labelNamesStr.substring(0, labelNamesStr.length() - 1);
                }
                esGoodsIndex.setStoreCategoryNamePath(labelNamesStr);
            }

            if (StrUtil.isNotBlank(categoryPath)) {

                StringBuilder categoryNames = new StringBuilder();

                for (String s : categoryPath.split(",")) {
                    if (StrUtil.isBlank(s)) {
                        continue;
                    }
                    Category category = categoryIdAndDataMap.get(Long.valueOf(s));
                    if (Objects.isNull(category)) {
                        continue;
                    }
                    categoryNames.append(category.getName()).append(",");
                }

                String categoryNamesStr = categoryNames.toString();
                if (categoryNamesStr.endsWith(",")) {
                    categoryNamesStr = categoryNamesStr.substring(0, categoryNamesStr.length() - 1);
                }
                esGoodsIndex.setCategoryNamePath(categoryNamesStr);
            }


            Brand brand = brandIdAndDataMap.get(Long.valueOf(brandId));
            if (Objects.nonNull(brand)) {
                esGoodsIndex.setBrandName(brand.getName());
                esGoodsIndex.setBrandUrl(brand.getLogo());
            }
            esGoodsIndex.setSkuSource(100);
            resList.add(esGoodsIndex);
        }
        return resList;
    }
}
