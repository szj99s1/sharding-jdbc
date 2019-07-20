package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {
    public static void main(String[] args){
        Thread d = new Thread(new Runnable() {
            public void run() {
                System.out.println("aaa");
            }
        });
        d.start();

        Thread e = new Thread(()->System.out.println("bb"));
        e.start();
        List<String> names = Arrays.asList("asdfasdfsdfsdfsdfsdfdsdfsd","b","c");
        String linkNames = names.stream().collect(Collectors.joining(", "));
        System.out.println(linkNames);
        names.forEach(n->System.out.println(n));
        names.forEach(System.out::println);

        Runnable sleeper = ()->{System.out.println("zzz");};
        new Thread(sleeper).start();

        long count = names.parallelStream().filter(x->x.length()>12).count();
        System.out.println(count);

        long count1 = names.stream().filter(x->x.length()>12).count();
        System.out.println(count1);
        //Predicate
        m1((s) -> s.length()>=5,"hello");
        m2((s) -> s.contains("H"), (s) -> s.contains("W"), "Helloworld");
        //Function
        m3((s)->Integer.parseInt(s)+3,"23");
        m4((s)->Integer.parseInt(s),(n)->n+10,"20");
        //Stream
        List<String> list = new ArrayList<String>();
        list. add("张无忌") ;
        list. add("周芷若") ;
        list. add("赵敏") ;
        list. add("张强") ;
        list. add("张三丰") ;
        list.stream().filter((str)->str.startsWith("张")).forEach(System.out::println);
        System.out.println( list.stream().filter((str)->str.startsWith("张")).count());
        list.stream().filter(s->s.startsWith("张")).limit(2).forEach(System.out::println);
        list.stream().map((s)->s.length()).forEach(System.out::println);


        List<Integer> list1 = new ArrayList<>();
        for(int i=0;i<500;i++){
            list1.add(i);
        }

        new Thread(()->{
            long start = System.currentTimeMillis();

            System.out.println(list1.stream().
                    filter((n)-> n % 2 == 0)
                    .filter(n -> n % 4 == 0)
                    .filter(n -> n % 5 == 0)
                    .filter(n -> n % 3 == 0)
                    .count());//1331 毫秒
            long end = System.currentTimeMillis();
            System.out.println("普通流，时间：" + (end - start) + " 毫秒");
        }).start();

        new Thread(()->{
            long start = System.currentTimeMillis();
            System.out.println(list1.stream().parallel().
                    filter((n)-> n % 2 == 0)
                    .filter(n -> n % 4 == 0)
                    .filter(n -> n % 5 == 0)
                    .filter(n -> n % 3 == 0)
                    .count());//1331 毫秒

            long end = System.currentTimeMillis();
            System.out.println("并行流，时间：" + (end - start) + " 毫秒");
        }).start();


        Stream<String> stream = Stream.of("10", "20", "30", "40");
///*        List<String> strList = stream.collect(Collectors.toList());
//        for(string s:strlist){
//            system.out.println(s);
//        }*/
      //  Set<String> strSet =  stream.collect(Collectors.toSet());
        System.out.println(stream.collect(Collectors.joining(", ")));
    }


    public static void  m1(Predicate<String> p, String s){
        boolean b = p.test(s);
        System.out.println("字符串 " + s + " 的长度是否很长：" + (b ? "很长" : "不长"));
    }

    public static void m2(Predicate<String> p1,Predicate<String> p2,String str){
        boolean b = p1.and(p2).test(str);
        System.out.println("字符串：" + str + " 是否即包含大写的H又包含大写的W :" + b);
    }

    public static  void m3(Function<String,Integer> f, String string){
       int t = f.apply(string);
        System.out.println("转换后的结果 + 10 : " + (t + 10));
    }

    public static void m4(Function<String,Integer> f,Function<Integer,Integer> f1,String string){
        int t = f.andThen(f1).apply(string);
        System.out.println("转换后的结果 + 10 : " + (t + 10));
    }

}
