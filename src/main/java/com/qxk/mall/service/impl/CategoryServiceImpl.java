

package com.qxk.mall.service.impl;

import com.qxk.mall.mapper.CategoryMapper;
import com.qxk.mall.pojo.Category;
import com.qxk.mall.pojo.CategoryExample;
import com.qxk.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        CategoryExample example =new CategoryExample();
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);
    }

//    @Override
//    public List<Category> listBySid(Integer sid) {
//        CategoryExample example =new CategoryExample();
//        example.setOrderByClause("id desc");
//        example.createCriteria().andSIdEqualTo(sid);
//        return categoryMapper.selectByExample(example);
//    }

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category get(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}

