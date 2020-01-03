package com.baizhi.controller;

import com.baizhi.entity.MapDto;
import com.baizhi.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private EchartsService echartsService;

    @RequestMapping("getData")
    public List<Integer> getData() {
        List<Integer> list = new ArrayList<>();
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        return list;
    }

    @RequestMapping("getMapData")
    public List<Map<String, Object>> getMapData() {
        List<Map<String, Object>> list11 = new ArrayList<>();


        List<MapDto> list = new ArrayList<>();
        list.add(new MapDto("北京", new Random().nextInt(1000)));
        list.add(new MapDto("天津", new Random().nextInt(1000)));
        list.add(new MapDto("上海", new Random().nextInt(1000)));
        list.add(new MapDto("四川", new Random().nextInt(1000)));
        list.add(new MapDto("台湾", new Random().nextInt(1000)));
        list.add(new MapDto("辽宁", new Random().nextInt(1000)));
        list.add(new MapDto("陕西", new Random().nextInt(1000)));


        for (MapDto mapDto : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", mapDto.getName());
            map.put("value", mapDto.getValue());
            list11.add(map);
        }

       /* List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","北京");
        map.put("value",new Random().nextInt(1000));
        list.add(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("name","四川");
        map1.put("value",new Random().nextInt(1000));
        list.add(map1);
        System.out.println(list);

        Map<String,Object> map2 = new HashMap<>();
        map2.put("name","天津");
        map2.put("value",new Random().nextInt(1000));
        list.add(map2);*/


        return list11;
    }


    @RequestMapping("/monthAll")
    public List<Integer> getMonthAll() {

        return this.echartsService.queryMonthByRegister();
    }


}
