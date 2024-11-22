package tech.elastic.runner;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.*;
import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexState;
import co.elastic.clients.json.JsonData;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.elastic.dto.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CMDLineRunner implements CommandLineRunner {
    //https://levelup.gitconnected.com/elasticsearch-with-java-41daeda3e6b1
//https://realkoy.tistory.com/entry/elasticsearch-8x-Java-api-client%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-IndexTemplate-%EC%83%9D%EC%84%B1
    @Autowired
    ElasticsearchClient client;
//https://louchen.top/posts/middleware_tool/ElasticSearch%E5%8F%8AELK/Elasticsearch%20Java%20API%20Client%E8%AF%A6%E8%A7%A3.html#%E6%9F%A5%E8%AF%A2%E6%96%87%E6%A1%A3
    @Override
    public void run(String... args) throws Exception {
//https://velog.io/@pooh6195/Elasticsearch%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9E%90%EB%8F%99-%EC%99%84%EC%84%B1-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8-%EB%A7%8C%EB%93%A4%EA%B8%B0-Mapping


//        createIndex();
//        addOrModifyDocument();
//        queryDocuments();


        AtomicInteger index = new AtomicInteger(1);
        String indexName = "example_document";
//        createIndex(indexName);

//        List<ExampleDocument> list = sampleData();
//        sampleData().stream()
//                .forEach(value -> {
//                    int i = index.getAndIncrement();
//                    try {
//                        this.addOrModifyDocument(value,i,indexName);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });

        queryDocuments(indexName);

//        Asynchronously deletes the document
//        client.delete(i -> i.index("books").id("1")).whenComplete((success,failure)->{
//            System.out.println(success.index());
//            System.out.println(success.version());
//        });
//        Map<String, IndexState> result  = client.indices().get(req -> req.index(indexName)).result();
//        result.forEach((k, v) -> log.info("key = {},value = {}",k ,v));

    }

    public List<ExampleDocument> sampleData(){

        ExampleDocument doc1 = new ExampleDocument();
        doc1.setName("John Doe");
        doc1.setEmail("john.doe@example.com");
        doc1.setActive(true);
        doc1.setRating(7.5f);
        doc1.setDob(LocalDate.parse("1985-03-25"));
        doc1.setGender("male");
        doc1.setNumberOfChild(2);
        doc1.setAddress(new Address("123 Main St","12345"));
        doc1.setLocation(new Location(40.7128,-74.0060));
        doc1.setComments(List.of(new Comment("John Doe","This is great!",LocalDateTime.parse("2024-11-21T14:30:22"))
                ,new Comment("bob","I disagree with this.",LocalDateTime.parse("2024-11-22T15:30:11"))));

        ExampleDocument doc2 = new ExampleDocument();
        doc2.setName("Jane Smith");
        doc2.setEmail("jane.smith@example.com");
        doc2.setActive(false);
        doc2.setRating(3.9f);
        doc2.setDob(LocalDate.parse("1990-07-14"));
        doc2.setGender("female");
        doc2.setNumberOfChild(1);
        doc2.setAddress(new Address("456 Oak St","900013"));
        doc2.setLocation(new Location(34.0522,-118.2437));
        doc2.setComments(List.of(new Comment("charlie","Could be better.",LocalDateTime.parse("2024-11-22T16:30:00"))
                ));

        ExampleDocument doc3 = new ExampleDocument();
        doc3.setName("Robert Johnson");
        doc3.setEmail("robert.johnson@example.com");
        doc3.setActive(true);
        doc3.setRating(5.0f);
        doc3.setDob(LocalDate.parse("1975-12-05"));
        doc3.setGender("male");
        doc3.setNumberOfChild(3);
        doc3.setAddress(new Address("789 Pine St","606011"));
        doc3.setLocation(new Location(41.8781,-87.6298));
        doc3.setComments(List.of(new Comment("daniel","Excellent!",LocalDateTime.parse("2024-11-23T13:12:12"))
                ));

        ExampleDocument doc4 = new ExampleDocument();
        doc4.setName("Mary Lee");
        doc4.setEmail("mary.lee@example.com");
        doc4.setActive(true);
        doc4.setRating(4.2f);
        doc4.setDob(LocalDate.parse("1988-09-12"));
        doc4.setGender("female");
        doc4.setNumberOfChild(0);
        doc4.setAddress(new Address("321 Elm St","770016"));
        doc4.setLocation(new Location(29.7604,-95.3698));
        doc4.setComments(List.of(new Comment("emma","Very useful.",LocalDateTime.parse("2024-11-21T17:45:55"))
                ));

        ExampleDocument doc5 = new ExampleDocument();
        doc5.setName("David Brown");
        doc5.setEmail("david.brown@example.com");
        doc5.setActive(false);
        doc5.setRating(2.8f);
        doc5.setDob(LocalDate.parse("1995-11-01"));
        doc5.setGender("male");
        doc5.setNumberOfChild(1);
        doc5.setAddress(new Address("654 Cedar St","850015"));
        doc5.setLocation(new Location(33.4484,-112.0740));
        doc5.setComments(List.of(new Comment("george","Not up to expectations.",LocalDateTime.parse("2024-11-24T09:00:44"))
                ));

        ExampleDocument doc6 = new ExampleDocument();
        doc6.setName("Emma Wilson");
        doc6.setEmail("emma.wilson@example.com");
        doc6.setActive(true);
        doc6.setRating(5.8f);
        doc6.setDob(LocalDate.parse("1982-06-19"));
        doc6.setGender("female");
        doc6.setNumberOfChild(2);
        doc6.setAddress(new Address("123 Maple Ave","941052"));
        doc6.setLocation(new Location(37.7749,-122.4194));
        doc6.setComments(List.of(new Comment("jake","I absolutely love it!",LocalDateTime.parse("2024-11-21T18:00:24"))));

        ExampleDocument doc7 = new ExampleDocument();
        doc7.setName("William Harris");
        doc7.setEmail("william.harris@example.com");
        doc7.setActive(false);
        doc7.setRating(3.2f);
        doc7.setDob(LocalDate.parse("2000-01-22"));
        doc7.setGender("male");
        doc7.setNumberOfChild(3);
        doc7.setAddress(new Address("987 Birch Rd","733017"));
        doc7.setLocation(new Location(30.2672,-97.7431));
        doc7.setComments(List.of(new Comment("olivia","Needs improvement.",LocalDateTime.parse("2024-11-20T10:30:36"))));

        ExampleDocument doc8 = new ExampleDocument();
        doc8.setName("Sophia Miller");
        doc8.setEmail("sophia.miller@example.com");
        doc8.setActive(true);
        doc8.setRating(4.9f);
        doc8.setDob(LocalDate.parse("1993-04-13"));
        doc8.setGender("female");
        doc8.setNumberOfChild(1);
        doc8.setAddress(new Address("654 Pine Ave","981018"));
        doc8.setLocation(new Location(47.6062,-122.3321));
        doc8.setComments(List.of(new Comment("diana","Amazing service.",LocalDateTime.parse("2024-11-19T12:15:27"))));

        ExampleDocument doc9 = new ExampleDocument();
        doc9.setName("James Taylor");
        doc9.setEmail("james.taylor@example.com");
        doc9.setActive(true);
        doc9.setRating(4.0f);
        doc9.setDob(LocalDate.parse("1980-05-30"));
        doc9.setGender("male");
        doc9.setNumberOfChild(3);
        doc9.setAddress(new Address("123 Oak Dr","12345"));
        doc9.setLocation(new Location(42.3601,-71.0589));
        doc9.setComments(List.of(new Comment("lucas","Good product.",LocalDateTime.parse("2024-11-22T11:45:33"))));

       return Arrays.asList(doc1,doc2,doc3,doc4,doc5,doc6,doc7,doc8,doc9);
    }
    private Query initMatchQuery(String fieldName, String fieldValue) {
        return StringUtils.isNotBlank(fieldValue) ? MatchQuery.of(m -> m
                .field(fieldName + ".keyword")
                .query(fieldValue)
        )._toQuery() : null;
    }
    //https://juejin.cn/post/7195393521577099321?searchId=20241122160720297564465FAE0094442C
    private Query initRangeQuery(String fieldName, Integer begin, Integer end) {
        return  RangeQuery.of(m -> m
                .field(fieldName + ".keyword")
                .gte((JsonData.of(begin)))
                .lte((JsonData.of(end)))
        )._toQuery();
    }


//    Map<String, Property> documentMap = new HashMap<>();
//        documentMap.put("userName", Property.of(property ->
//            property.text(TextProperty.of(textProperty ->
//            textProperty.index(true).analyzer("ik_max_word"))
//            )
//            )
//            );
//        documentMap.put("age", Property.of(property ->
//            property.integer(IntegerNumberProperty.of(integerNumberProperty
//                        -> integerNumberProperty.index(true))
//                                )
//                                )
//                                );

//    public void indexDetail () throws IOException {
//        GetIndexResponse getIndexResponse = elasticsearchClient.indices()
//                .get(getIndexRequest ->
//                        getIndexRequest.index("elasticsearch-client")
//                );
//
//        Map<String, Property> properties = getIndexResponse.get("elasticsearch-client").mappings().properties();
//
//        for (String key : properties.keySet()) {
//            log.info("== {} : == key: {}, Property: {}", "elasticsearch-client", key, properties.get(key)._kind());
//        }
//
//    }

//public List<GoodsItemRep> queryFilter(String startTime, String endTime,String skuName) {
//  List<GoodsItemRep> goodsItemReps = new ArrayList<>();
//
//  Query rangeQuery = RangeQuery.of(q -> q
//                     .field("updateTime")
//     .gt(JsonData.of(startTime))
//     .lt(JsonData.of(endTime))
//     .timeZone("Asia/Shanghai"))._toQuery();
//  Query termQuery = TermQuery.of(t -> t.
//                  field("skuName")
//                     .value(skuName))._toQuery();
//  Query query = BoolQuery.of(b -> b
//                     .must(rangeQuery)
//     .filter(termQuery)
//   )._toQuery();
//
//  List<GoodsItem> result = client.search(index, GoodsItem.class, query);
//
//  result.stream().forEach(goodsItem -> {
//  GoodsItemRep goodsItemRep = new GoodsItemRep();
//  BeanUtils.copyProperties(goodsItem, goodsItemRep);
//  goodsItemReps.add(goodsItemRep);
//   });
//
//  return goodsItemReps;
//   }

    public void createIndex() throws IOException {
//        title (text field): The title field is analyzed with the standard analyzer. This is useful for full-text search where the search terms might match individual words or phrases within the title.
//        title.verbatim (keyword field): The title.verbatim field is a keyword type, meaning it stores the entire string without analysis. This is ideal for exact matching, sorting, and aggregations.
        CreateIndexResponse createIndexResponse = client.indices().create(i -> i
                .index("blog")
                .settings(s -> s
                        .numberOfShards("2")
                        .numberOfReplicas("3")
                        .refreshInterval(re->re.time("5s"))
                        .maxResultWindow(10000)
                )
                .mappings(m -> m //mappings fluently
                        .properties("title", t -> t
                                .text(tt -> tt.analyzer("standard").fielddata(true)
                                        .fields("ravan", tv -> tv.keyword(kv -> kv.index(true)))
                                )
                        )
                        .properties("publish", p -> p.keyword(pp -> pp.index(true)))
                        .properties("date",d-> d.date(dd->dd.format("yyyy-MM-dd")))
                        .properties("location", g -> g.geoPoint(gg -> gg))
                        .properties("authors", a -> a.nested(n -> n.properties("name", p -> p.text(t -> t.analyzer("standard").fielddata(true)))))
                )
                .aliases("my_blog", aa -> aa.isWriteIndex(false))
        );
        System.out.println("createIndexResponse.acknowledged() = " + createIndexResponse.acknowledged());
        System.out.println("createIndexResponse.shardsAcknowledged() = " + createIndexResponse.shardsAcknowledged());
        System.out.println("createIndexResponse.index() = " + createIndexResponse.index());
    }

    public void createIndex(String index) throws IOException {
//        title (text field): The title field is analyzed with the standard analyzer. This is useful for full-text search where the search terms might match individual words or phrases within the title.
//        title.verbatim (keyword field): The title.verbatim field is a keyword type, meaning it stores the entire string without analysis. This is ideal for exact matching, sorting, and aggregations.
        CreateIndexResponse createIndexResponse = client.indices().create(i -> i
                .index(index)
                .settings(s -> s
                        .numberOfShards("2")
                        .numberOfReplicas("3")
                )
                .mappings(m -> m //mappings fluently
                        .properties("name", t -> t
                                .text(tt -> tt.analyzer("standard").fielddata(true))
                        )
                        .properties("email", p -> p.keyword(pp -> pp.index(true)))
                        .properties("isActive", ia -> ia.boolean_(pp -> pp))
                        .properties("rating", r -> r.float_(f -> f))
                        .properties("dob", d -> d.date(dd -> dd.format("yyyy-MM-dd")))
                        .properties("gender", g -> g.keyword(kv -> kv.index(true)))
                        .properties("numberOfChild", n -> n.integer(it -> it))
                        .properties("location", l -> l.geoPoint(g -> g))
                        .properties("comments", c -> c
                                .nested(n -> n
                                        .properties("user", u -> u
                                                .keyword(kv -> kv.index(true))
                                        )
                                        .properties("text", t -> t
                                                .text(tt -> tt.analyzer("standard"))
                                        )
                                        .properties("timestamp", ts -> ts
                                                .date(d -> d.format("yyyy-MM-dd'T'HH:mm:ss"))
                                        )
                                )
                        )
                        .properties("address", a -> a
                                .object(o -> o
                                        .properties("street", s -> s
                                                .text(t -> t.analyzer("standard").fielddata(true))
                                        )
                                        .properties("zipcode", z -> z
                                                .keyword(kv -> kv.index(true))
                                        )
                                )
                        )
                )
                .aliases("my_blog", aa -> aa.isWriteIndex(false))
        );
        System.out.println("createIndexResponse.acknowledged() = " + createIndexResponse.acknowledged());
        System.out.println("createIndexResponse.shardsAcknowledged() = " + createIndexResponse.shardsAcknowledged());
        System.out.println("createIndexResponse.index() = " + createIndexResponse.index());
    }
    public void deleteIndex() throws IOException {
        DeleteIndexResponse deleteIndexResponse = client.indices().delete(i -> i.index("blog"));
        System.out.println("createIndexResponse.acknowledged() = " + deleteIndexResponse.acknowledged());
    }

    public void addOrModifyDocument() throws IOException {
        Book blog = new Book("Discovering Sydney Cape Town", "Jane Smith", "2026-01-11", new Authors("Discovering Sydney"), new Location(-33.8688,151.2093));
        IndexResponse response = client.index(i -> i.index("blog").document(blog).id("5"));
        System.out.println("response.result() = " + response.result());
        System.out.println("response.id() = " + response.id());
        System.out.println("response.seqNo() = " + response.seqNo());
        System.out.println("response.index() = " + response.index());
        System.out.println("response.shards() = " + response.shards());
    }

    public void addOrModifyDocument(ExampleDocument doc, int id, String index) throws IOException {
        IndexResponse response = client.index(i -> i.index(index).document(doc).id(String.valueOf(id)));
        System.out.println("response.result() = " + response.result());
        System.out.println("response.id() = " + response.id());
        System.out.println("response.seqNo() = " + response.seqNo());
        System.out.println("response.index() = " + response.index());
        System.out.println("response.shards() = " + response.shards() +" "+response.version());
    }


    public void queryDocuments() throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s.index("blog")
                        .query(q -> q.match(m -> m.field("title").query("Cape Town")))
//                .query(q -> q.term(t -> t.field("author").value("罗")))
        );
        System.out.println("search query = " + searchRequest.toString());
        SearchResponse<Book> response = client.search(searchRequest, Book.class);
        System.out.println("response.toString() = " + response.toString());
        System.out.println(response.maxScore());
        System.out.println(response.shards());
        System.out.println(response.timedOut());

        HitsMetadata<Book> hitsMetadata = response.hits();
        System.out.println(hitsMetadata.total());
        List<Hit<Book>> hits = hitsMetadata.hits();
        for (Hit<Book> hit : hits) {
            System.out.println(hit.source());
            System.out.println(hit.index());
            System.out.println(hit.id());
            System.out.println(hit.score());
        }
    }

    public SearchResponse<ExampleDocument> searchByGeoLocation(String index) throws Exception {
        String distance = "10";
        double lat = 10.014;
        double lon = -25.014;
        GeoLocation location = GeoLocation.of(g -> g
                .latlon(lo-> lo.lat(lat).lon(lon))
        );
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(index)
                .query(q -> q
                        .geoDistance(g -> g
                                .field("location")
                                .distance(distance + "km") // Distance in km (can also be 'm', 'mi', etc.)
                                .location(lo-> lo.latlon(tt-> tt.lat(lat).lon(lon)))
                                .distanceType(GeoDistanceType.Arc)
                        )
                )
        );

        return client.search(searchRequest, ExampleDocument.class);
    }

    public void searchGenderCount(String index) throws Exception {
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(index)
                .size(100)
                .aggregations("gender_count", aggs -> aggs
                        .terms(t -> t
                                .field("gender")
                        )
                )
        );
        SearchResponse<Void> response =  client.search(searchRequest, Void.class);
