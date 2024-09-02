package com.xin.jiushutao.controller;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xin.jiushutao.pojo.*;
import com.xin.jiushutao.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xin.jiushutao.proxy.buyer.OrderShopname;
import feign.Param;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.jws.WebParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/15 16:27
 * @Version 1.0
 **/
@Controller
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    private String PREFIX="http://localhost:9001";
//private String PREFIX="http://60.204.157.187:9001";


    /*
    id: buyerid
    function: 渲染个人订单
     */
//    @RequestMapping("/buyer/allOrder")
//    public String allOrder(Model model, @Param("id")String id, HttpServletRequest request){
//        System.out.println("allorder------>"+id);
//        List allOrder = restTemplate.getForObject(PREFIX + "/buyer/allOrder?id=" + id, List.class);
//        List<OrderShopname> orderShopnames=new LinkedList<OrderShopname>();
//
//        //新增
//        List<T_BOOK> books=new LinkedList<T_BOOK>();
//
//        System.out.println("allOrder.size()===>"+allOrder.size());
//        int i=1;
//        for (Object o : allOrder) {
//
//            OrderShopname orderShopname=new OrderShopname();
//            T_ORDER t_order1 = JSON.parseObject(JSON.toJSONString(o), T_ORDER.class);
//            orderShopname.setT_order(t_order1);
//            String shopId=t_order1.getT_shop_id();
//            String shopName=restTemplate.getForObject(PREFIX+"/buyer/queryShopNameByShopId?id="+shopId,String.class);
//            System.out.println("t_order1.getT_book_id()====="+t_order1.getT_book_id());
//            T_BOOK t_book =restTemplate.getForObject(PREFIX+"/findByBookId?id="+t_order1.getT_book_id(),T_BOOK.class);
//            T_BUYER t_buyer = restTemplate.getForObject(PREFIX + "/getTBuyerByOrderId?id=" + t_order1.getT_order_id(), T_BUYER.class);
//            if(t_book==null||t_buyer==null){
//                System.out.println("此时i==========》"+i);
//                continue;
//            }
//            System.out.println(t_book);
//            double originPrice= t_order1.getT_bookid_quantity()*t_book.getT_book_origin_price();
//            orderShopname.setShopName(shopName);
//            orderShopname.setT_book(t_book);
//            orderShopname.setOriginTotalPrice(originPrice);
//            orderShopname.setT_buyer(t_buyer);
//            orderShopnames.add(orderShopname);
//            books.add(t_book);
//            i++;
//        }
//        model.addAttribute("loginUser",id);
//        model.addAttribute("allOrder",orderShopnames);
//        model.addAttribute("books",books);
//        System.out.println("books=>"+books);
//
//        System.out.println("modelattribute：allorder=》"+orderShopnames);
//
//
//        List<T_ADDRESS> addresses=new LinkedList<>();
//        //返回收货地址
//        List lists = restTemplate.getForObject(PREFIX + "/address/getAllAddresByBuyerid?buyerid=" + id, List.class);
//        for (Object list : lists) {
//            T_ADDRESS t_address = JSONObject.parseObject(JSON.toJSONString(list), T_ADDRESS.class);
//            addresses.add(t_address);
//        }
//        model.addAttribute("addresses",addresses);
//
//        System.out.println("addressese=<"+addresses);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "personal_center";
//    }


