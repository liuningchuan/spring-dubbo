package com.liuning.test;

import org.junit.Test;

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
    }
}