//        LongTermsAggregate longTermsAggregate = response.aggregations()
//                .get("gender_count")
//                .lterms();
//        StringTermsAggregate stringTermsAggregate = response.aggregations()
//                .get("gender_count")
//                .sterms();

        System.out.println(response.took());
        System.out.println(response.hits().total().value());

        response.hits().hits().forEach(e -> {
            System.out.println(e.source().toString());
        });

        Aggregate aggregate = response.aggregations().get("gender_count");
        LongTermsAggregate lterms = aggregate.lterms();
        Buckets<LongTermsBucket> buckets = lterms.buckets();
//        List<LongTermsBucket> array = response.aggregations()
//                .get("group_by_age")
//                .lterms()
//                .buckets()
//                .array();
        for (LongTermsBucket b : buckets.array()) {
            System.out.println(b.key() + " : " + b.docCount());
        }

    }

    void getMaxAgeUserTest(String index) throws IOException {
        SearchResponse<Void> response = client.search(b -> b
                        .index(index)
                        .size(0)
                        .aggregations("maxAge", a -> a
                                .max(MaxAggregation.of(s -> s
                                        .field("age"))
                                )
                        ),
                Void.class
        );
        MaxAggregate maxAge = response.aggregations()
                .get("maxAge")
                .max();
//        log.info("maxAge.value:{}",maxAge.value());
    }


