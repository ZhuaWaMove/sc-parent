package prefixtree;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multiset;
import lombok.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GL-shala on 2019/1/18.
 */
@Data
public class TreeNode {
    private char lable;//节点名称 在前缀树里是一个字母
    private Map<Character, TreeNode> sons;//使用哈希映射存放子节点，便于确认是否已经添加某个字母对应的节点
    private String prefix;//从树的根到到当前节点这条通路上，全部字母所组成的前缀，boy对于字母o前缀是b,y前缀是bo
    private String explanation;//词条的描述

    public TreeNode(char lable, String prefix, String explanation) {
        this.lable = lable;
        this.sons = new HashMap<>();
        this.prefix = prefix;
        this.explanation = explanation;
    }

    static String  pre = null;
    public static void creatTreeNode(String word, TreeNode parent){

        char c = word.toCharArray()[0];
        TreeNode found = null;
        if(parent.getSons().containsKey(c)){
            found = parent.getSons().get(c);
        }else {
            TreeNode son = new TreeNode(c, parent.getLable()+"", " ");
            parent.getSons().put(c, son);
            found = son;
        }
        if(word.indexOf(c)+1 >= word.length()) return;
        creatTreeNode(word.substring(word.indexOf(c)+1) , found);
    }

//    public static void main(String[] args) {
//        int s = 1;
//        String[] str = {"word","order","ren","wen"};
//        TreeNode root = new TreeNode('0', "", "root");
//        for (int i = 0; i < str.length-1;i++){
//            creatTreeNode(str[i], root);
//
//        }


//        System.out.println(root);
////        ch(str);
//    }

    private static void ch(String str){
        char c = str.toCharArray()[0];
        System.out.println(c);
        if(str.indexOf(c)+1 >= str.length()) return;
        str = str.substring(str.indexOf(c) + 1);
        ch(str);
    }

    public static void main(String[] args) {
        ArrayListMultimap<Integer,Object> amap = ArrayListMultimap.create();
        amap.put(1,"q1");
        amap.put(1,"q11");
        amap.put(1,"q111");
        amap.put(1,"q1111");
        amap.put(1,"q11111");
        amap.put(2,"q3");
        amap.put(2,"q4");
        amap.put(3,"q");
        amap.put(3,"q");
        amap.put(3,"q");
        amap.put(5,"q");
        amap.put(5,"q");
        Multiset<Integer> keys = amap.keys();
        System.out.println("keys: "+keys);
        Collection<Map.Entry<Integer, Object>> entries = amap.entries();
        Collection<Object> values = amap.values();
        System.out.println("values:"+values);
        System.out.println(amap.asMap());
        amap.asMap().entrySet().forEach(e->{
            e.getKey();
        });
        amap.asMap().get(1).forEach(k->{
            //求和 sum
        });
    }
}
