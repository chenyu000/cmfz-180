package com.baizhi.service;

import java.util.Map;

public interface AdminService {
    Map<String, Object> queryByUsername(String username, String password);
}
