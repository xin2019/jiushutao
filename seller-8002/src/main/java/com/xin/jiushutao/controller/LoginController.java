package com.xin.jiushutao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xin.jiushutao.pojo.*;
import com.xin.jiushutao.proxy.seller.BookAndBuyer;
import com.xin.jiushutao.proxy.seller.SellerAndShop;
import com.xin.jiushutao.utils.FileUtils;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/3/4 15:07
 * @Version 1.0
 **/
@Controller
@RequestMapping("/seller")
public class LoginController {
    @Autowired
    private RestTemplate restTemplate;
    private String PREFIX = "http://localhost:9002";

    private static int bookNum = 1;


    /*
     注册商家
     @Param name，phone,password
      */
    @RequestMapping("/register")
    @ResponseBody
    public Boolean register(HttpServletRequest request) {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        T_SELLER t_seller = new T_SELLER();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmssyyyyMMdd");
        String sellerid = simpleDateFormat.format(new Date());
        System.out.println("8002----/seller/register");
        System.out.println(sellerid);
        t_seller.setT_seller_id(sellerid);
        t_seller.setT_seller_name(name);
        t_seller.setT_seller_phone(phone);
        t_seller.setT_seller_password(password);
        t_seller.setT_shop_id(sellerid);
        System.out.println("8002-------------------------register");
        System.out.println(t_seller);
        //新增默认shop记录
        T_SHOP t_shop = new T_SHOP();
        t_shop.setT_seller_id(sellerid);
        t_shop.setT_shop_id(sellerid);
        t_shop.setT_shop_name("商店" + sellerid);
        t_shop.setT_shop_priority("0");
        t_shop.setT_shop_photo("http://localhost:8001/images/brand_gaitubao_160x100.jpg");
        System.out.println("register-9002==========dddddddddddd->");
        System.out.println(t_shop);
        Boolean shopBool = restTemplate.postForObject(PREFIX + "/seller/addNewShop", t_shop, Boolean.class);

        Boolean sellerBool = restTemplate.postForObject(PREFIX + "/seller/addNewSeller", t_seller, Boolean.class);
        return shopBool & sellerBool;
    }


    /*
    登陆
     */
    @RequestMapping("/login")
//    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        System.out.println("login----8002---");
        System.out.println("login==password===" + password);
        System.out.println("login==phone===" + phone);
        String seller = (String) request.getSession().getAttribute("sellerPhone");
        Boolean isRightUser = restTemplate.getForObject(PREFIX + "/seller/isRightSeller?phone=" + phone + "&password=" + password, Boolean.class);

        if (isRightUser == true || seller != null) {

            request.getSession().setAttribute("sellerPhone", phone);
            return "redirect:main";
        } else {
            return "login";
        }

    }

    @RequestMapping("/main")
    public String getMain(HttpSession session) {
        String phone = (String) session.getAttribute("sellerPhone");
        System.out.println("login----main:::sellerphone=>" + phone);
        return "redirect:/seller/enterAdmin?phone=" + phone;
    }

