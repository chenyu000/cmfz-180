package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.BannerMapper;
import com.baizhi.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Cmfz180ApplicationTests {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BannerMapper bannerMapper;

    @Test
    public void contextLoads() {
        List<Banner> banners = bannerMapper.queryByPager(1, 2);
        System.out.println(banners);
    }


    @Test
    public void contextLoads11() {
        List<Banner> banners = bannerMapper.queryByPager(1, 2);
        System.out.println(banners);
    }

}
