

package com.qxk.tmall.service.impl;

import com.qxk.tmall.mapper.OrderMapper;
import com.qxk.tmall.pojo.Order;
import com.qxk.tmall.pojo.OrderExample;
import com.qxk.tmall.pojo.OrderItem;
import com.qxk.tmall.pojo.Seller;
import com.qxk.tmall.pojo.User;
import com.qxk.tmall.service.OrderItemService;
import com.qxk.tmall.service.OrderService;
import com.qxk.tmall.service.SellerService;
import com.qxk.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserService userService;
    @Autowired
    SellerService sellerService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order c) {
        orderMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public float add(Order o, List<OrderItem> ois) {
        float total = 0;
        add(o);

        if(false) {
            throw new RuntimeException();
        }

        for (OrderItem oi: ois) {
            oi.setOid(o.getId());
            orderItemService.update(oi);
            total+=oi.getProduct().getPromotePrice()*oi.getNumber();
        }
        return total;
    }

    @Override
    public List<Order> list(){
        OrderExample example =new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> orders = orderMapper.selectByExample(example);
        setUser(orders);
        return orders;
    }

    @Override
    public List list(int uid, String excludedStatus) {
        OrderExample example =new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        List<Order> orders = orderMapper.selectByExample(example);
        setSellers(orders);
        return orders;
    }

    @Override
    public List<Order> listBySid(Integer sid) {
        OrderExample example =new OrderExample();
        example.createCriteria().andSIdEqualTo(sid);
        example.setOrderByClause("id desc");
        List<Order> orders = orderMapper.selectByExample(example);
        setUser(orders);
        return orders;
    }

    public void setUser(List<Order> os){
        for (Order o : os) {
            setUser(o);
        }
    }
    public void setUser(Order o){
        int uid = o.getUid();
        User u = userService.get(uid);
        o.setUser(u);
    }

    public void setSellers(List<Order> os){
        for (Order o : os) {
            setSeller(o);
        }
    }
    public void setSeller(Order o){
        int sid = o.getSid();
        Seller seller = sellerService.get(sid);
        o.setSeller(seller);
    }

}

