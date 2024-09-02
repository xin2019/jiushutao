package com.xin.jiushutao.service.impl;

import com.netflix.discovery.converters.Auto;
import com.xin.jiushutao.dao.PersonalDao;
import com.xin.jiushutao.pojo.T_ADDRESS;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.proxy.buyer.OrderAndBuyer;
import com.xin.jiushutao.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/21 16:43
 * @Version 1.0
 **/
@Service
public class PersonalServiceImpl implements PersonalService {


    @Autowired
    private PersonalDao personalDao;

    @Override
    public int deleteAddressByAddressID(String addressid) {

        return personalDao.deleteAddressByAddressID(addressid);
    }

    @Override
    public List<T_ADDRESS> getaddress(String buyerid) {
        return personalDao.getaddress(buyerid);
    }

    @Override
    public int addnewaddress(T_ADDRESS t_address) {
        return personalDao.addnewaddress(t_address);
    }

    @Override
    public OrderAndBuyer getOrderAndBuyerByorderId(String id) {
        return personalDao.getOrderAndBuyerByorderId(id);
    }

    @Override
    public T_BUYER getBuyerByBuyerId(String buyid) {
        return personalDao.getBuyerByBuyerId(buyid);
    }

    @Override
    public boolean updateT_Buyer(T_BUYER t_buyer) {
        return personalDao.updateT_Buyer(t_buyer);
    }

    /*

 更新buyer
  */
    public boolean save(T_BUYER t_buyer){
        return personalDao.save(t_buyer);
    }
}
