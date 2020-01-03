package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface BannerService {
    Map<String, Object> queryByPager(Integer page, Integer rows);

    Map<String, Object> insertBanner(Banner banner);

    void bannerUpload(MultipartFile img, String bannerId, HttpSession session);

    int updateByPrimaryKeySelective(Banner record);

    int deleteByPrimaryKeys(String[] id);
}
