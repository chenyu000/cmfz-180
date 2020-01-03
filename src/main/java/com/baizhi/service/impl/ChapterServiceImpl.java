package com.baizhi.service.impl;

import com.baizhi.dao.ChapterMapper;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;


    @Override
    public Map<String, Object> queryByPager(Integer page, Integer rows, String albumId) {
        Integer start = (page - 1) * rows;
        List<Chapter> list = chapterMapper.selectByPager(start, rows, albumId);
        Integer records = chapterMapper.queryCount(albumId);
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", list);
        map.put("records", records);
        map.put("total", total);
        return map;
    }

    @Override
    public String addChapter(Chapter chapter) {
        String id = UUID.randomUUID().toString().replace("-", "");
        chapter.setId(id);
        chapterMapper.insertSelective(chapter);
        return id;
    }

    @Override
    public void uploadChapter(MultipartFile src, String chapterId, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/upload/audio");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String realName = src.getOriginalFilename();
        String name = new Date().getTime() + "_" + realName;
        try {
            src.transferTo(new File(realPath, name));
            //获得时长
            AudioFile read = AudioFileIO.read(new File(realPath, name));
            AudioHeader audioHeader = read.getAudioHeader();
            int trackLength = audioHeader.getTrackLength();
            String second = trackLength % 60 + "秒";
            String minute = trackLength / 60 + "分";
            //大小
            String size = src.getSize() / 1024 / 1024 + "MB";
            Chapter chapter = new Chapter();
            chapter.setId(chapterId);
            chapter.setDuration(minute + second);
            chapter.setSize(size);
            chapter.setSrc(name);
            chapterMapper.updateByPrimaryKeySelective(chapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateByPrimaryKeySelective(Chapter record) {
        int i = chapterMapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    public int deleteByPrimaryKeys(String[] id) {
        int i = chapterMapper.deleteByPrimaryKeys(id);
        return i;
    }

    @Override
    public void downLoad(String src, HttpSession session, HttpServletResponse response) {
        String realPath = session.getServletContext().getRealPath("/upload/audio");
        File file = new File(realPath, src);
        /*
         *   src ---> 1577259937256_4.mp3    4.mp3
         * */
        String name = src.split("_")[1];

        //附件形式下载  设置响应头
        try {
            String encode = URLEncoder.encode(name, "utf-8");
            response.setHeader("content-disposition", "attachment;filename=" + encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            FileUtils.copyFile(file, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
