package base;

import java.util.Arrays;

public class 小问题 {
    public static void main(String[] args) {
        //        String str = "ab";
//        str.chars().filter(x->x>3).forEach(x-> System.out.println(x));
//
//        Arrays.asList(4,5,9,10).parallelStream()
//                .filter(m->{
//                    System.out.println(m);
//                    return (m>1);
//                }).map(m -> m+1)
//                .collect(Collectors.groupingBy(x ->x%10))
//                .forEach((x,y)-> System.out.println(y));

//
//        Map<Integer,Integer> map = new HashMap<>();
//        map.put(5,6);
//        System.out.println(map.get(4));
    }

    private static void ArraysTest() {
        int a;
        Object o=null;
        if(o instanceof Arrays){
            System.out.println("666");
        }


        Integer[] integer=new Integer[5];
        if(integer instanceof Integer[]){
            System.out.println("666");
        }
    }
}
