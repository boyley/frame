package com.bogle.frame;

import java.util.Optional;
import java.util.function.Function;

/**
 * Created by Administrator on 2015/8/13.
 */
public class Test {

    @org.junit.Test
    public void test1() {
        Optional<String> name = Optional.of(null);
        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));
    }
}
