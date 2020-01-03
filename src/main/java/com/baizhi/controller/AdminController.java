package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    public Map<String, Object> login(String username, String password, String code, HttpSession session) {
        String imageCode = (String) session.getAttribute("imageCode");
        Map<String, Object> map = new HashMap<>();
        if (imageCode.equals(code)) {
            map = adminService.queryByUsername(username, password);
        } else {
            map.put("msg", "验证码有误");
        }
        return map;
    }

    @RequestMapping("aa")
    public Admin aa() {
        return new Admin("1", "2", "3");
    }
}