//    /*
//    返回购物车清单，从购物车购买后,渲染订单列表
//
//    */
//    @RequestMapping("/buyer/allOrder2")
//    public String allOrder2(Model model,@Param("id")String id, HttpServletRequest request) throws IOException {
//        System.out.println("allorder------>"+id);
//        //list接收不到
//
//        List<String> allOrder = restTemplate.getForObject(PREFIX + "/buyer/order/getAllCartLists?buyerid=" + id, List.class);
//        List<OrderShopname> orderShopnames=new LinkedList<OrderShopname>();
//        System.out.println("allorder==>"+allOrder);
//
//
//
//
//        int index=0;
//     String[] ttBook=new String[allOrder.size()];
//     for(int k=0;k<allOrder.size();k++){
//         boolean flag=true;
//         String s = JSONObject.parseObject(JSON.toJSONString(allOrder.get(k)), String.class);
//         for(int l=0;l<index;l++){
//             if(s.equals(ttBook[l])){
//                 flag=false;
//                 break;
//             }
//         }
//         if(flag==true){
//             ttBook[index++]=s;
//         }
//     }
//        List<T_BOOK> realBook=new LinkedList<T_BOOK>();
//        for (String o : ttBook) {
//            T_BOOK book =restTemplate.getForObject(PREFIX+"/findByBookId?id="+o,T_BOOK.class);
//           if(book!=null)
//            realBook.add(book);
//        }
//        System.out.println("realBOoksiez==>"+realBook.size());
//        int i=1;
//        System.out.println("输出realbook========================");
//        for (T_BOOK o : realBook) {
//            System.out.println(o);
//
//            OrderShopname orderShopname=new OrderShopname();
//            T_ORDER t_order1 = restTemplate.getForObject(PREFIX + "/getOrderEntityByBookid?bookid=" + o.getT_book_id(), T_ORDER.class);
//
//            if(t_order1==null){
//                System.out.println("t_order===break>");
//                continue;
//            }
//
//            //获得addressid通过orderid
//            System.out.println("输出torder===");
//            System.out.println(t_order1);
//
//            T_ADDRESS t_address = restTemplate.getForObject(PREFIX + "/address/getAddressByAddressId?addressid=" + t_order1.getT_address_id(), T_ADDRESS.class);
//            System.out.println("allorder--->adress--->"+t_address);
//            orderShopname.setT_address(t_address);
//            orderShopname.setT_order(t_order1);
//            String shopId=t_order1.getT_shop_id();
//            String shopName=restTemplate.getForObject(PREFIX+"/buyer/queryShopNameByShopId?id="+shopId,String.class);
//            System.out.println("t_order1.getT_book_id()====="+t_order1.getT_book_id());
//            T_BUYER t_buyer = restTemplate.getForObject(PREFIX + "/buyer/personal/getBuyerByBuyerId/?id=" + id, T_BUYER.class);
//            System.out.println("通过order查询到的buyer实体\n"+t_buyer);
//            if(o==null||t_buyer==null){
//                System.out.println("此时i==========》"+i);
//                continue;
//            }
//            double originPrice= t_order1.getT_bookid_quantity()*o.getT_book_origin_price();
//            orderShopname.setShopName(shopName);
//            orderShopname.setT_book(o);
//            orderShopname.setOriginTotalPrice(originPrice);
//            orderShopname.setT_buyer(t_buyer);
//
//
//            orderShopnames.add(orderShopname);
//            i++;
//        }
//
//        System.out.println("allorder2:::>"+orderShopnames);
//        model.addAttribute("loginUser",id);
//        System.out.println("loginUser=allorder==>"+id);
//        model.addAttribute("allOrder",orderShopnames);
//
//
//        List<T_ADDRESS> addresses=new LinkedList<>();
//        //返回收货地址
//        List lists = restTemplate.getForObject(PREFIX + "/address/getAllAddresByBuyerid?buyerid=" + id, List.class);
//        for (Object list : lists) {
//            T_ADDRESS t_address = JSONObject.parseObject(JSON.toJSONString(list), T_ADDRESS.class);
//            addresses.add(t_address);
//        }
//        model.addAttribute("addresses",addresses);
//        return "personal_center";
//    }
//


    /*
   返回购物车清单，从购物车购买后,渲染订单列表,新版本，返回的是bookid在order中且buyerid相同的

   */
    @RequestMapping("/buyer/allOrder2")
    public String allOrder2(Model model,@Param("id")String id, HttpServletRequest request) throws IOException {

        //返回在torder和tbook中bookid相同，且为该用户的已完成订单，订单状态为5

//        List test = restTemplate.getForObject(PREFIX+"/buyer/order/getOrdersByBuyerID?buyerid="+id,List.class);
//        for(Object t:test){
//            T_ORDER t_order=JSONObject.parseObject(JSON.toJSONString(t),T_ORDER.class);
//
//        }


        //返回该用户所有订单信息
        T_ORDER[] allOrder = restTemplate.getForObject(PREFIX+"/buyer/order/getOrdersByBuyerID?buyerid="+id,T_ORDER[].class);
        //通过tbuyerid返回用户对应实体t_buyer
        T_BUYER t_buyer = restTemplate.getForObject(PREFIX + "/buyer/personal/getBuyerByBuyerId/?id=" + id, T_BUYER.class);
        List<OrderShopname> orderShopnames=new LinkedList<OrderShopname>();
//        for(int i=0;i<allOrder.length;i++){
//            System.out.println("=========="+allOrder[i]);
//        }
//        System.out.println("allorder=-----------------------------------------=>");
//        System.out.println("t_buyer==>"+t_buyer);
        for (T_ORDER order : allOrder) {
            OrderShopname OrderShopname=new OrderShopname();
            //通过bookid返回对应的t_book实体
            T_BOOK t_book=restTemplate.getForObject(PREFIX+"/buyer/product/getBookByBookId?id="+order.getT_book_id(),T_BOOK.class);
            OrderShopname.setT_buyer(t_buyer);
            OrderShopname.setT_book(t_book);
            OrderShopname.setT_order(order);
            //通过addressid返回对应的t_address实体
            T_ADDRESS address = restTemplate.getForObject(PREFIX + "/address/getAddressByAddressId?addressid=" + order.getT_address_id(), T_ADDRESS.class);
            OrderShopname.setT_address(address);
            String shopName=restTemplate.getForObject(PREFIX+"/buyer/queryShopNameByShopId?id="+order.getT_shop_id(),String.class);
            OrderShopname.setShopName(shopName);
            double originPrice= order.getT_bookid_quantity()*t_book.getT_book_origin_price();
            OrderShopname.setOriginTotalPrice(originPrice);
            orderShopnames.add(OrderShopname);
        }
        model.addAttribute("allOrder",orderShopnames);

        //旧版本
//        List<String> allOrder = restTemplate.getForObject(PREFIX + "/buyer/order/getAllCartLists?buyerid=" + id, List.class);
//        int index=0;
//        String[] ttBook=new String[allOrder.size()];
//        for(int k=0;k<allOrder.size();k++){
//            boolean flag=true;
//            String s = JSONObject.parseObject(JSON.toJSONString(allOrder.get(k)), String.class);
//            for(int l=0;l<index;l++){
//                if(s.equals(ttBook[l])){
//                    flag=false;
//                    break;
//                }
//            }
//            if(flag==true){
//                ttBook[index++]=s;
//            }
//        }

//        List<OrderShopname> orderShopnames=new LinkedList<OrderShopname>();
//
//        List<T_BOOK> realBook=new LinkedList<T_BOOK>();
//        for (T_ORDER o : allOrder) {
//            T_BOOK book =restTemplate.getForObject(PREFIX+"/findByBookId?id="+o.getT_book_id(),T_BOOK.class);
//            if(book!=null)
//                realBook.add(book);
//        }
//        System.out.println("realBOoksiez==>"+realBook.size());
//        int i=1;
//        T_BUYER t_buyer = restTemplate.getForObject(PREFIX + "/buyer/personal/getBuyerByBuyerId/?id=" + id, T_BUYER.class);
//
//        System.out.println("输出realbook========================");
//        for (T_BOOK o : realBook) {
//
//            OrderShopname orderShopname=new OrderShopname();
//            T_ORDER t_order1 = restTemplate.getForObject(PREFIX + "/getOrderEntityByBookid?bookid=" + o.getT_book_id(), T_ORDER.class);
//
//            if(t_order1==null){
//                System.out.println("t_order===break>");
//                continue;
//            }
//
//            //获得addressid通过orderid
//            System.out.println("输出torder===");
//            System.out.println(t_order1);
//
//            T_ADDRESS t_address = restTemplate.getForObject(PREFIX + "/address/getAddressByAddressId?addressid=" + t_order1.getT_address_id(), T_ADDRESS.class);
//            System.out.println("allorder--->adress--->"+t_address);
//            orderShopname.setT_address(t_address);
//            orderShopname.setT_order(t_order1);
//            String shopId=t_order1.getT_shop_id();
//            String shopName=restTemplate.getForObject(PREFIX+"/buyer/queryShopNameByShopId?id="+shopId,String.class);
//            System.out.println("t_order1.getT_book_id()====="+t_order1.getT_book_id());
//            System.out.println("通过order查询到的buyer实体\n"+t_buyer);
//            if(o==null||t_buyer==null){
//                System.out.println("此时i=====null=====》"+i);
//                continue;
//            }
//            double originPrice= t_order1.getT_bookid_quantity()*o.getT_book_origin_price();
//            orderShopname.setShopName(shopName);
//            orderShopname.setT_book(o);
//            orderShopname.setOriginTotalPrice(originPrice);
//            orderShopname.setT_buyer(t_buyer);
//
//
//            orderShopnames.add(orderShopname);
//            i++;
//        }
//
//        System.out.println("allorder2:::>"+orderShopnames);
//        model.addAttribute("allOrder",orderShopnames);
//
//
//        List<T_ADDRESS> addresses=new LinkedList<>();
//        //返回收货地址
//        List lists = restTemplate.getForObject(PREFIX + "/address/getAllAddresByBuyerid?buyerid=" + id, List.class);
//        for (Object list : lists) {
//            T_ADDRESS t_address = JSONObject.parseObject(JSON.toJSONString(list), T_ADDRESS.class);
//            addresses.add(t_address);
//        }
//        model.addAttribute("addresses",addresses);





        //取消的订单
        List<OrderShopname> cancelOrderShopnames=new LinkedList<OrderShopname>();
//        T_ORDER[] forObject = restTemplate.getForObject(PREFIX + "/buyer/order/getOrdersByBuyerIDAndStatus?buyerid=" + id + "&status=4", T_ORDER[].class);
        T_CANCEL_ORDER[] forObject = restTemplate.getForObject(PREFIX + "/buyer/order/getCancelOrdersByBuyerIDAndStatus?buyerid=" + id + "&status=4", T_CANCEL_ORDER[].class);

//        List<T_BOOK> resLists =(List<T_BOOK>) request.getSession().getAttribute("purchasingBooks");
//        System.out.println("insertNewOrder2==>"+resLists);
        for (T_CANCEL_ORDER order : forObject) {
            OrderShopname cancelOrderShopname=new OrderShopname();
            T_BOOK t_book=restTemplate.getForObject(PREFIX+"/buyer/product/getBookByBookId?id="+order.getT_book_id(),T_BOOK.class);
            cancelOrderShopname.setT_buyer(t_buyer);
            cancelOrderShopname.setT_book(t_book);

            T_ORDER tempOrder=new T_ORDER();
            tempOrder.setT_address_id(order.getT_address_id());
            tempOrder.setT_book_status(order.getT_book_status());
            tempOrder.setT_seller_id(order.getT_seller_id());
            tempOrder.setT_order_date(order.getT_order_date());
            tempOrder.setT_buyer_id(order.getT_buyer_id());
            tempOrder.setT_book_now_price(order.getT_book_now_price());
            tempOrder.setT_order_id(order.getT_order_id());
            tempOrder.setT_bookid_quantity(order.getT_bookid_quantity());
            tempOrder.setT_total_price(order.getT_total_price());
            cancelOrderShopname.setT_order(tempOrder);
            T_ADDRESS address = restTemplate.getForObject(PREFIX + "/address/getAddressByAddressId?addressid=" + order.getT_address_id(), T_ADDRESS.class);

            cancelOrderShopname.setT_address(address);
            String shopName=restTemplate.getForObject(PREFIX+"/buyer/queryShopNameByShopId?id="+order.getT_shop_id(),String.class);
            cancelOrderShopname.setShopName(shopName);
            double originPrice= order.getT_bookid_quantity()*t_book.getT_book_origin_price();
            cancelOrderShopname.setOriginTotalPrice(originPrice);
            cancelOrderShopnames.add(cancelOrderShopname);
        }
        //        Boolean deltestatus=restTemplate.getForObject(PREFIX+"/deleteBookByBookIdid="+bookid,Boolean.class);

        model.addAttribute("canceledOrders",cancelOrderShopnames);
        model.addAttribute("buyer",t_buyer);
        return "personal_center";
    }




    /*
    点击 立即购买模块
     */
    @RequestMapping("/insertNewOrder")
    @ResponseBody
    public boolean insertNewOrder(@Param("phone")String phone,@Param("bookid")String bookid,@Param("quantity")String quantity){


        T_ORDER t_order=new T_ORDER();
            //查询t——buyer
        T_BUYER t_buyer = getT_BuyerByPhone(phone);
        T_BOOK t_book=getBookByBookId(bookid);
        String orderid=t_book.getT_book_category()+t_book.getT_book_id();
//        String t_order_id=new SimpleDateFormat("HHmmssyyyyMMdd").format(new Date())+t_buyer.getT_buyer_id();
        int nums = Integer.parseInt(quantity);
        //通过shopid查询sellerid
        String sellerid = restTemplate.getForObject(PREFIX+"/getSelleridByShopId?shopid="+t_book.getT_shop_id(),String.class);
        t_order.setT_buyer_id(t_buyer.getT_buyer_id());
        t_order.setT_order_id(orderid);
        t_order.setT_book_id(bookid);
        t_order.setT_bookid_quantity(nums);
        t_order.setT_order_date(new Date());
        t_order.setT_book_now_price(t_book.getT_book_now_price());
        t_order.setT_total_price(nums*t_book.getT_book_now_price());
        t_order.setT_shop_id(t_book.getT_shop_id());
        t_order.setT_seller_id(sellerid);
        t_order.setT_book_status(1);
        Boolean insertData = restTemplate.postForObject(PREFIX + "/insertNewOrder", t_order, boolean.class);
//        Boolean deltestatus=restTemplate.getForObject(PREFIX+"/deleteBookByBookIdid="+bookid,Boolean.class);
        return insertData;

    }



    /*
  点击 立即购买模块2,从购物车提交多订单
   */
    @RequestMapping("/insertNewOrder2")
    @ResponseBody
    public boolean insertNewOrder2(@Param("phone")String phone,HttpServletRequest request){



        List<T_BOOK> resLists =(List<T_BOOK>) request.getSession().getAttribute("purchasingBooks");
        boolean res=true;
        for (T_BOOK resList : resLists) {
            T_ORDER t_order=new T_ORDER();
            //查询t——buyer
//            T_BUYER t_buyer = getT_BuyerByPhone(phone);

            String t_buyer_id=null;
            String addressID=null;
            Cookie[] cookies=request.getCookies();
            if(cookies!=null)
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("buyerID")){
                    t_buyer_id=cookie.getValue();
                }
                if(cookie.getName().equals("addressID")){
                    addressID=cookie.getValue();
                }
            }

            T_BUYER t_buyer=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuyerByBuyerId?id="+t_buyer_id,T_BUYER.class);
