//package query;
//
//import io.searchbox.core.Search;
//import io.searchbox.core.SearchResult;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.Strings;
//import org.elasticsearch.common.io.stream.InputStreamStreamInput;
//import org.elasticsearch.common.io.stream.StreamOutput;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.xcontent.ToXContent;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentFactory;
//import org.elasticsearch.common.xcontent.json.JsonXContent;
//import org.elasticsearch.index.query.QueryStringQueryBuilder;
//import org.elasticsearch.search.aggregations.*;
//import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
//import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
//import org.elasticsearch.search.aggregations.bucket.terms.Terms;
//import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.stream.Stream;
//
//
//public class TransportClientDemo {
//    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
//
//        List<InternalAggregations> ias = new ArrayList<>();
//        InternalAggregation.ReduceContext context = new InternalAggregation.ReduceContext(null, null, true);
//        // on shutdown
//        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("book_name").field("name.keyword")
//                .subAggregation(AggregationBuilders.terms("author").field("author.keyword"));
//
//        Settings settings = Settings.builder()
//                .put("cluster.name", "crossCluster").put("client.transport.sniff", true).build();
//        // on startup
//        TransportClient crossClusterclient = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//        SearchResponse searchResponse = crossClusterclient.prepareSearch("cluster*:test").addAggregation(termsAggregationBuilder).setSize(10).execute().get();
//        Aggregations aggregations = searchResponse.getAggregations();
//
//        Settings settings1 = Settings.builder()
//                .put("cluster.name", "cluster1").put("client.transport.sniff", true).build();
//        Client cluster1Client = new PreBuiltTransportClient(settings1)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9301));
//        SearchResponse searchResponse1 = cluster1Client.prepareSearch("test").addAggregation(termsAggregationBuilder).setSize(0).execute().get();
//
//        ias.add((InternalAggregations) searchResponse1.getAggregations());
//
//        Settings settings2 = Settings.builder()
//                .put("cluster.name", "cluster2").put("client.transport.sniff", true).build();
//        TransportClient cluster2Client = new PreBuiltTransportClient(settings2)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9302));
//        SearchResponse searchResponse2 = cluster2Client.prepareSearch("test").addAggregation(termsAggregationBuilder).setSize(0).execute().get();
//        ias.add((InternalAggregations) searchResponse2.getAggregations());
//        //合并数据
//        InternalAggregations reduce1 = InternalAggregations.reduce(ias, context);
//
//        aggregations2String(reduce1);
//
//        cluster2Client.close();
//        cluster1Client.close();
//        crossClusterclient.close();
//
//        Search.Builder searchBuilder = new Search.Builder("")
//                .addIndex("st1index")
//                .addType("st1type");
//
//    }
//
//    /**
//     * 仅输出aggregations 的聚合数据 为字符串
//     * @param aggregations
//     * @throws IOException
//     */
//     static void aggregations2String(Aggregations aggregations) throws IOException {
//         /* 将片段转为json*/
//        XContentBuilder builder = XContentFactory.jsonBuilder();
//        builder.startObject();
//        aggregations.toXContent(builder, ToXContent.EMPTY_PARAMS);
//        builder.endObject();
//        String string = builder.string();
//        System.out.println(string);
//    }
//
//    /**
//     * 将响应的所有数据输出为字符串
//     * @param searchResponse
//     * @throws IOException
//     */
//     static void searchResponse2String(SearchResponse searchResponse){
//        /*将 SearchResponse 转为字符串表示形式*/
//        String s = Strings.toString(searchResponse);
//        System.out.println(s);
//        /*如何将json响应转为 SearchRespone*/
//    }
//
//    /**
//     * 将json 转换为 SearchResponse
//     * @param responseJson
//     */
//    static void string2SearchResponse(String responseJson){
//        /*如何将json响应转为 SearchRespone*/
//
//    }
//
//
//}
