package com.xin.jiushutao.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.pojo.T_ORDER;
import com.xin.jiushutao.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/15 16:42
 * @Version 1.0
 **/
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @HystrixCommand(fallbackMethod ="NotExistAnyOrders" )
    @GetMapping("/buyer/allOrder")
    public List<T_ORDER> allOrder(@Param("id") String id) {
        List<T_ORDER> t_orders;
        try {
             t_orders = orderService.allOrder(id);
        }catch (Exception e){
            //熔断一般是超时调用或者抛出异常时候激发
            throw new RuntimeException("9001--ordercontroller--/buyer/allOrder");
        }
        return t_orders;
    }

    @GetMapping("/buyer/queryShopNameByShopId")
    public String queryShopNameByShopId(@Param("id") String id){
        String s = orderService.queryShopNameByShopId(id);
        return s;
    }

    @GetMapping("/buyer/getAllShopIdByBuyerId")
    public List<String> getAllShopIdByBuyerId(@Param("buyerid") String buyerid){
        return orderService.getAllShopIdByBuyerId(buyerid);
    }

    //服务熔断

    /*
    当没有该用户的订单时候调用
    备选调用
     */
    public List<T_ORDER> NotExistAnyOrders(@Param("id") String id){

       return null;
    }


    @GetMapping("/findByBookId")
    public T_BOOK findByBookId(@Param("id") String id){
        return orderService.findByBookId(id);
    }

    @RequestMapping("/buyer/order/deleteOrderByOrderId")
    @ResponseBody
    public boolean deleteOrderByOrderId(@Param("id")String id){
        return orderService.deleteOrderByOrderId(id);
    }


    /*
    通过orderid查询该订单的买家信息
     */
    @RequestMapping("/getTBuyerByOrderId")
    @ResponseBody
    public T_BUYER get(@Param("id") String id){
        return orderService.getBuyerByOrderId(id);
    }


    /*
    添加新的order记录
     */
    @RequestMapping("/insertNewOrder")
    public boolean insertNewOrder(@RequestBody T_ORDER t_order){
        return orderService.insertNewOrder(t_order);
    }

    /*
    根据bookid删除boo表的记录
     */
    @RequestMapping("/deleteBookByBookId")
    public boolean deleteBookByBookId(@Param("id")String id){

        return orderService.deleteBookByBookId(id);
    }

    /*
    通过订单id改变收货状态
     */
    @RequestMapping("/updateStatusByBookid")
    public int updateStatusByBookid(@Param("bookid")String bookid,@Param("status")int status){
       return orderService.updateStatusByBookid(bookid,status);
    }


    /*
    通过订单id改变收货状态
     */
    @RequestMapping("/updateStatusByOrderid")
    public int updateStatusByOrderid(@Param("orderid")String orderid,@Param("status")int status){
        return orderService.updateStatusByOrderid(orderid,status);
    }

    //通过orderid获得bookid
    @RequestMapping("/getBookidByOrderid")
    public String getBookidByOrderid(@Param("orderid") String orderid){
        return orderService.getBookidByOrderid(orderid);
    }

    /*
    通过shopid获得sellerid
     */
    @RequestMapping("/getSelleridByShopId")
    public String getSelleridByShopId(@Param("shopid")String shopid){
        return orderService.getSelleridByShopId(shopid);
    }

}
