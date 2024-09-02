package com.xin.jiushutao.dao;

import com.xin.jiushutao.pojo.T_ADDRESS;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.proxy.buyer.OrderAndBuyer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/21 16:40
 * @Version 1.0
 **/
@Mapper
@Repository
public interface PersonalDao {

    /*
    通过addressid删除对应的address
     */
    public int deleteAddressByAddressID(String addressid);


    /*
    渲染收货地址，获得该买家所有地址
    */
    public List<T_ADDRESS> getaddress(String buyerid);

    /*
   新增地址
    */
    public int addnewaddress(@RequestBody T_ADDRESS t_address);

    /*
    获得order+buyer
     */
    public OrderAndBuyer getOrderAndBuyerByorderId(String t_order_id);

    /*
    通过buyerid获取buyer对象
     */
    public T_BUYER getBuyerByBuyerId(String buyid);

    /*
    通过新的tbuyer对象更新数据库对象t_buyer
     */

    public boolean updateT_Buyer(T_BUYER t_buyer);

    /*

  更新buyer
   */
    public boolean save(T_BUYER t_buyer);


}
