package tech.elastic.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Script;
import co.elastic.clients.elasticsearch._types.ScriptLanguage;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionScore;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.elastic.dto.ExampleDocument;

import javax.print.attribute.standard.OrientationRequested;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EsService {
    @Autowired
    public ElasticsearchClient client;
    public String indexName = "student";

    public void scriptQuery(String keyword, double latitude, double longitude, String indexName) throws IOException {

        List<FunctionScore> functionScores = new ArrayList<>();

        Map<String, JsonData> params = new HashMap<>();
        String prPrefix = "Pr " + keyword.trim();
        String prDotPrefix = "Pr. " + keyword.trim();
        params.put("prPrefix", JsonData.of(prPrefix));
        params.put("prPrefixLength", JsonData.of(prPrefix.length()));
        params.put("prDotPrefix", JsonData.of(prDotPrefix));
        params.put("prDotPrefixLength", JsonData.of(prDotPrefix.length()));

        Script nameScript = Script.of(builder -> builder
                .inline(inlineBuilder -> inlineBuilder
                        .lang(ScriptLanguage.Painless)  // Set the language to Painless
                        .source(
                                "def str = doc['name.keyword'].value.trim(); " +
                                        "str.length() >= params.prDotPrefixLength ? (str.substring(0, params.prPrefixLength).equalsIgnoreCase(params.prPrefix) || " +
                                        "str.substring(0, params.prDotPrefixLength).equalsIgnoreCase(params.prDotPrefix) ? " +
                                        "str.substring(0, str.length()).equalsIgnoreCase(params.prPrefix) || " +
                                        "str.substring(0, str.length()).equalsIgnoreCase(params.prDotPrefix) ? 5 : 2 : 1) : 1"
                        )  // Set the script source
                        .params(params)  // Pass the parameters to the script
                )
        );

        Script locationScore = Script.of(builder -> builder
                .inline(inlineBuilder -> inlineBuilder
                        .lang(ScriptLanguage.Painless)  // Set the language to Painless
                        .source(
                                "def distance = (doc['author.location'].arcDistance(params.latitude, params.longitude))/1000; "
                                        + "if (distance <= 3 ) 1.1;"
                                        + "else if (distance > 3 && distance <=7)  1.08;"
                                        + "else if (distance > 7 && distance <=20 )  1.06;"
                                        + "else 1;"
                        )  // Set the script source
                        .params(new HashMap<String, JsonData>() {
                            {
                                put("latitude", JsonData.of(latitude));
                                put("longitude", JsonData.of(longitude));
                            }})
                )
        );
        functionScores.add(FunctionScore.of(f -> f
                .scriptScore(script1 -> script1.script(nameScript))  // Add name-based script score
        ));
        functionScores.add(FunctionScore.of(f -> f
                .scriptScore(script2 -> script2.script(locationScore))  // Add location-based script score
        ));
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(indexName)
                .query(src -> src
                        .functionScore(f -> f
                                .query(q1 -> q1
                                        .bool(b -> b
                                                .must(m -> m
                                                        .match(mq -> mq
                                                                .field("fieldName")
                                                                .query("searchTerm")
                                                        )
                                                )
                                                .filter(ft -> ft
                                                        .range(r -> r
                                                                .field("age")
                                                                .gte(JsonData.of("20"))
                                                                .lte(JsonData.of("30"))
                                                        )
                                                )
                                        )       )
                                .functions(functionScores)
                        )
                )
        );
        SearchResponse<ExampleDocument> response = client.search(searchRequest, ExampleDocument.class);
    }
}
//boolean flag = client.exists(req -> req.index(iName)).value();
//boolean result = client.create(req -> req.index(iName)).acknowledged();
//boolean isDelete = client.indices().delete(req -> req.index("indexName")).acknowledged();


//Product product = new Product("bk-1", "City bike", 123.0);
//IndexResponse response = client.index(i -> i
//        .index("products")
//        .id(product.getSku())
//        .document(product)
//);
//OR
//IndexRequest<Product> request = IndexRequest.of(i -> i
//        .index("products")
//        .id(product.getSku())
//        .document(product)
//);
//IndexResponse response = client.index(request);


//String scriptSource = "doc['content.keyword'].value.length() * params.factor";
//double factor = 1.1;
//public SearchResponse<Map> searchWithScriptSort(String indexName, String fieldName, String queryText, String scriptSource, double factor) throws IOException {
//    Query query = MatchQuery.of(m -> m.field(fieldName).query(queryText))._toQuery();
//
//    Script script = Script.of(s -> s.inline(i -> i
//            .source(scriptSource)
//            .params("factor", JsonData.of(factor))));
//
//    SortOptions sortOptions = SortOptions.of(so -> so.script(ss -> ss
//            .script(script)
//            .type(ScriptSortType.Number)
//            .order(SortOrder.Asc)));
//
//    SearchRequest searchRequest = new SearchRequest.Builder()
//            .index(indexName)
//            .query(query)
//            .sort(sortOptions)
//            .build();
//
//    return client.search(searchRequest, Map.class);
//}


//Script recentlyVisitedScript = new Script(
//        ScriptType.INLINE,
//        "painless",
//        "for(entry in params.visitCount.entrySet()){ if(doc['id'].value.toString().equals(entry.getKey())) { return entry.getValue()+1; } } return 1;",
//        Collections.singletonMap(
//                "visitCount",
//                recentlyVisitedPrIds.stream()
//                        .collect(Collectors.groupingBy(e -> e.toString(), Collectors.counting()))
//        )
//);

//private Map<String, Object> getScriptParamsForSearch(String value) {
//    String trimmedValue = value.trim();
//    Map<String, Object> map = new HashMap<>();
//    map.put("searchQuery", trimmedValue);
//    map.put("length", trimmedValue.length());
//    return map;
//}

//public Script getStartsWithScript(String value, String field) {
//    return new Script(ScriptType.INLINE, "painless", "def str = doc['" + field + ".keyword'].value.trim(); (str.length() >= params.length) " +
//            "? str.substring(0, params.length).equalsIgnoreCase(params.searchQuery) ? str.substring(0, str.length()).equalsIgnoreCase(params.searchQuery) ? 15 : 10 : 1 : 1",
//            getScriptParamsForSearch(value));
//}
