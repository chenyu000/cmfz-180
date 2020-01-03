package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ChapterService {
    Map<String, Object> queryByPager(Integer page, Integer rows, String albumId);

    String addChapter(Chapter chapter);

    void uploadChapter(MultipartFile src, String chapterId, HttpSession session);

    int updateByPrimaryKeySelective(Chapter record);

    int deleteByPrimaryKeys(String[] id);

    public void downLoad(String src, HttpSession session, HttpServletResponse response);
}