//    public String login(HttpServletRequest request, HttpServletResponse response) {
//        String phone = request.getParameter("phone");
//        String password = request.getParameter("password");
//        System.out.println("login----8002---");
//        System.out.println(password);
//        System.out.println(phone);
//        String seller = (String) request.getSession().getAttribute("sellerPhone");
//        JSONObject jsonObject = new JSONObject();
//        if (seller == null) {
//            Boolean isRightUser = restTemplate.getForObject(PREFIX + "/seller/isRightSeller?phone=" + phone + "&password=" + password, Boolean.class);
//            if (isRightUser == true) {
//
//                Cookie cookie=new Cookie("sellerPhone",phone);
//                // 设置cookie的有效时间
//                cookie.setMaxAge(24*60*60); // 24小时，可读性好
//                // 一般不主动设置Cookie的domain，因为可能导致被浏览器拒绝
//                // 设置Cookie的路径
//                cookie.setPath("/");
//                // 发送cookie
//                response.addCookie(cookie);
//
//                request.getSession().setAttribute("sellerPhone", phone);
//                jsonObject.put("sellerPhone", phone);
//                return jsonObject.toJSONString();
//            }
//            return jsonObject.toJSONString();
//        }
//
//        Cookie cookie=new Cookie("sellerPhone",phone);
//        // 设置cookie的有效时间
//        cookie.setMaxAge(24*60*60); // 24小时，可读性好
//        // 一般不主动设置Cookie的domain，因为可能导致被浏览器拒绝
//        // 设置Cookie的路径
//        cookie.setPath("/");
//        // 发送cookie
//        response.addCookie(cookie);
//        request.getSession().setAttribute("sellerPhone", phone);
//
//        jsonObject.put("sellerPhone", phone);
//
//        return jsonObject.toJSONString();
//    }

    /*
    进入用户注册界面
     */
    @RequestMapping("/enterRegister")
    public String enterRegister() {
        return "register";
    }

    /*
    进入后台界面, 根据卖家手机获得卖家，书籍和商店信息
     */
    @RequestMapping("/enterAdmin")
    public String enterAdmin(HttpServletRequest request, Model model, HttpSession session) {

        String phone = request.getParameter("phone");
        System.out.println("phone======>" + phone);
        T_SELLER tseller = restTemplate.getForObject(PREFIX + "/seller/getSeller?phone=" + phone, T_SELLER.class);
        SellerAndShop sellerAndShop = new SellerAndShop();
        sellerAndShop.setT_seller(tseller);
        System.out.println("seller========>" + tseller);
        T_SHOP t_shop = restTemplate.getForObject(PREFIX + "/seller/getShop?shopid=" + tseller.getT_shop_id(), T_SHOP.class);
        request.getSession().setAttribute("shopid", tseller.getT_shop_id());
        List books = restTemplate.getForObject(PREFIX + "/seller/getBooks?shopid=" + tseller.getT_shop_id(), List.class);
        List<T_BOOK> resBooks = new LinkedList<T_BOOK>();
        for (Object book : books) {

            T_BOOK t_book = JSONObject.parseObject(JSON.toJSONString(book), T_BOOK.class);

            resBooks.add(t_book);
        }


        sellerAndShop.setT_books(resBooks);
        sellerAndShop.setT_shop(t_shop);
        System.out.println(sellerAndShop);
        model.addAttribute("seller", sellerAndShop);
        return "index";
    }

    /*
    根据bookid和status更改book的状态
     */

    @RequestMapping("/updateStatusByBookidAndStatus")
    @ResponseBody
    public int updateStatusByBookidAndStatus(@Param("bookid") String bookid, @Param("status") int status) {
        return restTemplate.getForObject(PREFIX + "/seller/updateStatusByBookidAndStatus?bookid=" + bookid + "&status=2", int.class);
    }

    /*
    根据shopid和shopname修改shopname
     */
    @RequestMapping("/updateShopnameByShopid")
    @ResponseBody
    public Boolean updateShopnameByShopid(@Param("shopid") String shopid, @Param("shopname") String shopname) {
        Boolean update = restTemplate.getForObject(PREFIX + "/seller/updateShopnameByShopid?shopid=" + shopid + "&shopname=" + shopname, Boolean.class);
        return update;
    }

    /*
    通过bookid下架book
     */
    @RequestMapping("/rackBookByBookid")
    @ResponseBody
    public Boolean rackByBookid(@Param("bookid") String bookid) {
        return restTemplate.getForObject(PREFIX + "seller/rackBookByBookid?bookid=" + bookid, Boolean.class);
    }

    /*
    图片上传
     */
    @RequestMapping("/multipleImageUpload")
    @ResponseBody
    public List multipleImageUpload(@RequestParam("uploadFiles") MultipartFile[] files, HttpServletRequest request) {
        System.out.println("上传的图片数：" + files.length);
        request.getSession().setAttribute("fileLength", files.length);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hhmmssyyyyMMdd");
        String bookid = simpleDateFormat.format(new Date());
        System.out.println("上传图片时候生成的bookid===》" + bookid);
        request.getSession().setAttribute("bookid", bookid);
        String shopid = request.getParameter("shopid");

        System.out.println("shopid----->" + shopid);
        List<Map<String, Object>> root = new ArrayList<Map<String, Object>>();

        int i = 1;
        for (MultipartFile file : files) {    //循环保存文件

            Map<String, Object> result = new HashMap<String, Object>();//一个文件上传的结果
            String result_msg = "";//上传结果信息

            if (file.getSize() / 1000 > 2000) {
                result_msg = "图片大小不能超过2mB";
            } else {
                //判断上传文件格式
                String fileType = file.getContentType();
                if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                    // 要上传的目标文件存放的绝对路径
                    final String localPath = "D:\\STUDY\\bishe\\version14\\jiushutao\\buyer-8001\\src\\main\\resources\\static\\upload\\" + shopid + "\\" + bookid;
                    //重新生成文件名
                    String fileName;
                    fileName = i + ".jpg";
                    if (FileUtils.upload(file, localPath, fileName)) {
                        //文件存放的相对路径(一般存放在数据库用于img标签的src)
//                        String relativePath="img/"+fileName;
                        String relativePath = shopid+"/"+bookid+"/" + fileName;
                        result.put("relativePath", relativePath);//前端根据是否存在该字段来判断上传是否成功
                        result_msg = "图片上传成功8001";
                    } else {
                        result_msg = "图片上传失败8001";
                    }
                } else {
                    result_msg = "图片格式不正确";
                }
            }
            result.put("result_msg", result_msg);
            root.add(result);
            i++;
        }

        //上传到8002
