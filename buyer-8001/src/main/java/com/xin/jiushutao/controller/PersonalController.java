package com.xin.jiushutao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xin.jiushutao.pojo.T_ADDRESS;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.proxy.buyer.AddressAndBuyer;
import com.xin.jiushutao.proxy.buyer.OrderAndBuyer;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/21 20:34
 * @Version 1.0
 **/
@Controller
@RequestMapping("/buyer/personal")
public class PersonalController {

    @Autowired
    private RestTemplate restTemplate;
    private String PREFIX="http://localhost:9001";
//private String PREFIX="http://60.204.157.187:9001";
    @RequestMapping("/getOrderAndBuyerByorderId")
    public String personalOrder(@Param("id") String id){
        OrderAndBuyer order = restTemplate.getForObject(PREFIX + "buyer/personal/getOrderAndBuyerByorderId?id=" + id, OrderAndBuyer.class);
        return "personal_center";
    }

    /*
 渲染收货地址，获得该买家所有地址
 */
    @RequestMapping("/getAllAddress")
    @ResponseBody
    public List<AddressAndBuyer> getaddress(@Param("buyerid") String buyerid){


//        List<T_ADDRESS> addresses=new LinkedList<>();
//        //返回收货地址
//        List lists = restTemplate.getForObject(PREFIX + "/address/getAllAddresByBuyerid?buyerid=" + buyerid, List.class);
//        for (Object list : lists) {
//            T_ADDRESS t_address = JSONObject.parseObject(JSON.toJSONString(list), T_ADDRESS.class);
//            addresses.add(t_address);
//        }
//        System.out.println("getaddress============");
//        System.out.println(lists);

        List lists = restTemplate.getForObject(PREFIX + "/buyer/personal/getAllAddress?buyerid=" + buyerid, List.class);
        List<AddressAndBuyer> listres=new LinkedList<AddressAndBuyer>();
        for (Object list : lists) {
            AddressAndBuyer addressAndBuyer=new AddressAndBuyer();
            T_ADDRESS t_address = JSONObject.parseObject(JSON.toJSONString(list), T_ADDRESS.class);
            T_BUYER buyer=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuyerByBuyerId?id="+buyerid,T_BUYER.class);
           addressAndBuyer.setT_address(t_address);
           addressAndBuyer.setT_buyer(buyer);
            listres.add(addressAndBuyer);
        }
        return listres;
    }



    /*
   新增地址
    */
    @RequestMapping("/addNewAddress")
    @ResponseBody
    public int addnewaddress(@Param("buyerid")String buyerid, @Param("address")String address, HttpServletRequest request){
        //,@Param("name")String name,@Param("phone")String phone
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String tempBuyerid=request.getParameter("buyerid");
        T_ADDRESS t_address=new T_ADDRESS();
        String name = request.getParameter("name");
        String phone=request.getParameter("phone");
        t_address.setT_address_default(0);
        t_address.setT_buyer_address(address);
        t_address.setT_buyer_id(tempBuyerid);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yymmssyyyyMMdd");
        String format = simpleDateFormat.format(new Date());
        t_address.setT_address_id(format);
        t_address.setT_address_name(name);
        t_address.setT_address_phone(phone);
        t_address.setT_buyer_id(buyerid);
        Integer integer = restTemplate.postForObject(PREFIX + "/buyer/personal/addNewAddress", t_address, int.class);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return integer;
    }


    /*
    通过bookid返回book
     */
    @RequestMapping("/getBookByBookid")
    @ResponseBody
    public String getBookByBookid(@Param("id")String id){
        T_BUYER t_buyer = restTemplate.getForObject(PREFIX + "/buyer/personal/getBuyerByBuyerId?id=" + id, T_BUYER.class);
        return JSON.toJSONString(t_buyer);
    }


    /*
    通过orderid删除该条order记录
     */
    @RequestMapping("/deleteOrderByOrderId")
    public String deleteOrderByOrderId(@Param("id")String id, Model model){
        Boolean right = restTemplate.getForObject(PREFIX + "buyer/order/deleteOrderByOrderId?id=" + id, Boolean.class);
        if(right){
            model.addAttribute("right",right);
        }
        return "personal_center";
    }

    /*
   删除address通过addressid
     */
    @RequestMapping("/deleteAddressByAddressID")
    @ResponseBody
    public int deleteAddressByAddressID(@Param("addressid")String addressid){
        return restTemplate.getForObject(PREFIX+"/buyer/personal/deleteAddressByAddressID?addressid="+addressid,int.class);
    }



    @RequestMapping("/updateBuyer")
    @ResponseBody
    public String updateBuyer(@Param("phone")String phone,@Param("email")String email,@Param("name")String name,@Param("sex")String sex){

        String t_buyer_id = restTemplate.getForObject(PREFIX + "/buyer/queryIdByPhone?phone=" + phone, String.class);
        T_BUYER buyer=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuyerByBuyerId?id="+t_buyer_id,T_BUYER.class);
        buyer.setT_buyer_email(email);
        buyer.setT_buyer_name(name);
        boolean sexBool=false;
        if(sex.equals("true")){
            sexBool=true;
        }
        buyer.setT_buyer_sex(sexBool);
        Boolean aBoolean = restTemplate.postForObject(PREFIX + "/buyer/personal/updateBuyer",buyer, Boolean.class);
        return JSON.toJSONString(aBoolean.toString());
    }

      /*
        点击 个人订单-》 收货管理 --》修改按钮
         */
      @RequestMapping("/updateAddress")
      @ResponseBody
      public Boolean updateAddress(@Param("address")String address,@Param("phone")String phone){
          T_BUYER t_buyer = getT_BuyerByPhone(phone);
          t_buyer.setT_buyer_address(address);
          Boolean aBoolean = restTemplate.postForObject(PREFIX + "/buyer/personal/save", t_buyer, Boolean.class);
          return aBoolean;
      }

      /*
    点击 个人订单-》 渲染收货管理
     */
      @RequestMapping("/getAddress")
      @ResponseBody
      public List<T_ADDRESS> getT_BuyerByPhone2(@Param("phone") String phone){
          String t_buyer_id = restTemplate.getForObject(PREFIX + "/buyer/queryIdByPhone?phone=" + phone, String.class);
//          T_BUYER buyer=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuyerByBuyerId?id="+t_buyer_id,T_BUYER.class);
          List<T_ADDRESS> addresses=new LinkedList<>();
          //返回收货地址
          List lists = restTemplate.getForObject(PREFIX + "/address/getAllAddresByBuyerid?buyerid=" + t_buyer_id, List.class);
          for (Object list : lists) {
              T_ADDRESS t_address = JSONObject.parseObject(JSON.toJSONString(list), T_ADDRESS.class);
              addresses.add(t_address);
          }
          return addresses;
      }


      @RequestMapping("/getBuyerByUserID")
      @ResponseBody
      public T_BUYER getBuyerByPhone(@Param("userID")String userID){
          T_BUYER buyer=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuyerByBuyerId?id="+userID,T_BUYER.class);

          return buyer;
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
}
