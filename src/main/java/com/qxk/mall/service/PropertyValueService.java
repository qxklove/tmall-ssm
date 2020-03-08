

package com.qxk.mall.service;

import com.qxk.mall.pojo.Product;
import com.qxk.mall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    void init(Product p);
    void update(PropertyValue pv);

    PropertyValue get(int ptid, int pid);
    List<PropertyValue> list(int pid);
}


