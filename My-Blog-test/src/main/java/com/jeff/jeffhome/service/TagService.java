package com.jeff.jeffhome.service;

import com.jeff.jeffhome.entity.BlogTagCount;
import com.jeff.jeffhome.util.PageQueryUtil;
import com.jeff.jeffhome.util.PageResult;

import java.util.List;

public interface TagService {

    /**
     * 查询标签的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    int getTotalTags();

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();
}
