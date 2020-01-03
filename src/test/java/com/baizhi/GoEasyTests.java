package com.baizhi;

import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoEasyTests {
    @Test
    public void test() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-b121a256bfeb4d0e874c71b5a744d092");
        goEasy.publish("180_Channel", "你好啊,小老弟!");
    }

    @Test
    public void test1() {
        /* for (int i = 0; i < 50; i++) {*/
        ArrayList<Object> list = new ArrayList<>();
        list.add(new Random().nextInt(1000));
        list.add(new Random().nextInt(1000));
        list.add(new Random().nextInt(1000));
        list.add(new Random().nextInt(1000));
        list.add(new Random().nextInt(1000));
        list.add(new Random().nextInt(1000));
        System.out.println(list.toString());
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-b121a256bfeb4d0e874c71b5a744d092");
        goEasy.publish("echarts", list.toString());

        /*  }*/
    }
}
