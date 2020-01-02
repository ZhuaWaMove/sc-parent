//package query;
//
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.ShardSearchFailure;
//import org.elasticsearch.common.ParseField;
//import org.elasticsearch.common.io.stream.StreamInput;
//import org.elasticsearch.common.xcontent.ToXContent;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentFactory;
//import org.elasticsearch.common.xcontent.XContentParser;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.aggregations.Aggregations;
//import org.elasticsearch.search.aggregations.InternalAggregation;
//import org.elasticsearch.search.aggregations.InternalAggregations;
//import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
//import org.elasticsearch.search.suggest.Suggest;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.StreamSupport;
//
//import static org.elasticsearch.common.xcontent.XContentParserUtils.ensureExpectedToken;
//
//public class SearchResponeSub extends SearchResponse {
//
//    private static final ParseField SCROLL_ID = new ParseField("_scroll_id");
//    private static final ParseField TOOK = new ParseField("took");
//    private static final ParseField TIMED_OUT = new ParseField("timed_out");
//    private static final ParseField TERMINATED_EARLY = new ParseField("terminated_early");
//    private static final ParseField NUM_REDUCE_PHASES = new ParseField("num_reduce_phases");
//
//    public static SearchResponse fromXContent(XContentParser parser) throws IOException {
//        ensureExpectedToken(XContentParser.Token.START_OBJECT, parser.nextToken(), parser::getTokenLocation);
//        XContentParser.Token token;
//        String currentFieldName = null;
//        SearchHits hits = null;
//        Aggregations aggs = null;
//        Suggest suggest = null;
//        SearchProfileShardResults profile = null;
//        boolean timedOut = false;
//        Boolean terminatedEarly = null;
//        int numReducePhases = 1;
//        long tookInMillis = -1;
//        int successfulShards = -1;
//        int totalShards = -1;
//        int skippedShards = 0; // 0 for BWC
//        String scrollId = null;
//        List<ShardSearchFailure> failures = new ArrayList<>();
//        while((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
//            if (token == XContentParser.Token.FIELD_NAME) {
//                currentFieldName = parser.currentName();
//            } else if (token.isValue()) {
//                if (SCROLL_ID.match(currentFieldName)) {
//                    scrollId = parser.text();
//                } else if (TOOK.match(currentFieldName)) {
//                    tookInMillis = parser.longValue();
//                } else if (TIMED_OUT.match(currentFieldName)) {
//                    timedOut = parser.booleanValue();
//                } else if (TERMINATED_EARLY.match(currentFieldName)) {
//                    terminatedEarly = parser.booleanValue();
//                } else if (NUM_REDUCE_PHASES.match(currentFieldName)) {
//                    numReducePhases = parser.intValue();
//                } else {
//                    parser.skipChildren();
//                }
//            } else if (token == XContentParser.Token.START_OBJECT) {
//                if (SearchHits.Fields.HITS.equals(currentFieldName)) {
//                    hits = SearchHits.fromXContent(parser);
//                } else if (Aggregations.AGGREGATIONS_FIELD.equals(currentFieldName)) {
////                    aggs = Aggregations.fromXContent(parser);
//                    aggs = InternalAggregations.fromXContent(parser);
//                } else if (Suggest.NAME.equals(currentFieldName)) {
//                    suggest = Suggest.fromXContent(parser);
//                } else if (SearchProfileShardResults.PROFILE_FIELD.equals(currentFieldName)) {
//                    profile = SearchProfileShardResults.fromXContent(parser);
//                } else if (RestActions._SHARDS_FIELD.match(currentFieldName)) {
//                    while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
//                        if (token == XContentParser.Token.FIELD_NAME) {
//                            currentFieldName = parser.currentName();
//                        } else if (token.isValue()) {
//                            if (RestActions.FAILED_FIELD.match(currentFieldName)) {
//                                parser.intValue(); // we don't need it but need to consume it
//                            } else if (RestActions.SUCCESSFUL_FIELD.match(currentFieldName)) {
//                                successfulShards = parser.intValue();
//                            } else if (RestActions.TOTAL_FIELD.match(currentFieldName)) {
//                                totalShards = parser.intValue();
//                            } else if (RestActions.SKIPPED_FIELD.match(currentFieldName)) {
//                                skippedShards = parser.intValue();
//                            } else {
//                                parser.skipChildren();
//                            }
//                        } else if (token == XContentParser.Token.START_ARRAY) {
//                            if (RestActions.FAILURES_FIELD.match(currentFieldName)) {
//                                while((token = parser.nextToken()) != XContentParser.Token.END_ARRAY) {
//                                    failures.add(ShardSearchFailure.fromXContent(parser));
//                                }
//                            } else {
//                                parser.skipChildren();
//                            }
//                        } else {
//                            parser.skipChildren();
//                        }
//                    }
//                } else {
//                    parser.skipChildren();
//                }
//            }
//        }
//        XContentBuilder builder = XContentFactory.smileBuilder();
////        builder.startObject();
//        aggs.toXContentInternal(builder, ToXContent.EMPTY_PARAMS);
////        builder.endObject();
//        StreamInput streamInput = builder.bytes().streamInput();
////        InternalAggregations internalAggregations = InternalAggregations.readOptionalAggregations(streamInput);
//        List<InternalAggregation> agges = StreamSupport.stream(aggs.spliterator(), false).map((p) -> {
//
//            return (StringTerms) p;
//        }).collect(Collectors.toList());
//
//        SearchResponseSections searchResponseSections = new SearchResponseSections(hits, aggs, suggest, timedOut, terminatedEarly, profile, numReducePhases);
//        //如何将Aggregations 转换为 InternalAggregations
////        InternalSearchResponse internalSearchResponse = new InternalSearchResponse(hits, aggs, suggest, profile, timedOut, terminatedEarly, numReducePhases);
//        return new SearchResponse(searchResponseSections, scrollId, totalShards, successfulShards, skippedShards, tookInMillis,
//                failures.toArray(new ShardSearchFailure[failures.size()]));
//    }
//}
