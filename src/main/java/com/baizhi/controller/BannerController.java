package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("queryBannerByPager")
    public Map<String, Object> queryBannerByPager(Integer page, Integer rows) {
        Map<String, Object> map = bannerService.queryByPager(page, rows);
        return map;
    }

    @RequestMapping("bannerEdit")
    public Map<String, Object> bannerEdit(Banner banner, String oper, String[] id) {
        Map<String, Object> map = new HashMap<>();
        if ("add".equals(oper)) {
            //添加数据
            Map<String, Object> map1 = bannerService.insertBanner(banner);
            if (map1.get("i").equals(1)) {
                map.put("msg", "添加成功");
                map.put("bannerId", map1.get("bannerId"));
            } else {
                map.put("msg", "添加失败");
            }
        } else if ("edit".equals(oper)) {
            //修改
            if (banner.getImg() == "") {
                //用户没修改图片
                banner.setImg(null);
                bannerService.updateByPrimaryKeySelective(banner);
                map.put("msg", "变更成功");
                map.put("bannerId", null);
            } else {
                bannerService.updateByPrimaryKeySelective(banner);
                map.put("msg", "变更成功");
                map.put("bannerId", banner.getId());
            }
        } else if ("del".equals(oper)) {
            int i = bannerService.deleteByPrimaryKeys(id);
            if (0 != i) {
                map.put("msg", "删除成功");
            } else {
                map.put("msg", "删除失败");
            }
        }
        return map;
    }

    @RequestMapping("bannerUpload")
    public void bannerUpload(MultipartFile img, String bannerId, HttpSession session) {
        bannerService.bannerUpload(img, bannerId, session);
    }
}
