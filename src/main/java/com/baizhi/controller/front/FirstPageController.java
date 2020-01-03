package com.baizhi.controller.front;

import com.baizhi.commons.FirstPageDto;
import com.baizhi.commons.FirstPageSubDto;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://192.168.0.3:8080/cmfz/first_page
 */
@RestController
public class FirstPageController {


    @Autowired
    private BannerService bannerService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArticleService articleService;

    /**
     * @param uid
     * @param type     all     查所有, 轮播图  专辑   文章     header才有值
     *                 wen     专辑
     *                 si      文章
     * @param sub_type
     * @return
     */
    @RequestMapping(value = "/first_page", method = RequestMethod.GET)
    public FirstPageDto first_page(String uid, String type, String sub_type) {
        //405
        FirstPageDto result = new FirstPageDto();

        if ("all".equals(type)) {
            //查询首页
            //查轮播图
            result = this.getBanner(result);
            //查专辑
            result = this.getAlbum(result);
            //查文章
            result = this.getArticle(result);
        } else if ("web".equals(type)) {
            //专辑
            result = this.getAlbum(result);

        } else if ("si".equals(type)) {
            if ("ssyj".equals(sub_type)) {
                //上师
            } else if ("xmfy".equals(sub_type)) {
                result = this.getArticle(result);
            }
        }

        return result;
    }


    private FirstPageDto getBanner(FirstPageDto result) {

        Map<String, Object> map = bannerService.queryByPager(1, 5);
        List<Banner> rows = (List<Banner>) map.get("rows");
        List<Map<String, String>> header = result.getHeader();
        for (Banner row : rows) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("thumbnail", row.getImg());
            hashMap.put("desc", row.getTitle());
            hashMap.put("id", row.getId());
            header.add(hashMap);
        }
        return result;
    }


    private FirstPageDto getAlbum(FirstPageDto result) {

        //查专辑
        List<FirstPageSubDto> body = result.getBody();
        Map<String, Object> albumMap = albumService.queryByPager(1, 6);
        List<Album> rows1 = (List<Album>) albumMap.get("rows");
        for (Album album : rows1) {
            FirstPageSubDto subDto = new FirstPageSubDto();
            subDto.setThumbnail(album.getImg());
            subDto.setAuthor(album.getAuthor());
            subDto.setTitle(album.getTitle());
            subDto.setType("0");
            subDto.setSet_count(album.getCount());
            subDto.setCreate_date(album.getCreateDate());
            body.add(subDto);
        }
        return result;
    }


    private FirstPageDto getArticle(FirstPageDto result) {

        //查文章
        List<FirstPageSubDto> body = result.getBody();
        Map<String, Object> articleMap = articleService.queryByPager(1, 2);
        List<Article> rows2 = (List<Article>) articleMap.get("rows");
        for (Article article : rows2) {
            FirstPageSubDto subDto = new FirstPageSubDto();
            subDto.setThumbnail("");
            subDto.setAuthor(article.getAuthor());
            subDto.setTitle(article.getTitle());
            subDto.setType("1");
            subDto.setSet_count("");
            subDto.setCreate_date(article.getCreateDate());
            body.add(subDto);
        }
        return result;
    }

}