//            T_BOOK t_book=getBookByBookId(resList.getT_book_id());
            T_BOOK t_book=restTemplate.getForObject(PREFIX+"/buyer/product/getBookByBookId?id="+resList.getT_book_id(),T_BOOK.class);
//            String t_order_id=new SimpleDateFormat("yyyyMMdd").format(new Date())+t_buyer.getT_buyer_id();
            String t_order_id=UUID.randomUUID().toString().replace("-", "");



            int nums = 1;

            //通过shopid查询sellerid
            String sellerid = restTemplate.getForObject(PREFIX+"/getSelleridByShopId?shopid="+t_book.getT_shop_id(),String.class);
            t_order.setT_buyer_id(t_buyer.getT_buyer_id());
            t_order.setT_order_id(t_order_id);
            t_order.setT_book_id(t_book.getT_book_id());
            t_order.setT_bookid_quantity(nums);
            t_order.setT_order_date(new Date());
            t_order.setT_book_now_price(t_book.getT_book_now_price());
            t_order.setT_total_price(nums*t_book.getT_book_now_price());
            t_order.setT_shop_id(t_book.getT_shop_id());
            t_order.setT_seller_id(sellerid);
            t_order.setT_book_status(5);
            t_order.setT_address_id(addressID);
            Boolean insertData = restTemplate.postForObject(PREFIX + "/insertNewOrder", t_order, boolean.class);

            //修改tbook中对应tbookid的书籍的状态为5，表示订单完成
            int changeBookStatusTo5=restTemplate.getForObject(PREFIX+"/updateStatusByBookid5?bookid="+t_book.getT_book_id(),Integer.class);

            boolean change=changeBookStatusTo5!=0?true:false;
            res&=(insertData&change);

        }



        //        Boolean deltestatus=restTemplate.getForObject(PREFIX+"/deleteBookByBookIdid="+bookid,Boolean.class);
        return res;

    }







    /*
    修改订单状态通过bookid
     */
    @RequestMapping("/updateStatusByBookid2")
    @ResponseBody
    public int updateStatusByBookid2(@Param("orderid")String orderid){
        String bookid=restTemplate.getForObject(PREFIX+"/getBookidByOrderid?orderid="+orderid,String.class);
        return restTemplate.getForObject(PREFIX+"/updateStatusByBookid2?bookid="+bookid,int.class);
    }

    /*
   修改订单状态通过bookid
    */
    @RequestMapping("/updateStatusByBookid3")
    @ResponseBody
    public int updateStatusByBookid3(@Param("orderid")String orderid){
        String bookid=restTemplate.getForObject(PREFIX+"/getBookidByOrderid?orderid="+orderid,String.class);
        return restTemplate.getForObject(PREFIX+"/updateStatusByBookid3?bookid="+bookid,int.class);
    }

    /*
  修改订单状态通过bookid
   */
    @RequestMapping("/updateStatusByBookid0")
    @ResponseBody
    public int updateStatusByBookid0(@Param("bookid")String bookid){
        //删除购物车对应书籍
        restTemplate.getForObject(PREFIX+"/buyer/cart/deleteCartRecordByBookId?bookid="+bookid,Boolean.class);
        return restTemplate.getForObject(PREFIX+"/updateStatusByBookid0?bookid="+bookid,int.class);
    }

    /*
    跳转到支付页面
     */
    @RequestMapping("/order")
    public String order(Model model,@Param("phone")String phone,@Param("bookid")String bookid,@Param("quantity")String quantity){
        T_BUYER buyer = getT_BuyerByPhone(phone);
        T_BOOK book = getBookByBookId(bookid);
        model.addAttribute("buyer",buyer);
        model.addAttribute("book",book);
        model.addAttribute("quantity",quantity);
        double total=Integer.parseInt(quantity)*book.getT_book_now_price();
model.addAttribute("totalPrice",total);

        return "check_order";
    }


    /*
  跳转到支付页面
   */
    @RequestMapping("/orderMore")