//https://realkoy.tistory.com/entry/elasticsearch-8x-Java-api-client%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-IndexTemplate-%EC%83%9D%EC%84%B1
//    Query address=Nanjing and group by age. This query only queries the results, so it is Voild
//    private static void aggregation(ElasticsearchClient esClient) throws IOException {
//        String searchText = "ayushman";
//        Query query = TermQuery.of(m -> m
//                .field("address.keyword")
//                .value(searchText)
//        )._toQuery();
//        SearchResponse<Void> response = esClient.search(b -> b
//                        .index("es_2024")
//                        .size(0)
//                        .query(query)
//                        .aggregations("group_by_age", a -> a
//                                .terms(h -> h
//                                        .field("age")
//                                )
//                        ),
//                Void.class
//        );
//        List<LongTermsBucket> array = response.aggregations()
//                .get("group_by_age")
//                .lterms()
//                .buckets()
//                .array();
//        for (LongTermsBucket bucket: array) {
//            System.out.println("There are " + bucket.docCount() +
//                    " value " + bucket.key());
//        }
//    }

//    SearchRequest searchRequest = SearchRequest.of(s -> s
//            .index(index)
//            .aggregations("rating_range", aggs -> aggs
//                    .range(r -> r
//                            .field("rating")  // The field to aggregate on
//                            .ranges(
//                                    rgs -> rgs
//                                            .from(JsonData.of(0))
//                                            .to(JsonData.of(5)),
//                                    rgs -> rgs
//                                            .from(JsonData.of(5))
//                                            .to(JsonData.of(10))
//                            )
//                    )
//            )
//    );


