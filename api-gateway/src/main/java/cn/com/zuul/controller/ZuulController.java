package cn.com.zuul.controller;

import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.collections.map.UnmodifiableOrderedMap;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by new on 2017/12/24.
 */
@RestController
public class ZuulController {

    public static void main(String[] args) {
        String[] strs = {"aa","bb","cc"};
//String数组转List
        List<String> strsToList1= Arrays.asList(strs);
        for(String s:strsToList1){
            System.out.println(s);
        }
        LRUMap lruMap = new LRUMap();
        lruMap.put("1","a");
        lruMap.put("2","b");
        lruMap.put("3","c");
        lruMap.put("4","d");
        lruMap.put("5","d");
        lruMap.put("6","d");
        lruMap.put("7","d");
        lruMap.put("4","d");
        System.out.println(lruMap.orderedMapIterator());
        lruMap.orderedMapIterator();
        OrderedMap decorate = UnmodifiableOrderedMap.decorate(lruMap);
        decorate.orderedMapIterator();
        System.out.println(decorate.orderedMapIterator().previous());
    }
}
