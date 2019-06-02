package prefixtree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by GL-shala on 2019/1/19.
 *
 * 广度优先搜索
 */
public class Node {
    private int user_id;//节点的名称 这里是用户的id
    private HashSet<Integer> friends = null;//使用hash映射存放项链的朋友节点，便于确认和某个用户是否相连。
    private int degree;//用于存放和给定用户节点 是几度haoyou

    public Node(int user_id) {
        this.user_id = user_id;
        this.friends = new HashSet<>();
        this.degree = 0;
    }

    /**
     * 获取用户关系nodes
     * @param user_num      用户数量
     * @param relation_num  相识关系度数
     * @return              包含用户的节点数组
     */
    public static Node[] getNode(int user_num, int relation_num){
        Node[] user_nodes = new Node[user_num];
        //生成所有表示用户的节点
        for (int i = 0; i < user_num; i++){
            user_nodes[i] = new Node(i);
        }
        Random rand = new Random();
        //生成所有表示好友关系的边
        for (int i = 0; i < relation_num; i++){
            int friend_a_id = rand.nextInt(user_num);
            int friend_b_id = rand.nextInt(user_num);
            if (friend_a_id == friend_b_id) continue;//如果生成的两个好友id相同则跳过
            Node friend_a = user_nodes[friend_a_id];
            Node friend_b = user_nodes[friend_b_id];
            friend_a.friends.add(friend_b_id);
            friend_b.friends.add(friend_a_id);

        }
        return user_nodes;
    }

    public static void bfs(Node[] user_nodes, int user_id){
        if (user_id > user_nodes.length) return;
        Queue<Integer> queue = new LinkedList<>();//用于广度优先搜索的队列
        queue.offer(user_id);//放入初始队列
        //存放已经别访问过的用户 防止产生回路
        HashSet<Integer> visited = new HashSet<>();
        visited.add(user_id);
        while (! queue.isEmpty()){
            int current_user_id = queue.poll();//取出列头第一个节点
            if(user_nodes[current_user_id] == null) continue;

            //遍历刚刚拿出的这个节点，的所有直接连接节点，并加入队列尾部
            for (int friend_id : user_nodes[current_user_id].friends){
                if(user_nodes[friend_id] == null) continue;
                if(visited.contains(friend_id)) continue;
                queue.offer(friend_id);
                visited.add(friend_id);//记录已经访问过的节点
                //好友度数是当前好友度数加1
                user_nodes[friend_id].degree = user_nodes[current_user_id].degree + 1;
                System.out.println(String.format("\t%d 度好友：%d", user_nodes[friend_id].degree, friend_id));

            }


        }


    }
    public static void main(String[] args) {
        Node[] nodes = getNode(50, 20);
        for (Node n : nodes){
            System.out.println(n.user_id+":"+n.friends+":"+n.degree);
        }
        bfs(nodes, 0);
    }
}