//    public SearchResponse<Object> searchCommentsByUser(String index, String username, String commentText) throws Exception {
//        SearchRequest searchRequest = SearchRequest.of(s -> s
//                .index(index)
//                .query(q -> q
//                        .nested(n -> n
//                                .path("comments") // Path to the nested field
//                                .query(query -> query
//                                        .bool(b -> b
//                                                .must(m -> m
//                                                        .term(t -> t
//                                                                .field("comments.user")
//                                                                .value(JsonData.of(username)) // User's name
//                                                        )
//                                                )
//                                                .must(m -> m
//                                                        .match(t -> t
//                                                                .field("comments.text")
//                                                                .query(commentText) // Comment text to search for
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//
//        return client.search(searchRequest, Object.class);
//    }

    public void queryDocuments2(String index) throws IOException {
        float minRating = 2.1f;
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(index)
                .query(q -> q
                        .bool(b -> b
                                .must(m -> m
                                        .term(t -> t
                                                .field("isActive")
                                                .value(true) // Filter by active users
                                        )
                                )
                                .filter(f -> f
                                        .range(r -> r
                                                .field("rating")
                                                .gte(JsonData.of(minRating)) // Filter by rating greater than or equal to minRating
                                        )
                                )
                        )
                )
        );
        System.out.println("search query = " + searchRequest.toString());
        SearchResponse<Book> response = client.search(searchRequest, Book.class);
        System.out.println("response.toString() = " + response.toString());
        System.out.println(response.maxScore());
        System.out.println(response.shards());
        System.out.println(response.timedOut());

        HitsMetadata<Book> hitsMetadata = response.hits();
        System.out.println(hitsMetadata.total());
        List<Hit<Book>> hits = hitsMetadata.hits();
        for (Hit<Book> hit : hits) {
            System.out.println(hit.source());
            System.out.println(hit.index());
            System.out.println(hit.id());
            System.out.println(hit.score());
        }
    }

