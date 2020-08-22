package com.clear.esdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mybatis.xml")
public class EsTest{

    private String index; //es索引

    private String type;  //es类型

    @Autowired
    private RestHighLevelClient restHighLevelClient;

//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private EsSearchOperateService esSearchOperateService;
//
//    @Before
//    public void prepare() {
//        index = "index1";
//        type = "user";
//    }
//
//    /**
//     * 添加数据
//     */
//    @Test
//    public void addTest() {
//        IndexRequest indexRequest = new IndexRequest(index, type);
//        //User user = userMapper.selectById(1l);
//       /* List<User> list = userMapper.selectList();
//        for (User uu : list) {
//        	String source = JSON.toJSONString(uu);
//            indexRequest.source(source, XContentType.JSON);
//            try {
//            	IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//                System.out.println("--STATUS--:"+response.status());
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//		}*/
//
//    }
//
//    /**
//     * id存在则更新，id不存在则创建
//     */
//    @Test
//    public void updateTest(){
//        UpdateRequest updateRequest = new UpdateRequest(index, type, "akt0cW0BTxiEHOu6eJAf");
//        User user=new User();
//        user.setAddress("近年来,随着互联网行业的蓬勃发展,信息量呈指数增长,一个好的搜索引擎就显得极为必要,为了解决当前在大数据时代面临的信息爆炸的问题。本系统用ElasticSearch设计并实现了个人搜索引擎,开发技术主要使用轻量级框架Django技术、MySQL,搜索框架采用ElasticSearch实现网页数据的获取,搜索建议的生成,数据的存储,以及数据搜索后的展示。");
//        user.setId(10l);
//
//        user.setPassward("134532");
//        user.setPhone("18765745890");
//        String source = JSON.toJSONString(user);
//
//        updateRequest.doc(source,XContentType.JSON).upsert(source, XContentType.JSON);
//
//        try {
//            UpdateResponse response = restHighLevelClient.update(updateRequest,RequestOptions.DEFAULT);
//            System.out.println("--STATUS--:"+response.status());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 删除
//     */
//    @Test
//    public void deleteTest(){
//        DeleteRequest deleteRequest = new DeleteRequest(index, type, "mlZOa20BLcVy-BTtAtnK");
//
//
//        try {
//            DeleteResponse response = restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);
//            System.out.println("--STATUS--:"+response.status());
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 批量添加数据
//     */
//    @Test
//    public void batchAddTest() {
//        BulkRequest bulkRequest = new BulkRequest();
//        List<IndexRequest> requests = generateRequests();
//        for (IndexRequest indexRequest : requests) {
//            bulkRequest.add(indexRequest);
//        }
//        try {
//            restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public List<IndexRequest> generateRequests(){
//        List<IndexRequest> requests = new ArrayList<>();
//        requests.add(generateNewsRequest(7l,"187364758675", "一天", "ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java语言开发的，并作为Apache许可条款下的开放源码发布，是一种流行的企业级搜索引擎。ElasticSearch用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。官方客户端在Java、.NET（C#）、PHP、Python、Apache Groovy、Ruby和许多其他语言中都是可用的。根据DB-Engines的排名显示，Elasticsearch是最受欢迎的企业搜索引擎，其次是Apache Solr，也是基于Lucene。","ggsf"));
//        requests.add(generateNewsRequest(8l,"18767856783", "小红", "ElasticSearch 是一个分布式、高扩展、高实时的搜索与数据分析引擎。它能很方便的使大量数据具有搜索、分析和探索的能力。充分利用ElasticSearch的水平伸缩性，能使数据在生产环境变得更有价值。ElasticSearch 的实现原理主要分为以下几个步骤，首先用户将数据提交到Elastic Search 数据库中，再通过分词控制器去将对应的语句分词，将其权重和分词结果一并存入数据，当用户搜索数据时候，再根据权重将结果排名，打分，再将返回结果呈现给用户。","ggsf"));
//        requests.add(generateNewsRequest(9l,"187364775475", "张三", "Elasticsearch是与名为Logstash的数据收集和日志解析引擎以及名为Kibana的分析和可视化平台一起开发的。这三个产品被设计成一个集成解决方案，称为“Elastic Stack”（以前称为“ELK stack”）。","ggsf"));
//        requests.add(generateNewsRequest(10l,"187360095375", "李四", "Elasticsearch可以用于搜索各种文档。它提供可扩展的搜索，具有接近实时的搜索，并支持多租户。”Elasticsearch是分布式的，这意味着索引可以被分成分片，每个分片可以有0个或多个副本。每个节点托管一个或多个分片，并充当协调器将操作委托给正确的分片。再平衡和路由是自动完成的。“相关数据通常存储在同一个索引中，该索引由一个或多个主分片和零个或多个复制分片组成。一旦创建了索引，就不能更改主分片的数量。","ggsf"));
//        requests.add(generateNewsRequest(7l,"187364758675", "小花", "Elasticsearch使用Lucene，并试图通过JSON和Java API提供其所有特性。它支持facetting和percolating，如果新文档与注册查询匹配，这对于通知非常有用。另一个特性称为“网关”，处理索引的长期持久性；例如，在服务器崩溃的情况下，可以从网关恢复索引。Elasticsearch支持实时GET请求，适合作为NoSQL数据存储，但缺少分布式事务。","ggsf"));
//        return requests;
//    }
//
//    public IndexRequest generateNewsRequest(Long id, String phone, String name,String address,String passward ){
//        IndexRequest indexRequest = new IndexRequest(index, type);
//        User user=new  User();
//        user.setAddress(address);
//        //user.setId(id);
//        user.setName(name);
//        user.setPassward(passward);
//        user.setPhone(phone);
//        String source = JSON.toJSONString(user);
//
//        indexRequest.source(source, XContentType.JSON);
//        return indexRequest;
//    }
//
//    /**
//     * 根据id查询ES数据
//     */
//    @Test
//    public void getByIdTest(){
//        try {
//            Map<String,Object> map = esSearchOperateService.searchDataById(index, type, "n1ZYa20BLcVy-BTtmdmU", "name,address,passward,phone");
//
//            System.out.println(map.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 分词查询ES数据
//     */
//    @Test
//    public void getList1Test(){
//        try {
//            List<Map<String, Object>>  list=esSearchOperateService.searchListData(index, type, "name,address,passward,phone", "address=万达,address=中原");
//
//            list.forEach(str-> System.out.println(str));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 分词查询ES数据
//     */
//    @Test
//    public void getList2Test(){
//        try {
//            List<Map<String, Object>>  list = esSearchOperateService.searchListData(index, type, 0l, 0l, 500, "name,address,passward,phone", "phone.keyword", true, "name", "address=中原");
//            list.forEach(str-> System.out.println(str));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 分词查询ES数据-分页(from-size-适用冷词分页)-高亮关键词
//     */
//    @Test
//    public void getList3Test(){
//        try {
//            int currentPage = 1;
//            int pageSize = 10;
//            EsDataPage dataPage = esSearchOperateService.searchDataPage(index, type, currentPage, pageSize, 0l, 0l, "name,address,passward,phone", "phone.keyword", true, "name", "address=中原");
//            dataPage.getSourceList().forEach(str-> System.out.println(str));
//            System.out.println("总数据量： "+dataPage.getTotalHits());
//            System.out.println("当前命中数： "+dataPage.getSourceList().size());
//            System.out.println("当前页： "+dataPage.getCurrentPage());
//            System.out.println("每页数据行数： "+dataPage.getPageSize());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 分词查询ES数据-分页(scroll-适用热词分页)-高亮关键词
//     */
//    @Test
//    public void getList4Test(){
//        try {
//            int currentPage = 1;
//            int pageSize = 4;
//            EsDataPage dataPage = esSearchOperateService.searchScrollDataPage(index, type, currentPage, pageSize, 0l, 0l, "name,address,passward,phone", "phone.keyword", true, "address", "address=elasticsearch");
//            dataPage.getSourceList().forEach(str-> System.out.println(str));
//            System.out.println("总数据量： "+dataPage.getTotalHits());
//            System.out.println("当前命中数： "+dataPage.getSourceList().size());
//            System.out.println("当前页： "+dataPage.getCurrentPage());
//            System.out.println("每页数据行数： "+dataPage.getPageSize());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
