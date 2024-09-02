package com.xin.jiushutao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_ORDER;
import com.xin.jiushutao.pojo.T_SHOP;
import com.xin.jiushutao.proxy.buyer.BookAndShop;
import feign.Param;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/18 10:16
 * @Version 1.0
 **/
@RequestMapping("/buyer/product")
@Controller
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    private String PREFIX="http://localhost:9001";
//    private String PREFIX="http://60.204.157.187:9001";

    /*
    按书籍进行分类
     */
    @RequestMapping("/getCategoryBooks")
    @ResponseBody
    public List<T_BOOK> getCategoryBooks(@Param("category")String category){
        List lists = restTemplate.getForObject(PREFIX + "/buyer/product/getCategoryBooks?category=" + category, List.class);
       List<T_BOOK> res=new LinkedList<T_BOOK>();
        for (Object list : lists) {
            T_BOOK t_book = JSONObject.parseObject(JSON.toJSONString(list), T_BOOK.class);
            res.add(t_book);
        }
        return res;
     }

    /*
    获得更多好书
     */

    @RequestMapping("/getMoreGoodBooks")
    @ResponseBody
    public List<T_BOOK> getmoregoodbooks(){
        List lists = restTemplate.getForObject(PREFIX + "/buyer/product/getmoreBooks", List.class);
        int len=lists.size();
        List<T_BOOK> books=new LinkedList<T_BOOK>();
        if(len==0){
            T_BOOK zhaozu =restTemplate.getForObject(PREFIX+"/findByBookId?id=999",T_BOOK.class);
            books.add(zhaozu);
        }else{
            for (Object list : lists) {
                T_BOOK t_book = JSONObject.parseObject(JSON.toJSONString(list), T_BOOK.class);
                books.add(t_book);
            }
        }
        return books;
    }


    /*
    渲染书籍页面
     */
    @RequestMapping("/shopDetail")
    public String getShopDetailAllBooks(@Param("shopid")String shopid,Model model){
        List books = restTemplate.getForObject(PREFIX + "buyer/product/getAllBooksByShopid?shopid=" + shopid, List.class);
        T_SHOP shop = restTemplate.getForObject(PREFIX + "/buyer/product/getShopByShopid?shopid=" + shopid, T_SHOP.class);
        List<T_BOOK> resbooks=new LinkedList<T_BOOK>();
        for (Object book : books) {
            T_BOOK t_book = JSONObject.parseObject(JSON.toJSONString(book), T_BOOK.class);
            resbooks.add(t_book);
        }
        model.addAttribute("allCurrentBooks",resbooks);
        model.addAttribute("currentShop",shop);
        return "shop_detail";
    }
    /*
    获取主栏位书籍
     */
    @RequestMapping("/getMainbooks")
    @ResponseBody
    public List<T_BOOK> getMainbooks(){
        List mainBooks = restTemplate.getForObject(PREFIX + "buyer/product/getMainbooks", List.class);
        int len=mainBooks.size();
            T_BOOK zhaozu =restTemplate.getForObject(PREFIX+"/findByBookId?id=999",T_BOOK.class);
        List<T_BOOK> books=new LinkedList<T_BOOK>();
        for (Object mainBook : mainBooks) {
            T_BOOK t_book = JSON.parseObject(JSON.toJSONString(mainBook), T_BOOK.class);
            books.add(t_book);
        }
        for(int i=0;i<3-len;i++){
            books.add(zhaozu);
        }
        return books;
    }

    /*
    获取好书推荐栏位
     */
    @RequestMapping("/getRecomendBooks")

    @ResponseBody
    public List<T_BOOK> getrecommendbooks(){
        List booksObj = restTemplate.getForObject(PREFIX + "buyer/product/getRecomendBooks", List.class);
        int len=booksObj.size();
        List<T_BOOK> res=new LinkedList<>();
        T_BOOK zhaozu =restTemplate.getForObject(PREFIX+"/findByBookId?id=999",T_BOOK.class);
        if(len==0){
            res.add(zhaozu);
        }
        for (Object o : booksObj) {
            T_BOOK t_book = JSON.parseObject(JSON.toJSONString(o), T_BOOK.class);
            res.add(t_book);
        }
        return res;

    }


    /*
    获取金牌书屋
     */
    @RequestMapping("/getGoldShops")
    @ResponseBody
    public List<T_SHOP> getGoldShops(){
        List shopObj = restTemplate.getForObject(PREFIX + "buyer/product/getGoldShops", List.class);
        int len=shopObj.size();
        List<T_SHOP> shops=new LinkedList<>();
        if(len==0){
            T_SHOP zhaozu = restTemplate.getForObject(PREFIX + "/buyer/product/getShopByShopid?shopid=999", T_SHOP.class);
            shops.add(zhaozu);
        }
        else{
            for (Object o : shopObj) {
                T_SHOP t_shop = JSON.parseObject(JSON.toJSONString(o), T_SHOP.class);
                shops.add(t_shop);
            }
        }
        return shops;
    }




    /*
    通过shopid返回shop实体
     */
    @RequestMapping("/getShopByShopid")
    @ResponseBody
    public T_SHOP getShopByShopid(@Param("shopid")String shopid){

     return    restTemplate.getForObject(PREFIX+"/buyer/product/getShopByShopid?shopid="+shopid,T_SHOP.class);
    }




    /*
    @param: id 为bookid，也同时为图片的id
     */
    @RequestMapping("/detail")
    public String detail(@Param("id")String id, Model model){
        T_BOOK book = restTemplate.getForObject(PREFIX + "/buyer/product/getBookByBookId?id=" + id, T_BOOK.class);
        T_SHOP shop = restTemplate.getForObject(PREFIX + "/buyer/product/getShopByShopid?shopid=" + book.getT_shop_id(), T_SHOP.class);


        model.addAttribute("book",book);
        model.addAttribute("mms",3);
        model.addAttribute("shopId",shop.getT_shop_id());
        return "book_detail";

    }

    /*
    搜索框，返回类似名字书籍
     */
    @RequestMapping("/searchBooks")
    @ResponseBody
    public String getSearchBooks(@Param("likename")String likename, HttpServletRequest request,HttpServletResponse response) throws IOException {
        List books = restTemplate.getForObject(PREFIX + "/buyer/product/searchBooks?likename=" + likename, List.class);
        JSONObject jsonObject=new JSONObject();
        if(books==null||books.size()==0){
            jsonObject.put("msg","未找到类似书名的书籍");
//            response.getWriter().write(jsonObject.toJSONString());
//            response.getWriter().close();
            return jsonObject.toJSONString();
//            return jsonObject.toJSONString();
        }
        List<T_BOOK> resBooks=new LinkedList<T_BOOK>();
        for (Object book : books) {
            T_BOOK t_book = JSONObject.parseObject(JSON.toJSONString(book), T_BOOK.class);
            resBooks.add(t_book);
        }
        request.getSession().setAttribute("likebooks",resBooks);
        jsonObject.put("likebooks",resBooks);

       return jsonObject.toJSONString();

    }
}
