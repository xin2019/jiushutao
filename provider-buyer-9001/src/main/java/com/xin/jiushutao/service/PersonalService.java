package com.xin.jiushutao.service;

import com.xin.jiushutao.pojo.T_ADDRESS;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.proxy.buyer.OrderAndBuyer;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/21 16:42
 * @Version 1.0
 **/
public interface PersonalService {

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

    public OrderAndBuyer getOrderAndBuyerByorderId(String id);

    /*
        通过buyerid获取buyer对象
         */
    public T_BUYER getBuyerByBuyerId(String buyid);


    /*
    通过buyer_phone更新数据库对象t_buyer
     */

    public boolean updateT_Buyer(T_BUYER t_buyer);

    /*

 更新buyer
  */
    public boolean save(T_BUYER t_buyer);

}
