package com.xin.jiushutao.utils;

import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_BUYER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/2 17:07
 * @Version 1.0
 **/
public class FormalUtils {

    private String PREFIX="http://localhost:9001";
    @Autowired
    private RestTemplate restTemplate;

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

    /*
    通过shopid返回sellerid
     */

    public String getSellerIdByShopId(String shopid){
        return restTemplate.getForObject(PREFIX+"/getSelleridByShopId?shopid="+shopid,String.class);
    }
}
