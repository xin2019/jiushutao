package com.xin.jiushutao.controller;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_ORDER;
import com.xin.jiushutao.pojo.T_SELLER;
import com.xin.jiushutao.pojo.T_SHOP;
import com.xin.jiushutao.service.SellerLoginService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/4 19:08
 * @Version 1.0
 **/
@RestController
@RequestMapping("/seller")
public class SellerLoginController {

    @Autowired
    private SellerLoginService loginService;
    /*
   添加新的卖家
    */
    @RequestMapping("/addNewSeller")
    public boolean addNewSeller(@RequestBody T_SELLER t_seller){
        System.out.println(t_seller);
        boolean b = loginService.addNewSeller(t_seller);
        return b;
    }

    /*
   卖家登陆
    */
    @RequestMapping("/isRightSeller")
    public boolean isRightSeller(@Param("phone") String phone, @Param("password") String password){
        return loginService.isRightSeller(phone,password);
    }

    /*
    根据卖家手机号返回卖家
     */
    @ResponseBody
    @RequestMapping("/getSeller")
    public T_SELLER getSeller(@Param("phone")String phone){
        return loginService.getSeller(phone);
    }


    /*
    根据shopid获得shop实体
     */
    @RequestMapping("/getShop")
    @ResponseBody
    public T_SHOP getShop(@Param("shopid")String shopid){
        return loginService.getShop(shopid);
    }


    /*
    根据shopid获得所有书籍
     */
    @RequestMapping("/getBooks")
    @ResponseBody
    public List<T_BOOK> getAllBooksByShopId(@Param("shopid") String shopid){
        return loginService.getAllBooksByShopId(shopid);
    }

    /*
    通过bookid在order表中寻找，返回order对象
     */
    @RequestMapping("/getOrderByBookid")
    @ResponseBody
    public T_ORDER getOrderByBookid(@Param("bookid") String bookid){
        return loginService.getOrderByBookid(bookid);
    }

    /*
    通过bookid和status修改bookid的stauts，确认收货
     */
    @RequestMapping("/updateStatusByBookidAndStatus")
    @ResponseBody
    public int updateStatusByBookidAndStatus(@Param("bookid")String bookid,@Param("status")int status){
        return loginService.updateStatusByBookidAndStatus(bookid,status);
    }


    /*
      查看已被购买书籍
       */
    @RequestMapping("/getAllBooksPurchase")
    public List<T_BOOK> getAllBooksPurchase(@Param("sellerid")String sellerid,@Param("status")int status){
        return loginService.getAllBooksPurchase(sellerid,status);
    }
    /*
    通过shopid修改商店名称
     */
    @RequestMapping("/updateShopnameByShopid")
    @ResponseBody

    public boolean updateShopnameByShopid(@Param("shopid") String shopid,@Param("shopname")String shopname){
        System.out.println(shopid);
        System.out.println(shopname);
        return loginService.updateShopnameByShopid(shopid,shopname);
    }


    /*
    通过bookid下架book
     */
    @RequestMapping("/rackBookByBookid")
    public boolean rackBookByBookid(@Param("bookid") String bookid){
     return loginService.rackBookByBookid(bookid);
    }

    /*
    新增书籍,在book表和shop表
     */
    @RequestMapping("/addNewBook")
    @ResponseBody
    public boolean addNewBook(@RequestBody T_BOOK t_book){
        System.out.println(t_book);
        return loginService.addNewBook(t_book);
    }


    /*
    新增默认商店，用于注册新的卖家时候使用
     */
    @RequestMapping("/addNewShop")
    @ResponseBody
    public boolean addNewShop(@RequestBody T_SHOP t_shop){
        System.out.println("9002-------------addnewshop");
        System.out.println(t_shop);
        return loginService.addNewShop(t_shop);
    }

}
