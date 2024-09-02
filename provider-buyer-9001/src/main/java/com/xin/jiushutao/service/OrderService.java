package com.xin.jiushutao.service;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.pojo.T_ORDER;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/15 16:40
 * @Version 1.0
 **/
public interface OrderService {
    public List<T_ORDER> allOrder(String id);
    public String queryShopNameByShopId(String shopId); //通过店铺id查询商铺名称
    public List<String> getAllShopIdByBuyerId(String id);//通过buyerid查询所有

    //通过bookid修改状态
    public int updateStatusByBookid(String bookid,int status);

    //通过orderid获得bookid
    public String getBookidByOrderid(String orderid);
    /*
    通过订单id改变收货状态
     */
    public int updateStatusByOrderid(String orderid, int status);

    /*
    通过bookid返回book
     */
     public T_BOOK findByBookId(String bookid);

     public boolean deleteOrderByOrderId(String id);

     /*
     通过orderid返回tbuyer对象
      */
     public T_BUYER getBuyerByOrderId(String t_order_id);

    /*
 通过buyerid和bookid往order表插入记录
 删除对应bookid在book表中
  */
    public boolean insertNewOrder(T_ORDER t_order);

    public boolean deleteBookByBookId(String bookid);

    /*
   通过shopid查询sellerid
    */
    public String getSelleridByShopId(String shopid);
}
