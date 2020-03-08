

package com.qxk.tmall.service;

import com.qxk.tmall.pojo.Category;
import java.util.List;

public interface CategoryService{

    List<Category> list();

//    List<Category> listBySid(Integer sid);

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}

