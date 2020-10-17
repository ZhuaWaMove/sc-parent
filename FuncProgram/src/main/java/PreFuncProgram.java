import model.Apple;

import java.util.ArrayList;
import java.util.List;

public class PreFuncProgram {
    public static void main(String[] args) {

    }

    /**
     * 通过for循环和if判断实现绿苹果筛选
     * 优点：简单
     * 缺点：扩展性低，如果加入新的水果筛选需要写很多if判断，代码重复率高
     *
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        //仅仅筛选出绿苹果
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 改进1：将颜色作为参数传入，根据颜色进行筛选。
     * 优点：支持多种类型的水果筛选
     * 缺点：如果进行重量筛选则需要重写此方法，不具备扩展性
     *
     * @param inventory
     * @param color
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        //颜色作为参数
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 当需要对苹果的多个属性进行筛选时，
     * 依据随着属性的增多，此方法需要不断的修改，扩展性低
     *
     * @param inventory
     * @param color
     * @param weight
     * @param flag
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (flag && color.equals(apple.getColor()) || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 由于行为不同导致方法需要被重写，如果不进行很好的设计，方法会越来越臃肿，充斥很多if判断条件，非常不利于扩展。
     * 考虑将行为进行参数化，作为参数传进入方法内，方法根据不同的行为作为不同响应，极容易扩展。
     */
    //封装了对选择苹果的策略
    public interface ApplePredicate {
        //具体算法交给子类去实现
        boolean test(Apple apple);
    }

    //颜色算法
    public class AppleGreenColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    //重量算法
    public class AppleHeavyWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        //行为参数化
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static void testFilterApples() {
        List<Apple> inventory = new ArrayList<>();
        List<Apple> result = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));

    }

    public interface Predicate<T> {
        boolean test(T t);
    }
    //映入类型参数T
    public static<T> List<T> filter(List<T> list,Predicate<T> p){
        List<T> result =new ArrayList<>();
        for (T e : list) {
            if (p.test(e)){
                result.add(e);
            }
        }
        return result;
    }
}