//    void filterFieldSearch() throws IOException  {
//        SearchResponse<User> response = elasticsearchClient.search(s -> s
//                        .index("users")
//                        .query(q -> q
//                                .matchAll( m -> m)
//                        )
//                        .sort(f -> f
//                                .field(o -> o
//                                        .field("age")
//                                        .order(SortOrder.Desc)
//                                )
//                        )
//                        .source(source -> source
//                                .filter(f -> f
//                                        .includes("name","id")
//                                        .excludes(""))),  //.excludes(null)
//                User.class
//        );
//        List<Hit<User>> hits = response.hits().hits();
//        List<User> userList = new ArrayList<>(hits.size());
//        for (Hit<User> hit: hits) {
//            User user = hit.source();
//            userList.add(user);
//        }
//        log.info("过滤字段后：{}",JSONUtil.toJsonStr(userList));
//    }


//    void fuzzyQuerySearch() throws IOException  {
//        SearchResponse<User> response = elasticsearchClient.search(s -> s
//                        .index("users")
//                        .query(q -> q
//                                .fuzzy(f -> f
//                                        .field("name")
//                                        .value("liuyi")
//                                        .fuzziness("2")
//                                )
//                        )
//                        .source(source -> source
//                                .filter(f -> f
//                                        .includes("name","id")
//                                        .excludes(""))),
//                User.class
//        );
//        List<Hit<User>> hits = response.hits().hits();
//        List<User> userList = new ArrayList<>(hits.size());
//        for (Hit<User> hit: hits) {
//            User user = hit.source();
//            userList.add(user);
//        }
//        log.info("过滤字段后：{}",JSONUtil.toJsonStr(userList));
//    }

