package com.qxk.tmall.service;

import com.qxk.tmall.pojo.Seller;

import java.util.List;

/**
 * @Author: laijianzhen
 * @Date: 2019/5/4 15:18
 */
public interface SellerService {
    void add(Seller c);
    void delete(int id);
    void update(Seller c);
    Seller get(int id);
    List list();
    boolean isExist(String name);

    Seller get(String name, String password);
}
