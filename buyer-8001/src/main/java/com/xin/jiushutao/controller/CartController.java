package com.xin.jiushutao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.pojo.T_CART;
import com.xin.jiushutao.pojo.T_ORDER;
import com.xin.jiushutao.proxy.buyer.CartBookAndQuantitys;
import feign.Param;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/26 16:35
 * @Version 1.0
 **/
@Controller
public class CartController {

    @Autowired
    private RestTemplate restTemplate;
    private String PREFIX="http://localhost:9001";
//private String PREFIX="http://60.204.157.187:9001";
    /*
    渲染购物车
     */
    @RequestMapping("/cart")
    public String retrunCart(Model model, @Param("phone")String phone){
        String t_buyer_id = restTemplate.getForObject(PREFIX + "/buyer/queryIdByPhone?phone=" + phone, String.class);

        List bookidStrings = restTemplate.getForObject(PREFIX + "/buyer/order/getAllCartLists?buyerid=" + t_buyer_id, List.class);



        int index=0;
        boolean flag=true;
        String[] temp=new String[bookidStrings.size()];
        for (Object bookidString : bookidStrings) {
            flag=true;
            String s = JSONObject.parseObject(JSON.toJSONString(bookidString), String.class);
            for(int k=0;k<index;k++){
                if(s.equals(temp[k])){
                    flag=false;
                    break;
                }
            }
            if(flag==true){
                temp[index++]=s;
            }
        }

//        System.out.println("---------==============输出temp【】内容=============");
//        for (String s : temp) {
//            System.out.println(s);
//        }
        List<CartBookAndQuantitys> books=new LinkedList<>();
        for(int loop=0;loop<index;loop++){
            T_BOOK t_book =restTemplate.getForObject(PREFIX+"/buyer/product/getBookByBookId?id="+temp[loop],T_BOOK.class);
            if(t_book==null){
                continue;
            }
//            restTemplate.getForObject(PREFIX+"/submitToPurchase?bookid="+t_book.getT_book_id(),int.class);
            CartBookAndQuantitys andQuantitys=new CartBookAndQuantitys();
            andQuantitys.setT_book(t_book);

            //旧版
//            T_BOOK bookPurchasing = restTemplate.getForObject(PREFIX + "/buyer/order/getBookidSpecifedStatus?bookid=" + t_book.getT_book_id() + "&status=5", T_BOOK.class);
//           if(bookPurchasing==null){
//               System.out.println("berak===bookpurchasing====>"+temp[loop]);
//               continue;
//           }
//            andQuantitys.setT_book(bookPurchasing);
            andQuantitys.setQuantity(1);

            double price=t_book.getT_book_now_price();
            andQuantitys.setTotalPrice(price);
            books.add(andQuantitys);
        }


        model.addAttribute("books",books);
        return "cart";
    }


    /*
    添加购物车记录，同时删除book表中对应的bookid记录
     */

    @RequestMapping("/addToCart")
    @ResponseBody
    public Boolean addToCart(@Param("bookid")String bookid,@Param("phone")String phone,@Param("quantity")String quantity){
        String t_buyer_id = restTemplate.getForObject(PREFIX + "/buyer/queryIdByPhone?phone=" + phone, String.class);
//        int num=Integer.parseInt(quantity);
        boolean addNew= restTemplate.getForObject(PREFIX + "/buyer/cart/addToCart?bookid=" + bookid + "&buyerid=" + t_buyer_id + "&quantity=1", Boolean.class);
//        restTemplate.getForObject(PREFIX+"/submitToPurchase?bookid="+bookid,int.class);
        //        boolean deleteBook=restTemplate.getForObject(PREFIX+"/deleteBookByBookId?id="+bookid, Boolean.class);
        return addNew;
    }

    /*
  商品结算
   */
    @RequestMapping("/settleBooks")
    @ResponseBody
    public int settleBooks(@Param("bookid") String[] bookid,@Param("phone")String phone,@Param("addressid")String addressid){
        String t_buyer_id = restTemplate.getForObject(PREFIX + "/buyer/queryIdByPhone?phone=" + phone, String.class);
        String[] temp=new String[bookid.length];
        int index=0;
        boolean flag=false;
       for(int i=0;i<bookid.length;i++){
           flag=false;
           bookid[i]=  bookid[i].replace('[',' ');
           bookid[i]=  bookid[i].replace(']',' ');
           bookid[i]=  bookid[i].replace('"',' ');
           bookid[i]=  bookid[i].trim();
           for(int j=0;j<index;j++){
               if(temp[j].equals(bookid[i])){
                   flag=true;
                   break;
               }
           }
           if(flag==false){
               temp[index++]=bookid[i];
               //新增order

               T_ORDER t_order=new T_ORDER();
               //查询t——buyer
               T_BUYER t_buyer = getT_BuyerByPhone(phone);
               T_BOOK t_book=getBookByBookId(bookid[i]);
               String t_order_id=new SimpleDateFormat("yymmssyyyyMMdd").format(new Date())+t_buyer.getT_buyer_id();
               int nums = 1;
               //通过shopid查询sellerid
               String sellerid = restTemplate.getForObject(PREFIX+"/getSelleridByShopId?shopid="+t_book.getT_shop_id(),String.class);
               t_order.setT_buyer_id(t_buyer.getT_buyer_id());
               t_order.setT_order_id(t_order_id);
               t_order.setT_book_id(bookid[i]);
               t_order.setT_bookid_quantity(nums);
               t_order.setT_address_id(addressid);
               t_order.setT_order_date(new Date());
               t_order.setT_book_now_price(t_book.getT_book_now_price());
               t_order.setT_total_price(nums*t_book.getT_book_now_price());
               t_order.setT_shop_id(t_book.getT_shop_id());
               t_order.setT_seller_id(sellerid);
               t_order.setT_book_status(1);
               Boolean insertData = restTemplate.postForObject(PREFIX + "/insertNewOrder", t_order, boolean.class);

               //修改每个book的状态为1

           }

       }

        return restTemplate.postForObject(PREFIX+"/buyer/cart/settleBooks",bookid,int.class);
//        return restTemplate.postForObject(PREFIX+"/buyer/cart/settleBooks",temp,int.class);
//
       //生成新的订单insertintoneworder


    }

    /*
   通过bookid删除cart记录

    */
    @RequestMapping("/deleteCartRecordByBookid")
    @ResponseBody
    public Boolean delete(@Param("bookid")String bookid){
        return restTemplate.getForObject(PREFIX+"/buyer/cart/deleteCartRecordByBookId?bookid="+bookid,Boolean.class);
    }

    /*
   常用方法：
     通过phone返回T_Buyer对象
    */
    public T_BUYER getT_BuyerByPhone(String phone){
        String t_buyer_id = restTemplate.getForObject(PREFIX + "/buyer/queryIdByPhone?phone=" + phone, String.class);
        T_BUYER buyer=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuyerByBuyerId?id="+t_buyer_id,T_BUYER.class);
        return buyer;
    }

    /*
    通过bookid返回book
     */
    public T_BOOK getBookByBookId(String bookid){
        return restTemplate.getForObject(PREFIX+"/buyer/product/getBookByBookId?id="+bookid,T_BOOK.class);
    }


}