//    public void aggregation() throws IOException {
//        MultiTermsAggregation aggregation = MultiTermsAggregation.of(s -> s.terms(
//                MultiTermLookup.of(t->t.field("product")),
//                MultiTermLookup.of(t->t.field("user"))
//        ));
//
//        Aggregation priceAggregation = Aggregation.of(s -> s.sum(AggregationBuilders.sum().field("price").build()));
//        Aggregation idAggregation = Aggregation.of(s -> s.valueCount(ValueCountAggregation.of(v -> v.field("id"))));
//
//        Aggregation aggs = Aggregation.of(s -> s
//                .multiTerms(aggregation)
//                .aggregations("price", priceAggregation)
//                .aggregations("id", idAggregation)
//        );
//
//        SearchRequest searchRequest = new SearchRequest.Builder()
//                .index("order-index")
//                .aggregations("aggs", aggs)
//                .build();
//        SearchResponse<Void> searchResponse = elasticsearchClient.search(searchRequest, Void.class);
//        Aggregate aggregate = searchResponse.aggregations().get("aggs");
//        Buckets<MultiTermsBucket> buckets = aggregate.multiTerms().buckets();
//        buckets.array().forEach(bu -> {
//            String product = bu.key().get(0).stringValue();
//            String user = bu.key().get(1).stringValue();
//
//            double totalPrice = bu.aggregations().get("price").sum().value();;
//            double totalNum = bu.aggregations().get("id").valueCount().value();
//        });
//    }

// Creating aggregations
//         SearchResponse<Void> search3 = client.search( b-> b
//                 .index("products")
//                 .size(0)
//                 .aggregations("price-histo", a -> a
//                          .histogram(h -> h
//                                 .field("price")
//                                 .interval(20.0)
//                         )
//                         ),
//                          Void.class
//         );
//         long firstBucketCount = search3.aggregations()
//                  .get("price-histo")
//                 .histogram()
//                 .buckets().array()
//                  .get(0)
//                 .docCount();
//          System.out.println("doc count: " + firstBucketCount);

    public void queryDocuments1(String index) throws IOException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.of(1980, 1, 1);  // Example start date
        LocalDate endDate = LocalDate.of(1988, 12, 31);  // Example end date
        String start = startDate.format(dateFormatter);
        String end = endDate.format(dateFormatter);

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(index)
                .query(q -> q.range(r -> r
                                .field("dob")
                                .gte(JsonData.of(start))
                                .lte(JsonData.of(end))
                        )
                )
                .from(0)
                .size(100)
                .sort(sort -> sort
                        .field(f -> f
                                .field("rating")
                                .order(SortOrder.Asc)
                        )
                )

        );
        System.out.println("search query = " + searchRequest.toString());
        SearchResponse<ExampleDocument> response = client.search(searchRequest, ExampleDocument.class);
        System.out.println("response.toString() = " + response.toString());
        System.out.println("maxScore: "+response.maxScore());
        System.out.println("shards: "+response.shards());
        System.out.println("timedOut: "+response.timedOut());

        TotalHits total = response.hits().total();
        assert total != null;
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        HitsMetadata<ExampleDocument> hitsMetadata = response.hits();
        System.out.println("total records: "+hitsMetadata.total());
        List<Hit<ExampleDocument>> hits = hitsMetadata.hits();
        for (Hit<ExampleDocument> hit : hits) {
            System.out.println(hit.source());
//            System.out.println("index: "+hit.index());
            System.out.println("id: "+hit.id());
            System.out.println("score: "+hit.score());
        }
    }

    public void queryDocuments(String index) throws IOException {
        Map<String, JsonData> params = new HashMap<>();
        params.put("factor", JsonData.of(1.5));

        // Create the script
        Script script = Script.of(builder -> builder
                .inline(inlineBuilder -> inlineBuilder
                        .lang("painless")
                        .source("doc['rating'].value * params.factor")
                        .params(params)
                )
        );
        //https://www.chenzhen.space/blog/48
        //https://velog.io/@pooh6195/Elasticsearch%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9E%90%EB%8F%99-%EC%99%84%EC%84%B1-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8-%EB%A7%8C%EB%93%A4%EA%B8%B0-Mapping
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(index) // Define the index
                .query(q -> q
                        .scriptScore(scriptScore -> scriptScore
                                .query(ry->ry.matchAll( mt-> mt))
                                .script(script)
                        )
                )
        );

        System.out.println("search query = " + searchRequest.toString());
        SearchResponse<ExampleDocument> response = client.search(searchRequest, ExampleDocument.class);
        System.out.println("response.toString() = " + response.toString());
        System.out.println("maxScore: "+response.maxScore());
        System.out.println("shards: "+response.shards());
        System.out.println("timedOut: "+response.timedOut());

        HitsMetadata<ExampleDocument> hitsMetadata = response.hits();
        System.out.println("total records: "+hitsMetadata.total());
        List<Hit<ExampleDocument>> hits = hitsMetadata.hits();
        for (Hit<ExampleDocument> hit : hits) {
            System.out.println(hit.source());
//            System.out.println("index: "+hit.index());
            System.out.println("id: "+hit.id());
            System.out.println("score: "+hit.score());
        }
    }
}

