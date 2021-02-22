package test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void reduceTest() {
        int result = Stream.of(1, 2, 3, 4)
                .reduce(100, (t1, t2) -> t1 + t2);
        System.out.println(result);

        int result2 = Stream.of(1, 2, 3, 4)
                .reduce(100, Integer::sum);
        System.out.println(result2);

        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + nums.stream().filter(num -> num != null).
                distinct().mapToInt(num -> num * 2).
                peek(System.out::println).skip(2).limit(4).sum());

        System.out.println("core size is : " + Runtime.getRuntime().availableProcessors());
        System.out.println("free memory is : " + Runtime.getRuntime().freeMemory());
        System.out.println("total mempry is : " + Runtime.getRuntime().totalMemory());
        System.out.println("max memory is : " + Runtime.getRuntime().maxMemory() / (1024.0 * 1024.0 * 1024.0) + " GB");
    }
}
