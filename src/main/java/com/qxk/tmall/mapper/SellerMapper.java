package com.qxk.tmall.mapper;

import com.qxk.tmall.pojo.Seller;
import com.qxk.tmall.pojo.SellerExample;

import java.util.List;

/**
 * @Author: laijianzhen
 * @Date: 2019/5/4 15:16
 */
public interface SellerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Seller record);

    int insertSelective(Seller record);

    List<Seller> selectByExample(SellerExample example);

    Seller selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);
}