@ResponseBody
    public String orderMore(HttpServletResponse response, Model model,@Param("bookid")String[] bookid,@Param("phone")String phone,HttpServletRequest request) throws InterruptedException {

        Thread.currentThread().sleep(1000);
        String t_buyer_id = restTemplate.getForObject(PREFIX + "/buyer/queryIdByPhone?phone=" + phone, String.class);
        //获得收货人地址列表
        List forObject = restTemplate.getForObject(PREFIX + "/address/getAllAddresByBuyerid?buyerid=" + t_buyer_id, List.class);
        List<T_ADDRESS> addresses=new LinkedList<>();
        for (Object o : forObject) {
            T_ADDRESS t_address = JSONObject.parseObject(JSON.toJSONString(o), T_ADDRESS.class);
            addresses.add(t_address);
        }
        model.addAttribute("addressPurchse",addresses);
        request.getSession().setAttribute("addressPurchse",addresses);
        List<T_BOOK> resLists=new LinkedList<>();

        double totalPrice=0;
        int quantity=0;
        String [] tempS=new String[bookid.length];
        int index=0;
        for (String s : bookid) {
            s=s.replace(']',' ');
            s=s.replace('[',' ');
            s=s.replace('"',' ');
            s=s.trim();
            boolean flag=false;
            for(int j=0;j<index;j++){
                if(tempS[j].equals(s)){
                    flag=true;
                    break;
                }
            }
            if(flag){
                continue;
            }
            tempS[index++]=s;

            //添加bookid的cookie
            Cookie bookID=new Cookie("bookID",s);
            bookID.setPath("/");
            bookID.setMaxAge(24*1000*3600);
            response.addCookie(bookID);

            T_BOOK t_book =restTemplate.getForObject(PREFIX+"/findByBookId?id="+s,T_BOOK.class);
            totalPrice+=t_book.getT_book_now_price();
            quantity++;
            resLists.add(t_book);
//            Integer status = restTemplate.getForObject(PREFIX + "/submitToPurchase?bookid=" + s, int.class);
//            System.out.println("status===>"+status);
//            if(status!=0){
//               T_BOOK t_book =restTemplate.getForObject(PREFIX+"/findByBookId?id="+s,T_BOOK.class);
//              totalPrice+=t_book.getT_book_now_price();
//              quantity++;
//               resLists.add(t_book);
//           }
        }


        model.addAttribute("quantity",quantity);
        request.getSession().setAttribute("quantity",quantity);
        model.addAttribute("purchasingBooks",resLists);
        request.getSession().setAttribute("purchasingBooks",resLists);
        request.getSession().setAttribute("totalPrice",totalPrice);


        //如果有，在cart表中删除该bookid记录
        //从购物车跳转过来需执行的部分
        for(String loop:tempS){
            restTemplate.getForObject(PREFIX+"/buyer/cart/deleteCartRecordByBookId?bookid="+loop, Boolean.class);
        }





        return "/pay";
    }
    /*
    跳转到check_order2
     */
    @RequestMapping("/pay")
    public String senredirectOrder2(Model model,HttpServletRequest request){
        Object totalPrice =(Double) request.getSession().getAttribute("totalPrice");
        model.addAttribute("totalPrice",totalPrice);
        List<T_BOOK> resLists =(List<T_BOOK>) request.getSession().getAttribute("purchasingBooks");
        model.addAttribute("purchasingBooks",resLists);
        int quantity=(Integer) request.getSession().getAttribute("quantity");
        model.addAttribute("quantity",quantity);
        List<T_ADDRESS> addressPurchse = (List<T_ADDRESS>)request.getSession().getAttribute("addressPurchse");
        model.addAttribute("addressPurchse",addressPurchse);
//        model.addAttribute("cl")
        return "check_order2";
    }

    /*
    跳转到正在支付页面
     */
    @RequestMapping("/confirmOrder/{phone}/{bookid}/{quantity}")
    public String confirmOrder(Model mo1del, @PathVariable("phone") String phone, @PathVariable("bookid") String bookid, @PathVariable("quantity") String quantity){
       mo1del.addAttribute("phone",phone);
       mo1del.addAttribute("bookid",bookid);
       mo1del.addAttribute("quantity",quantity);
        return "confirm_order";
    }


    /*
    跳转到正在支付页面2：：：接收来自购物车的跳转
     */
    @RequestMapping("/confirmOrder2")
    public String confirmOrder2(HttpServletResponse response,@Param("addressid")String addressid, Model model,HttpServletRequest request){
        Object totalPrice =(Double) request.getSession().getAttribute("totalPrice");
        model.addAttribute("totalPrice",totalPrice);
        List<T_BOOK> resLists =(List<T_BOOK>) request.getSession().getAttribute("purchasingBooks");
        model.addAttribute("purchasingBooks",resLists);

        //太长了
//        Cookie cookie=new Cookie("purchasingBooks",resLists.toString());
//        cookie.setPath("/");
//        cookie.setMaxAge(24*3600*1000);
//        response.addCookie(cookie);
        int quantity=(Integer) request.getSession().getAttribute("quantity");
        model.addAttribute("quantity",quantity);
        List<T_ADDRESS> addressPurchse = (List<T_ADDRESS>)request.getSession().getAttribute("addressPurchse");
        model.addAttribute("addressPurchse",addressPurchse);
        T_ADDRESS address = restTemplate.getForObject(PREFIX + "/address/getAddressByAddressId?addressid=" + addressid, T_ADDRESS.class);
        model.addAttribute("address",address);


        Cookie addressID=new Cookie("addressID",address.getT_address_id());
        addressID.setPath("/");
        addressID.setMaxAge(24*3600*1000);
        response.addCookie(addressID);

        return "confirm_order2";
    }

    /*
    支付完成页面
     */
    @RequestMapping("/orderSuccess")
    public String orderSuccess(){

        return "order_success";
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







    //新增





    /*

     */
//    @RequestMapping("/cancelOrders")
//    @ResponseBody
//    public String cancelOrders(@Param("buyerid")String buyerid){
//        //返回order实体
//
//
//        //返回book实体
//    }


    /*
    返回未完成订单，即order中status=4
通过buyerid返回order中状态为4的bookid，并通过buyerid查询返回tbuyer，通过bookid查询返回tbook，并生成对应的shopname
     */
    @RequestMapping("/getAllCancelOrders")
    @ResponseBody
    public String getAllCancelOrders(HttpServletRequest request,Model model){
        Cookie[] cookies = request.getCookies();
        String buyerid=null;
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("buyerID")){
                    buyerid=cookie.getValue();
                }
            }
        }

        T_BUYER t_buyer=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuyerByBuyerId?id="+buyerid,T_BUYER.class);

        List<OrderShopname> cancelOrderShopnames=new LinkedList<OrderShopname>();
        T_ORDER[] forObject = restTemplate.getForObject(PREFIX + "/buyer/order/getOrdersByBuyerIDAndStatus?buyerid=" + buyerid + "&status=4", T_ORDER[].class);

