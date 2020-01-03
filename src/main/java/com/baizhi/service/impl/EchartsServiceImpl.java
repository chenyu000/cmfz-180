package com.baizhi.service.impl;

import com.baizhi.commons.MapDto;
import com.baizhi.dao.UserMapper;
import com.baizhi.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Integer> queryMonthByRegister() {

        List<MapDto> mapDtos = userMapper.selectMonthAllByRegister();
        return this.resultToMapDto(mapDtos);
    }


    private List<Integer> resultToMapDto(List<MapDto> mapDtos) {

        Map<Integer, Integer> map = new HashMap<>();
        for (MapDto mapDto : mapDtos) {
            map.put(mapDto.getMonth(), mapDto.getCount());
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            if (map.get(i) == null) {
                result.add(0);
            } else {
                result.add(map.get(i));
            }
        }
        return result;
    }

}
