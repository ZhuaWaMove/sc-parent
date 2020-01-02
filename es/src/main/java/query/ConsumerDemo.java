package query;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsumerDemo {

    public static void main(String[] args) {
        testConsumer();
    }

    public static void testConsumer(){
        Stream<String> stream = Stream.of("hello", "felord.cn");
        stream.peek(System.out::println).collect(Collectors.toList());;
    }
}
