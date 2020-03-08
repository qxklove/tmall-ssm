/**



 */

package com.qxk.mall.service.impl;

import java.util.List;

import com.qxk.mall.mapper.SellerMapper;
import com.qxk.mall.pojo.Seller;
import com.qxk.mall.pojo.SellerExample;
import com.qxk.mall.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerMapper sellerMapper;

    @Override
    public void add(Seller s) {
        sellerMapper.insert(s);
    }

    @Override
    public void delete(int id) {
        sellerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Seller s) {
        sellerMapper.updateByPrimaryKeySelective(s);
    }

    @Override
    public Seller get(int id) {
        return sellerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Seller> list(){
        SellerExample example = new SellerExample();
        example.setOrderByClause("id desc");
        return sellerMapper.selectByExample(example);

    }

    @Override
    public boolean isExist(String name) {
        SellerExample example = new SellerExample();
        example.createCriteria().andNameEqualTo(name);
        List<Seller> result= sellerMapper.selectByExample(example);
        if(!result.isEmpty()){
            return true;
        }
        return false;

    }

    @Override
    public Seller get(String name, String password) {
        SellerExample example = new SellerExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<Seller> result= sellerMapper.selectByExample(example);
        if(result.isEmpty()){
            return null;
        }
        return result.get(0);
    }


}

/**



 */
