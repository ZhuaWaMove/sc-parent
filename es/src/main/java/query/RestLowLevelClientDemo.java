//package query;
//
//import org.apache.http.HttpHost;
//import org.apache.http.message.BasicHeader;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.Response;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.common.io.stream.StreamInput;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.Aggregations;
//import org.elasticsearch.search.aggregations.InternalAggregation;
//import org.elasticsearch.search.aggregations.InternalAggregations;
//import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
//import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RestLowLevelClientDemo {
//
//    public static void main(String[] args) throws IOException {
//
//        RestClient restClient = RestClient.builder(new HttpHost("172.17.172.97", 9201)).build();
//
//
//
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("test");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("book_name").field("name.keyword");
//        searchSourceBuilder.aggregation(termsAggregationBuilder);
//        searchRequest.source(searchSourceBuilder);
//        //聚合结果
//        Response searchResponse = restClient.performRequest("POST", "test/_search");
//        StreamInput content = (StreamInput) searchResponse.getEntity().getContent();
//
//        InternalAggregation internalAggregation = new StringTerms(content);
//        InternalAggregation.ReduceContext context = new InternalAggregation.ReduceContext(null, null, true);
//        List<InternalAggregations> aggres = new ArrayList<>();
//
////        aggres.add((InternalAggregations) aggregations);
//        //执行合并操作。
//        InternalAggregations reduceResult = InternalAggregations.reduce(aggres, context);
//
////        System.out.println(reduceResult.asList().get(0).getName());
//    }
//}
