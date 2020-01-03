package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("queryChapterByPager")
    public Map<String, Object> queryChapterByPager(Integer page, Integer rows, String albumId) {
        Map<String, Object> map = chapterService.queryByPager(page, rows, albumId);
        return map;
    }

    @RequestMapping("edit")
    public Map<String, Object> edit(Chapter chapter, String oper, String[] id) {
        Map<String, Object> map = new HashMap<>();
        if ("add".equals(oper)) {
            String ids = chapterService.addChapter(chapter);
            map.put("msg", "添加成功");
            map.put("chapterId", ids);
        } else if ("edit".equals(oper)) {
            if ("" == chapter.getSrc()) {
                //用户没修改音频
                chapter.setSrc(null);
                int i = chapterService.updateByPrimaryKeySelective(chapter);
                if (1 == i) {
                    map.put("msg", "修改成功");
                    map.put("chapterId", null);
                } else {
                    map.put("msg", "修改失败");
                }
            } else {
                int i = chapterService.updateByPrimaryKeySelective(chapter);
                map.put("msg", "修改成功");
                map.put("chapterId", chapter.getId());
            }
        } else if ("del".equals(oper)) {
            int i = chapterService.deleteByPrimaryKeys(id);
            if (i != 0) {
                map.put("msg", "删除成功");
            } else {
                map.put("msg", "删除失败");
            }
        }
        return map;
    }

    @RequestMapping("uploadChapter")
    public void edit(MultipartFile src, String chapterId, HttpSession session) {
        chapterService.uploadChapter(src, chapterId, session);
    }

    @RequestMapping("downLoad")
    public void downLoad(String src, HttpSession session, HttpServletResponse response) {
        chapterService.downLoad(src, session, response);
    }
}