//Let’s say you need to transform the created_at timestamp field into a user-friendly date format when retrieving support tickets.
//SearchRequest searchRequest = SearchRequest.of(s -> s
//        .index("support_tickets")
//        .query(q -> q
//                .match(m -> m
//                        .field("status")
//                        .query("open")
//                )
//        )
//        .scriptFields(sf -> sf
//                .add("created_at_formatted", script -> script
//                        .source("""
//                SimpleDateFormat sdf = new SimpleDateFormat('yyyy-MM-dd');
//                return sdf.format(new Date(doc['created_at'].value));
//            """)
//                )
//        )
//);

//Let’s sort blog posts by a custom formula that takes both the number of views and the user rating into account. A post with higher views but a lower rating might be sorted lower, while posts with high ratings but fewer views might be boosted.
//SearchRequest searchRequest = SearchRequest.of(s -> s
//        .index("blog_posts")
//        .query(q -> q
//                .match(m -> m
//                        .field("title")
//                        .query("latest trends")
//                )
//        )
//        .sort(sort -> sort
//                .script(s -> s
//                        .type(SortScriptType.Number)  // Custom sort based on numeric value
//                        .script("""
//                double ratingWeight = 0.7;
//                double viewsWeight = 0.3;
//                return (doc['rating'].value * ratingWeight) + (doc['views'].value * viewsWeight);  // Custom formula
//            """)
//                )
//        )
//);

//Let’s say we need to filter properties based on both price and a custom availability condition, such as properties that have been available for less than 30 days.
//SearchRequest searchRequest = SearchRequest.of(s -> s
//        .index("real_estate")
//        .query(q -> q
//                .bool(b -> b
//                        .must(m -> m
//                                .range(r -> r
//                                        .field("price")
//                                        .gte(100000)
//                                        .lte(500000)
//                                )
//                        )
//                        .filter(f -> f
//                                .script(sq -> sq
//                                        .source("""
//                        long daysAvailable = (new Date().getTime() - doc['listing_date'].value.getMillis()) / (1000 * 60 * 60 * 24);
//                        return daysAvailable <= 30;  // Filter properties listed in the last 30 days
//                    """)
//                                )
//                        )
//                )
//        )
//);
//SearchRequest searchRequest = SearchRequest.of(s -> s
//        .index("products")
//        .query(q -> q
//                .match(m -> m
//                        .field("product_name")
//                        .query("smartphone")
//                )
//        )
//        .scriptScore(ss -> ss
//                .script(sc -> sc
//                        .source("""
//                double score = _score;  // Start with the default score
//                if (doc['product_id.keyword'].value in params.viewed_products) {
//                    score *= 2;  // Boost the score for viewed products
//                }
//                return score;
//            """)
//                        .params(p -> p.put("viewed_products", viewedProductIds)  // List of product IDs user has viewed
//                        )
//                )
//        )
//);

//SearchRequest searchRequest = SearchRequest.of(s -> s
//        .index("blog")
//        .query(q -> q
//                .match(m -> m
//                        .field("title")
//                        .query("Cape Town")
//                )
//        )
//        .scriptScore(ss -> ss
//                .query(q -> q
//                        .match(m -> m
//                                .field("title")
//                                .query("Cape Town")
//                        )
//                )
//                .script(sc -> sc
//                        .source("doc['title.keyword'].value.contains('Cape') ? 1 : 0") // Painless script for custom score
//                )
//        )
//);
//SearchRequest searchRequest = SearchRequest.of(s -> s
//        .index("blog")
//        .query(q -> q
//                .bool(b -> b
//                        .must(m -> m
//                                .match(m1 -> m1
//                                        .field("title")
//                                        .query("Cape Town")
//                                )
//                        )
//                        .filter(f -> f
//                                .script(sq -> sq
//                                        .source("doc['title.keyword'].value.length() > 10") // Filter documents where title length is greater than 10
//                                )
//                        )
//                )
//        )
//);
//SearchRequest searchRequest = SearchRequest.of(s -> s
//        .index("blog")
//        .query(q -> q
//                .match(m -> m
//                        .field("title")
//                        .query("Cape Town")
//                )
//        )
//        .sort(sort -> sort
//                .script(s -> s
//                        .type(SortScriptType.Number) // Sort by a numeric script
//                        .script("doc['title.keyword'].value.length()") // Sort by title length
//                )
//        )
//);

