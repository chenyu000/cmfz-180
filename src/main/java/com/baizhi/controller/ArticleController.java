package com.baizhi.controller;

import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("queryArticleByPager")
    public Map<String, Object> queryArticleByPager(Integer page, Integer rows) {
        //1. 接收参数
        //2. 调用业务
        //3. 页面跳转(数据的响应)
        Map<String, Object> map = articleService.queryByPager(page, rows);

        return map;
    }

    @RequestMapping("uploadImg")
    public Map<String, Object> uploadImg(MultipartFile img, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/upload/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = img.getOriginalFilename();
        String name = new Date().getTime() + "_" + originalFilename;
        try {
            img.transferTo(new File(realPath, name));
            map.put("error", 0);
            //  http://localhost:80/cmfz/upload/img/xxx.png
            String scheme = request.getScheme();
            InetAddress localHost = InetAddress.getLocalHost();
            String lh = localHost.toString().split("/")[1];
            int port = request.getServerPort();
            String contextPath = request.getContextPath(); //  /cmfz
            String url = scheme + "://" + lh + ":" + port + contextPath + "/upload/img/" + name;
            map.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /*
    * {
    "moveup_dir_path": "",
    "current_dir_path": "",
    "current_url": "/ke4/php/../attached/",
    "total_count": 5,
    "file_list": [
        {
            "is_dir": false,
            "has_file": false,
            "filesize": 208736,
            "dir_path": "",
            "is_photo": true,
            "filetype": "jpg",
            "filename": "1241601537255682809.jpg",
            "datetime": "2018-06-06 00:36:39"
        }
     ]
    * */
    @RequestMapping("getAllImgs")
    public Map<String, Object> getAllImgs(HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String realPath = session.getServletContext().getRealPath("/upload/img");
        File file = new File(realPath);
        ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
        String[] strs = file.list();   //文件夹下所有资源的名字
        for (String s : strs) {
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("is_dir", false);
            hashMap.put("has_file", false);
            hashMap.put("filesize", new File(realPath, s).length());
            hashMap.put("dir_path", "");
            hashMap.put("is_photo", true);
            hashMap.put("filetype", FilenameUtils.getExtension(s));
            hashMap.put("filename", s);
            String s1 = s.split("_")[0];
            Long aLong = Long.valueOf(s1);
            Date date = new Date(aLong);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(date);
            hashMap.put("datetime", format);
            arrayList.add(hashMap);
        }
        map.put("file_list", arrayList);
        map.put("moveup_dir_path", "");
        map.put("current_dir_path", "");
        String scheme = request.getScheme();
        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();
            String lh = localHost.toString().split("/")[1];
            int port = request.getServerPort();
            String contextPath = request.getContextPath(); //  /cmfz
            String url = scheme + "://" + lh + ":" + port + contextPath + "/upload/img/";
            map.put("current_url", url);
            map.put("total_count", strs.length);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return map;
    }
}
