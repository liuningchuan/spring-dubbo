package com.liuning.test.function;

@FunctionalInterface
public interface FunctionTest {

    /**
     * 抽象方法，只能有一个
     */
    void doExecute();

    /**
     * 默认方法
     */
    default void defaultMethod() {
        System.out.println("Function Interface Defalut Method");
    }

    /**
     * 静态方法
     */
    static void staticMethod() {
        System.out.println("Function Interface Static Method");
    }
}