//Map<String, JsonData> params = new HashMap<>();
//params.put("threshold", JsonData.of(4.9));
//ScriptSort scriptSort = new ScriptSort.Builder()
//        .type(ScriptSortType.Number)
//        .script(s -> s
//                .inline(builder -> builder.
//                        lang(ScriptLanguage.Painless)
//                        .params(params)
//                        .source("if (doc['rating'].size() != 0 && doc['rating'].value > params.threshold) { return true; } else { return false; }"))
//        )
//        .order(SortOrder.Asc)
//        .build();

//bulk index
//List<Product> products = fetchProducts();
//BulkRequest.Builder br = new BulkRequest.Builder();
//	for (Product product : products) {
//        br.operations(op -> op
//        .index(idx -> idx
//        .index("products")
//	            .id(product.getSku())
//        .document(product)
//	        )
//                    );
//                    }
//BulkResponse result = client.bulk(br.build());
//// Log errors, if any
//	if (result.errors()) {
//        logger.error("Bulk had errors");
//	    for (BulkResponseItem item: result.items()) {
//        if (item.error() != null) {
//        logger.error(item.error().reason());
//        }
//        }
//        }
//
//Combination + paging + sorting + grouping + aggregation query
//public PageVO queryUser(String indexName, UserDTO user) {
//    // 将多个查询条件放入list中.
//    List<Query> queries = new ArrayList<>();
//    Query byEducation = this.initMatchQuery("education", user.getEducation());
//    Query byAge = this.initRangeQuery("age", 18, 60);
//    queries.add(byEducation);
//    queries.add(byAge);
//    int offSet = user.getOffset();
//    // 因为es分页时，查找的是from到from+size的数据.
//    Integer from = offSet <= 0 ? 0 : apuserpLog.getLimit() * offSet;
//    try {
//
//        // 根据性别分组展示，且分组内只展示年龄最大的一条.
//        List<SortOptions> sorts = new ArrayList<>();
//        SortOptions sortName = SortOptionsBuilders.field(f -> f.field("age.keyword")
//                .order(SortOrder.Desc));
//        sorts.add(sortName);
//        // 从es中根据条件查询结果.
//        SearchResponse<User> searchResponse = client.search(s -> s
//                .index(indexName)
//                .query(q -> q
//                        .bool(b -> b.must(queries)
//                        )
//                        // 根据sex性别进行分组.
//                        .collapse(col -> col
//                                .field("sex.keyword").innerHits(inner -> inner
//                                        .name("group")
//                                        .ignoreUnmapped(true)
//                                        .from(0)
//                                        .size(0)
//                                        .sort(sorts)
//                                ))
//                        // 根据sex性别进行聚合.
//                        .aggregations("sex.keyword", a -> a.terms(t -> t.field("sex.keyword")
//                                .size(100000)))
//                        .from(from)
//                        .size(user.getLimit())
//                        // 按照创建正序排列.
//                        .sort(sort -> sort.field(f -> f.field("createTime.keyword").order(SortOrder.Asc)))
//                ), User.class);
//        List<Hit<User>> hitList = searchResponse.hits().hits();
//        List<User> userList = new ArrayList<>();
//        for (Hit<User> mapHit : hitList) {
//            userList.add(mapHit.source());
//        }
//        long total = searchResponse.hits().total() != null ? searchResponse.hits().total().value() : 0;
//
//        Map<String, Long> map = new HashMap<>();
//        searchResponse.aggregations().get("sex.keyword").sterms().buckets().array()
//                .forEach(f -> map.put(f.key().stringValue(), f.docCount()));
//        // 这里total改成实际的分组数据.
//        total = map.size();
//        // 这里将每一个分组出现的次数也进行了统计.
//        userList.forEach(user -> {
//            if (map.containsKey(user.getSex())) {
//                user.setCount(map.get(user.getSex()));
//            }
//        });
//        return new PageVO().setContent(userList)
//                .setSize(user.getLimit())
//                .setTotalElements(total)
//                .setTotalPages((total + user.getLimit() - 1) / user.getLimit());
//    }catch (Exception e) {
//        throw new RunExcepotion("【查询 -> 失败】从es中组合查询user出错，错误信息为：{}", e.getMessage());
//    }
//
//}

