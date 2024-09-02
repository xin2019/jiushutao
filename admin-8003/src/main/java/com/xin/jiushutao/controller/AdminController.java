package com.xin.jiushutao.controller;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_SELLER;
import com.xin.jiushutao.pojo.T_SHOP;
import com.xin.jiushutao.proxy.admin.ShopAndSeller;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/7 10:43
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RestTemplate restTemplate;
    private String PREFIX9001="http://localhost:9001";
    private String PREFIX9003="http://localhost:9003";

    /*
    调整店铺优先级，降级
     */
    @RequestMapping("/updateShopPriorityByShopid")
    @ResponseBody
    public int down(@Param("shopid")String shopid,@Param("priority")String priority){
        return restTemplate.getForObject(PREFIX9003+"admin/updateShopPriorityByShopid?shopid="+shopid+"&priority="+priority,int.class);
    }

    /*
    返回所有在主栏位的书籍信息
     */
    @RequestMapping("/allMainBooks")
    @ResponseBody
    public List<T_BOOK> allMainBoooks(){
        return restTemplate.getForObject(PREFIX9003+"/admin/allMainBooks",List.class);
    }

    /*
    返回所有在好书推荐栏位的信息
     */
    @RequestMapping("/allRecomendBooks")
    @ResponseBody
    public List<T_BOOK> allRecomendBooks(){
        return restTemplate.getForObject(PREFIX9003+"/admin/allRecomendBooks",List.class);
    }


    /*
    返回所有在金牌书屋栏位的信息
     */
    @RequestMapping("/allGoldShops")
    @ResponseBody
    public List<T_SHOP> allGoldShops(){
        return restTemplate.getForObject(PREFIX9003+"admin/allGoldShops",List.class);
    }



    /*
    通过shopid查询
     */
    @RequestMapping("/liftShop")
    @ResponseBody
    public int lift(@Param("shopid")String shopid){
      return   restTemplate.getForObject(PREFIX9003+"/admin/liftShop?shopid="+shopid,int.class);
    }

    /*
    通过shopid查询shop和seller信息
     */
    @RequestMapping("/getShopAndSellerByShopid")
    @ResponseBody
    public ShopAndSeller getshopandseler(@Param("shopid")String shopid){
        T_SHOP shop = restTemplate.getForObject(PREFIX9003 + "admin/getshopByShopId?shopid=" + shopid, T_SHOP.class);
        T_SELLER seller=restTemplate.getForObject(PREFIX9003+"admin/getSellerByShopId?shopid="+shopid,T_SELLER.class);
        ShopAndSeller shopAndSeller=new ShopAndSeller();
        shopAndSeller.setT_seller(seller);
        shopAndSeller.setT_shop(shop);
        return shopAndSeller;
    }


    @RequestMapping("/getBookByBookid")
    @ResponseBody
    public T_BOOK getbook(@Param("bookid")String bookid, Model model){

        T_BOOK book = restTemplate.getForObject(PREFIX9001 + "/findByBookId?id=" + bookid, T_BOOK.class);
        model.addAttribute("book",book);
        return book;

    }

    /*
    通过参数priority和bookid更改优先级
     */
    @RequestMapping("/updatePriorityBook")
    @ResponseBody
    public int updatePriorityBook(@Param("priority")String priority,@Param("bookid")String bookid){
        Integer res = restTemplate.getForObject(PREFIX9003 + "admin/updatePriorityBook?priority=" + priority + "&bookid=" + bookid, int.class);
        return res;
    }
}