//
//        i = 1;
//        for (MultipartFile file : files) {    //循环保存文件
//
//            Map<String, Object> result = new HashMap<String, Object>();//一个文件上传的结果
//            String result_msg = "";//上传结果信息
//
//            if (file.getSize() / 1000 > 2000) {
//                result_msg = "图片大小不能超过2mB";
//            } else {
//                //判断上传文件格式
//                String fileType = file.getContentType();
//                if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
//                    // 要上传的目标文件存放的绝对路径
//                    final String localPath8002 = "D:\\STUDY\\bishe\\version14\\jiushutao\\seller-8002\\src\\main\\resources\\static\\upload\\" + shopid + "\\" + bookid;
//                    String fileName;
//                    fileName = i + ".jpg";
//                    if (FileUtils.upload(file, localPath8002, fileName)) {
//                        //文件存放的相对路径(一般存放在数据库用于img标签的src)
////                        String relativePath="img/"+fileName;
//                        String relativePath = shopid+"/"+bookid+"/" + fileName;
//                        result.put("relativePath", relativePath);//前端根据是否存在该字段来判断上传是否成功
//                        result_msg += "图片上传成功8002";
//                    } else {
//                        result_msg += "图片上传失败8002";
//                    }
//
//                } else {
//                    result_msg += "8002图片格式不正确";
//                }
//            }
//            result.put("result_msg", result_msg);
//            root.add(result);
//            i++;
//        }

        String root_json = JSON.toJSONString(root);
        System.out.println(root_json);
        return root;
    }

    /*
    上架新的书籍
     */

    @RequestMapping("/addNewBook")