//        List<T_BOOK> resLists =(List<T_BOOK>) request.getSession().getAttribute("purchasingBooks");
//        System.out.println("insertNewOrder2==>"+resLists);
        OrderShopname cancelOrderShopname=new OrderShopname();
        for (T_ORDER order : forObject) {


            T_BOOK t_book=restTemplate.getForObject(PREFIX+"/buyer/product/getBookByBookId?id="+order.getT_book_id(),T_BOOK.class);
            cancelOrderShopname.setT_buyer(t_buyer);
            cancelOrderShopname.setT_book(t_book);
            cancelOrderShopname.setT_order(order);
            T_ADDRESS address = restTemplate.getForObject(PREFIX + "/address/getAddressByAddressId?addressid=" + order.getT_address_id(), T_ADDRESS.class);

            cancelOrderShopname.setT_address(address);
            String shopName=restTemplate.getForObject(PREFIX+"/buyer/queryShopNameByShopId?id="+order.getT_shop_id(),String.class);
            cancelOrderShopname.setShopName(shopName);
            double originPrice= order.getT_bookid_quantity()*t_book.getT_book_origin_price();
            cancelOrderShopname.setOriginTotalPrice(originPrice);
            cancelOrderShopnames.add(cancelOrderShopname);
        }



        //        Boolean deltestatus=restTemplate.getForObject(PREFIX+"/deleteBookByBookIdid="+bookid,Boolean.class);

        model.addAttribute("cancelOrders",cancelOrderShopnames);
   return JSON.toJSONString(cancelOrderShopnames);
