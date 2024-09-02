package com.xin.jiushutao.controller;

import com.xin.jiushutao.pojo.T_ADDRESS;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.proxy.buyer.OrderAndBuyer;
import com.xin.jiushutao.service.PersonalService;
import com.xin.jiushutao.service.TestService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/21 16:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/buyer/personal")

public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping("/getOrderAndBuyerByorderId")
    public OrderAndBuyer get(@Param("id") String id) {
        return personalService.getOrderAndBuyerByorderId(id);
    }

    /*
    通过buyerid获取buyer对象
     */
    @RequestMapping("/getBuyerByBuyerId")
    @ResponseBody
    public T_BUYER getBuyerByBuyerId(@Param("id")String id){
        return personalService.getBuyerByBuyerId(id);
    }



    /*
    通过addressid删除对应的address
     */
    @RequestMapping("/deleteAddressByAddressID")
    @ResponseBody
    public int deleteAddressByAddressID(@Param("addressid") String addressid){
        return personalService.deleteAddressByAddressID(addressid);
    }




    /*
    新增地址
     */
    @RequestMapping("/addNewAddress")
    @ResponseBody
    public int addnewaddress(@RequestBody T_ADDRESS t_address){
        System.out.println("新增地址9001---------------");
        System.out.println(t_address);
        return personalService.addnewaddress(t_address);
    }

    /*
    渲染收货地址，获得该买家所有地址
    */
    @RequestMapping("/getAllAddress")
    public List<T_ADDRESS> getaddress(@Param("buyerid") String buyerid){
        return personalService.getaddress(buyerid);
    }


    /*
    通过新的tbuyer对象更新数据库对象t_buyer
     */
@RequestMapping("/updateBuyer")
@ResponseBody
    public boolean updateT_Buyer( @RequestBody T_BUYER buyer){
    System.out.println(buyer);
        return personalService.updateT_Buyer(buyer);
    }


    @RequestMapping("/save")
    @ResponseBody
    public boolean saveT_buyer(@RequestBody T_BUYER t_buyer){
        return personalService.save(t_buyer);
    }



}
