package com.xin.jiushutao.controller;

import com.xin.jiushutao.pojo.T_ADMIN;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_SELLER;
import com.xin.jiushutao.pojo.T_SHOP;
import com.xin.jiushutao.proxy.admin.ShopAndSeller;
import com.xin.jiushutao.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/6 20:27
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /*
    通过shopid和priority修改shop优先级
     */
    @RequestMapping("/updateShopPriorityByShopid")
    public int updateShopPriorityByShopid(@Param("shopid")String shopid, @Param("priority")String priority){
        return adminService.updateShopPriorityByShopid(shopid,priority);
    }


    /*
   返回所有在主栏位的书籍信息
    */
    @RequestMapping("/allMainBooks")
    public List<T_BOOK> allMainBoooks(){
        return adminService.allMainBoooks();
    }

    /*
    返回所有在好书推荐栏位的信息
     */
    @RequestMapping("/allRecomendBooks")
    public List<T_BOOK> allRecomendBooks(){
        return adminService.allRecomendBooks();
    }


    /*
    返回所有在金牌书屋栏位的商店信息
     */
    @RequestMapping("/allGoldShops")
    public List<T_SHOP> allGoldShops(){
        return adminService.allGoldShops();
    }

    /*
   提升商铺-》在金牌书屋栏位展示
    */
    @RequestMapping("/liftShop")
    public int liftShop(@Param("shopid") String shopid){
        return adminService.liftShop(shopid);
    }


    /*
    提升商铺---》通过商铺id查询商铺
     */
    @RequestMapping("/getshopByShopId")

    public T_SHOP getshop(@Param("shopid")String shopid){
        return adminService.getshopByShopid(shopid);
    }

     /*
    提升商铺---》通过商铺id查询商铺持有人信息信息
     */
     @RequestMapping("/getSellerByShopId")

     public T_SELLER getseller(@Param("shopid")String shopid){
      return adminService.getseller(shopid);
     }


    /*
    提升书籍优先级
     */
    @RequestMapping("/updatePriorityBook")
    public int updatePriorityBook(@Param("priority")String priority,@Param("bookid")String bookid){
        return adminService.updatePriorityBook(priority,bookid);
    }


    /*
    通过adminphone返回admin对象
     */
    @RequestMapping("/getAdminByPhone")
    public T_ADMIN getadmin(@Param("phone")String phone){
        return adminService.getadmin(phone);
    }


    /*
   接收来自8002卖家发送的需要提升栏位的书籍id booid
    */
    @RequestMapping("/receiveBookid")
    public int receiveBookid(@Param("bookid") String bookid){
       return adminService.receiveBookid(bookid);
    }

    /*
    接收来自8002卖家发送的需要提升栏位的商铺id shopid
     */
    @RequestMapping("/receiveShopid")
    public int receiveShopid(@Param("shopid") String shopid){
        return adminService.receiveShopid(shopid);
    }
}