//        return "personal_center";
    }



    /*
    返回未完成订单，即order中status=5
通过buyerid返回order中状态为4的bookid，并通过buyerid查询返回tbuyer，通过bookid查询返回tbook，并生成对应的shopname
     */
    @RequestMapping("/getAllFinishedOrders")
    @ResponseBody
    public String getAllFinishedOrders(HttpServletRequest request,Model model){
        Cookie[] cookies = request.getCookies();
        String buyerid=null;
        if(cookies.length>0&&cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("buyerID")){
                    buyerid=cookie.getValue();
                }
            }
        }

        T_BUYER t_buyer=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuyerByBuyerId?id="+buyerid,T_BUYER.class);

        List<OrderShopname> orderShopnames=new LinkedList<OrderShopname>();
        T_ORDER[] forObject = restTemplate.getForObject(PREFIX + "/buyer/order/getOrdersByBuyerIDAndStatus?buyerid=" + buyerid + "&status=5", T_ORDER[].class);

//        List<T_BOOK> resLists =(List<T_BOOK>) request.getSession().getAttribute("purchasingBooks");
//        System.out.println("insertNewOrder2==>"+resLists);
        boolean res=true;
        OrderShopname orderShopname=new OrderShopname();
        for (T_ORDER order : forObject) {


            T_BOOK t_book=restTemplate.getForObject(PREFIX+"/buyer/product/getBookByBookId?id="+order.getT_book_id(),T_BOOK.class);
            int nums = 1;
            String sellerid = restTemplate.getForObject(PREFIX+"/getSelleridByShopId?shopid="+t_book.getT_shop_id(),String.class);
            orderShopname.setT_buyer(t_buyer);
            orderShopname.setT_book(t_book);
            orderShopname.setT_order(order);
            T_ADDRESS address = restTemplate.getForObject(PREFIX + "/address/getAddressByAddressId?addressid=" + order.getT_address_id(), T_ADDRESS.class);

            orderShopname.setT_address(address);
            String shopName=restTemplate.getForObject(PREFIX+"/buyer/queryShopNameByShopId?id="+order.getT_shop_id(),String.class);
            orderShopname.setShopName(shopName);
            double originPrice= order.getT_bookid_quantity()*t_book.getT_book_origin_price();
            orderShopname.setOriginTotalPrice(originPrice);
            orderShopnames.add(orderShopname);
        }



        //        Boolean deltestatus=restTemplate.getForObject(PREFIX+"/deleteBookByBookIdid="+bookid,Boolean.class);


        model.addAttribute("finishedOrders",orderShopnames);
        return JSON.toJSONString(orderShopnames);
    }


    /*
   通过订单id改变收货状态
    */
    @RequestMapping("/updateStatusByBookid4")
    @ResponseBody
    public int updateStatusByBookid4(@RequestParam("bookid")String bookid){
        return restTemplate.getForObject(PREFIX+"/updateStatusByBookid4?bookid="+bookid,Integer.class);
    }



    /*
    新增取消订单
     */
    @RequestMapping("/insertNewCancelOrder")
    @ResponseBody
    public boolean insertNewCancelOrder(@Param("phone")String phone,HttpServletRequest request){

        List<T_BOOK> resLists =(List<T_BOOK>) request.getSession().getAttribute("purchasingBooks");
        boolean res=true;
        for (T_BOOK resList : resLists) {
//            T_ORDER t_order=new T_ORDER();
            T_CANCEL_ORDER t_order=new T_CANCEL_ORDER();
            //查询t——buyer
//            T_BUYER t_buyer = getT_BuyerByPhone(phone);

            String t_buyer_id=null;
            String addressID=null;
            Cookie[] cookies=request.getCookies();
            if(cookies!=null&&cookies.length>0)
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("buyerID")){
                        t_buyer_id=cookie.getValue();
                    }
                    if(cookie.getName().equals("addressID")){
                        addressID=cookie.getValue();
                    }
                }

            T_BUYER t_buyer=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuyerByBuyerId?id="+t_buyer_id,T_BUYER.class);
