package com.baizhi.service.impl;

import com.baizhi.dao.BannerMapper;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Map<String, Object> queryByPager(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Integer start = (page - 1) * rows;
        List<Banner> list = bannerMapper.queryByPager(start, rows);
        /*
         *  jqgrid 返回  page  rows  records total
         * */
        //records  总条数
        Integer records = bannerMapper.queryRecords();
        //total 总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("page", page);
        map.put("rows", list);
        map.put("records", records);
        map.put("total", total);
        return map;
    }

    @Override
    public Map<String, Object> insertBanner(Banner banner) {
        Map<String, Object> map = new HashMap<>();
        String id = UUID.randomUUID().toString().replace("-", "");
        banner.setId(id);
        int i = bannerMapper.insertSelective(banner);
        map.put("i", i);
        map.put("bannerId", id);
        return map;
    }

    @Override
    public void bannerUpload(MultipartFile img, String bannerId, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/upload/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String realName = img.getOriginalFilename();
        String name = new Date().getTime() + "_" + realName;
        try {
            img.transferTo(new File(realPath, name));
            Banner banner = new Banner();
            banner.setId(bannerId);
            banner.setImg(name);
            bannerMapper.updateByPrimaryKeySelective(banner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateByPrimaryKeySelective(Banner record) {
        int i = bannerMapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    public int deleteByPrimaryKeys(String[] id) {
        int i = bannerMapper.deleteByPrimaryKeys(id);
        return i;
    }
}
