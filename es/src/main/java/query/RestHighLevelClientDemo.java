package query;


import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.InternalAggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class RestHighLevelClientDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        RestClientBuilder builder = RestClient.builder(new HttpHost("172.17.172.97", 9200));
        builder.setMaxRetryTimeoutMillis(10000);
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder.build());

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("cluster*:test");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("book_name").field("name.keyword").size(10).subAggregation(AggregationBuilders.terms("author").field("author.keyword"));
        searchSourceBuilder.aggregation(termsAggregationBuilder);
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        //聚合结果
//        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        restHighLevelClient.searchAsync(searchRequest, new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                Aggregations aggregations = searchResponse.getAggregations();
                InternalAggregations aggregations1 = (InternalAggregations) aggregations;
                System.out.println(Strings.toString(aggregations1));
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                countDownLatch.countDown();
            }
        });
//        countDownLatch.await();
//        Aggregations aggregations =searchResponse.getAggregations();
//
//        InternalAggregations aggregations1 = (InternalAggregations) aggregations;
//
//        String s = Strings.toString(searchResponse);
//        System.out.println(s);

    }
}