//            T_BOOK t_book=getBookByBookId(resList.getT_book_id());
            T_BOOK t_book=restTemplate.getForObject(PREFIX+"/buyer/product/getBookByBookId?id="+resList.getT_book_id(),T_BOOK.class);
//            String t_order_id=new SimpleDateFormat("yyyyMMdd").format(new Date())+t_buyer.getT_buyer_id();
            String t_order_id=UUID.randomUUID().toString().replace("-", "");



            int nums = 1;

            //通过shopid查询sellerid
            String sellerid = restTemplate.getForObject(PREFIX+"/getSelleridByShopId?shopid="+t_book.getT_shop_id(),String.class);
            t_order.setT_buyer_id(t_buyer.getT_buyer_id());
            t_order.setT_order_id(t_order_id);
            t_order.setT_book_id(t_book.getT_book_id());
            t_order.setT_bookid_quantity(nums);
            t_order.setT_order_date(new Date());
            t_order.setT_book_now_price(t_book.getT_book_now_price());
            t_order.setT_total_price(nums*t_book.getT_book_now_price());
            t_order.setT_shop_id(t_book.getT_shop_id());
            t_order.setT_seller_id(sellerid);
            t_order.setT_book_status(5);
            t_order.setT_address_id(addressID);
            Boolean insertData = restTemplate.postForObject(PREFIX + "/buyer/order/insertNewCancelOrder", t_order, boolean.class);

            //修改tbook中对应tbookid的书籍的状态为5，表示订单完成
//            int changeBookStatusTo5=restTemplate.getForObject(PREFIX+"/updateStatusByBookid5?bookid="+t_book.getT_book_id(),Integer.class);

//            boolean change=changeBookStatusTo5!=0?true:false;
//            System.out.println("change=>"+change);
            res&=(insertData);

        }



        //        Boolean deltestatus=restTemplate.getForObject(PREFIX+"/deleteBookByBookIdid="+bookid,Boolean.class);
        return res;

    }





}
