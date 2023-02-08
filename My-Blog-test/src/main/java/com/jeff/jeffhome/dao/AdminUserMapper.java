package com.jeff.jeffhome.dao;

import com.jeff.jeffhome.entity.AdminUser;
import com.jeff.jeffhome.util.PageQueryUtil;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;

public interface AdminUserMapper {
    List<AdminUser> findAdminUsers(PageQueryUtil pageUtil);
    int getTotalAdminUser(PageQueryUtil pageUtil);
    AdminUser login(String userName, String password);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}