//    @ResponseBody
    public String test(HttpServletRequest request) {
        String bookname = request.getParameter("bookname");
        String originprice = request.getParameter("originprice");
        double originpricedouble = Double.parseDouble(originprice);
        String nowprice = request.getParameter("nowprice");
        double nowpricedouble = Double.parseDouble(nowprice);
        String press = request.getParameter("press");
        String desc = request.getParameter("desc");
        String category = request.getParameter("category");
        String shopid = request.getParameter("shopid");
        char categoryChar = category.charAt(0);
        Integer fileLength = (Integer) request.getSession().getAttribute("fileLength");
        System.out.println("filename===>" + fileLength);
        T_BOOK t_book = new T_BOOK();
        t_book.setT_shop_id(shopid);
        t_book.setT_book_photo_nums(fileLength);
        String bookid = (String) request.getSession().getAttribute("bookid");
        t_book.setT_book_id(bookid);
        t_book.setT_book_category(categoryChar);
        t_book.setT_book_desc(desc);
        t_book.setT_book_is_secondhand(true);
        t_book.setT_book_origin_price(originpricedouble);
        t_book.setT_book_press(press);
        t_book.setT_book_now_price(nowpricedouble);
        t_book.setT_book_name(bookname);
        t_book.setT_book_photo("/upload/" + t_book.getT_shop_id() + "/" +bookid);
        System.out.println(t_book);
        Boolean addBool = restTemplate.postForObject(PREFIX + "/seller/addNewBook", t_book, Boolean.class);

        return "redirect:/seller/enterAdmin?phone=" + request.getParameter("phone");
    }


    /*
    卖家安全退出
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if(cookie.getName().equals("sellerPhone")){
//                    cookie.setMaxAge(0);
//                    cookie.setValue("");
//break;
//            }
//        }
        request.getSession().removeAttribute("sellerPhone");
        request.getSession().invalidate();
        return "redirect:/";
    }

    /*
         查看已被购买书籍
          */
    @RequestMapping("/getAllBooksPurchase")
    @ResponseBody
    public List<T_BOOK> getAllBooksPurchase(@Param("sellerid") String sellerid) {
        List forObject = restTemplate.getForObject(PREFIX + "/seller/getAllBooksPurchase?sellerid=" + sellerid + "&status=1", List.class);
        List<T_BOOK> res = new LinkedList<T_BOOK>();
        for (Object o : forObject) {
            T_BOOK t_book = JSONObject.parseObject(JSON.toJSONString(o), T_BOOK.class);
            res.add(t_book);


        }
        return res;
    }


    /*
    查看书籍详情
     */
    @RequestMapping("/getBookAndBuyer")
    @ResponseBody
    public BookAndBuyer getBooksandBuyers(@Param("bookid") String bookid) {
        System.out.println("getBookAndBuyer==========");
        System.out.println("bookid==>" + bookid);

        T_BOOK forObject = restTemplate.getForObject("http://localhost:9001/" + "findByBookId?id=" + bookid, T_BOOK.class);
        System.out.println("book--------");
        System.out.println(forObject);
        BookAndBuyer bookAndShop = new BookAndBuyer();
        bookAndShop.setT_book(forObject);
        T_ORDER forObject1 = restTemplate.getForObject(PREFIX + "/seller/getOrderByBookid?bookid=" + bookid, T_ORDER.class);
        T_BUYER forObject2 = null;
        if (forObject1 != null) {
            forObject2 = restTemplate.getForObject("http://localhost:9001/buyer/personal/getBuyerByBuyerId?id=" + forObject1.getT_buyer_id(), T_BUYER.class);
        }
        System.out.println("order--------");
        System.out.println(forObject1);

        System.out.println("buyer--------");
        System.out.println(forObject2);
        bookAndShop.setT_buyer(forObject2);
        bookAndShop.setDate(forObject1.getT_order_date());
        return bookAndShop;
    }


    /*
       查看已被发货书籍
        */
    @RequestMapping("/getAllBooksDelegated")
    @ResponseBody
    public List<T_BOOK> getAllBooksDelegated(@Param("sellerid") String sellerid) {
        System.out.println("getAllBooksDelegated====");
        System.out.println(sellerid);
        List forObject = restTemplate.getForObject(PREFIX + "/seller/getAllBooksPurchase?sellerid=" + sellerid + "&status=2", List.class);
        List<T_BOOK> res = new LinkedList<T_BOOK>();
        for (Object o : forObject) {
            T_BOOK t_book = JSONObject.parseObject(JSON.toJSONString(o), T_BOOK.class);
            res.add(t_book);


        }
        return res;
    }

    /*
    商铺封面图片上传
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
        JSONObject jsonObject = new JSONObject();
//        String shopid=(String) request.getSession().getAttribute("shopid");
        String shopid = request.getParameter("shopid");
        System.out.println("fileupload--->shopid==>" + shopid);
        System.out.println("文件--》" + shopid);
        if (file.isEmpty()) {
            System.out.println("文件为空");
            jsonObject.put("msg", "文件为空");
            model.addAttribute("msg", "文件为空");
            return jsonObject.toJSONString();
        }
        String filePath = "D:\\STUDY\\bishe\\version02\\jiushutao\\buyer-8001\\src\\main\\resources\\static\\upload\\second_shops\\" + shopid + "\\";
        String filename = "\\1.jpg";
        File dest = new File(filePath + filename);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            System.out.println(e);
            jsonObject.put("msg", "上传失败");
            model.addAttribute("msg", "上传失败");
            return jsonObject.toJSONString();
        }
        jsonObject.put("url", "http://localhost:8001/upload/second_shops/" + shopid + "/" + bookNum + "/1.jpg");
        return jsonObject.toJSONString();
//        model.addAttribute("url","http://localhost:8001/upload/second_shops/"+shopid+"/1.jpg");
//        return "index";
    }

    @RequestMapping("/sendBookid")
    @ResponseBody
    public int sendbookid(@Param("bookid") String bookid) {
        Integer status = restTemplate.getForObject("http://localhost:9003/admin/receiveBookid?bookid=" + bookid, int.class);
        return status;
    }

    @RequestMapping("/sendShopid")
    @ResponseBody
    public int sendshopid(@Param("shopid") String shopid) {
        Integer status = restTemplate.getForObject("http://localhost:9003/admin/receiveShopid?shopid=" + shopid, int.class);
        return status;
    }

}
