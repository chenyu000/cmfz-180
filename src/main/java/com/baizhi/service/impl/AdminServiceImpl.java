package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryByUsername(String username, String password) {
        Admin admin = adminDao.queryByUsername(username);
        Map<String, Object> map = new HashMap<>();
        if (admin == null) {
            map.put("msg", "用户名有误");
        } else {
            if (admin.getPassword().equals(password)) {
                map.put("msg", null);
            } else {
                map.put("msg", "密码有误");
            }
        }
        return map;
    }
}
