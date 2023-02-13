package com.jeff.jeffhome.dao;



import com.jeff.jeffhome.entity.BlogConfig;

import java.util.List;

public interface BlogConfigMapper {
    List<BlogConfig> selectAll();

    BlogConfig selectByPrimaryKey(String configName);

    int updateByPrimaryKeySelective(BlogConfig record);

}