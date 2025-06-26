package org.brainacad.testlib;

import com.google.common.collect.*;

import java.io.*;
import java.util.*;

public class TestMaven {
    public static void main(String[] args) throws IOException {
        List<String> fruits = Lists.newArrayList("orange", "banana", "kiwi");
        fruits.forEach(System.out::println);

        List<String> reverseFruits = Lists.reverse(fruits);
        reverseFruits.forEach(System.out::println);

        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("key", "firstValue");
        map.put("key", "secondValue");

        System.out.println(map);

        Properties prop = new Properties();
        try (InputStream input = TestMaven.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
            System.out.println(prop.getProperty("props.local.hello"));
            System.out.println(prop.getProperty("props.mvn.hello"));
        }
    }
}
