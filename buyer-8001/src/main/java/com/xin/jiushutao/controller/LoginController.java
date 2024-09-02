package com.xin.jiushutao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xin.jiushutao.pojo.AccessTokenDTO;
import com.xin.jiushutao.pojo.T_BOOK;
import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.service.GithubProvider;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/13 10:30
 * @Version 1.0
 **/

@Controller

public class LoginController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private  String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;
    @Autowired
   private GithubProvider githubProvider;

    private String PREFIX="http://localhost:9001"; //buyer的服务提供者，若是查询并发较大使用ribbon进行负载均衡
//    private String PREFIX="http://60.204.157.187:9001";


    @RequestMapping("/buyer/getBuyerLogin")
    @ResponseBody
    public String getBuyerLoginStatus(HttpServletResponse response, @RequestParam("phone")String phone){
        Map map=new HashMap();

        Integer status = restTemplate.getForObject(PREFIX + "/buyer/getBuyerLogin?phone=" + phone, Integer.class);
        map.put("status",status);


        return  JSONObject.toJSONString(map);
    }


    //github登录
    @RequestMapping(value = "/loginGithub")
    public String githubLogin(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String githubname=null;
        if(cookies!=null&&cookies.length>0)
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("githubName")){
                githubname=cookie.getValue();break;
            }
        }
        if(githubname!=null&&githubname!=""){
            T_BUYER gituser = restTemplate.getForObject(PREFIX + "/buyer/getUserBygithubname?githubname=" + githubname, T_BUYER.class);
            if(gituser!=null){
                Cookie userPhone=new Cookie("userPhone",gituser.getT_buyer_phone());
                userPhone.setPath("/");
                userPhone.setMaxAge(60*60*24*1000);
                response.addCookie(userPhone);
                Cookie userID=new Cookie("userID",gituser.getT_buyer_id());
                userPhone.setPath("/");
                userPhone.setMaxAge(60*60*24*1000);
                response.addCookie(userID);
                String buyerID=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuerIDByuserPhone?userPhone="+gituser.getT_buyer_phone(),String.class);


                //buyerID加入cookie中
                Cookie cookie=new Cookie("buyerID",buyerID);
                cookie.setMaxAge(24*3600*1000);
                cookie.setPath("/");
                response.addCookie(cookie);

                return "redirect:/";
            }

            return "redirect:registerGithub";

        }else{
            String state = UUID.randomUUID().toString().replace("-", "");
            return "redirect:https://github.com/login/oauth/authorize?client_id=411789560e005d724dd8&state="+state;
//            return "redirect:https://github.com/login/oauth/authorize?client_id=783f0312405613ecd0a7&state="+state;
        }

    }



    @GetMapping("/callback")
//    @ResponseBody
    public String getAccessToken(@RequestParam("code") String code, @Param("state")String state, HttpServletResponse response) {
        // 封装成AccessTokenDTO
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        // 获取access_token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        // 获取用户信息
//        GitHubUser gitHubUser = githubProvider.getGitHubUser(accessToken);
        String gitHubUser = githubProvider.getGitHubUser(accessToken);
//        model.addAttribute("user", gitHubUser);

        String[] splits = gitHubUser.split(",");
        String[] split = splits[0].split(":");
        String githubName=split[1];


        if(githubName.contains("Bad")){
            return "redirect:/";
        }


        Cookie cookie=new Cookie("githubName",githubName);
        cookie.setPath("/");
        cookie.setMaxAge(24*1000*3600);
        response.addCookie(cookie);
        //先搜索一下，如果有不用注册，否则再进行注册


        githubName=githubName.substring(1,githubName.length()-1);
        T_BUYER gituser = restTemplate.getForObject(PREFIX + "/buyer/getUserBygithubname?githubname=" + githubName, T_BUYER.class);
        if(gituser!=null){
            Cookie userPhone=new Cookie("userPhone",gituser.getT_buyer_phone());
            userPhone.setPath("/");
            userPhone.setMaxAge(60*60*24*1000);
            response.addCookie(userPhone);
            Cookie userID=new Cookie("userID",gituser.getT_buyer_id());
            userPhone.setPath("/");
            userPhone.setMaxAge(60*60*24*1000);
            response.addCookie(userID);

            String buyerID=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuerIDByuserPhone?userPhone="+gituser.getT_buyer_phone(),String.class);


            //buyerID加入cookie中
            Cookie cookie2=new Cookie("buyerID",buyerID);
            cookie.setMaxAge(24*3600*1000);
            cookie.setPath("/");
            response.addCookie(cookie2);

            return "redirect:/";
        }
        else{
            return "redirect:registerGithub";
        }


//        Cookie cookie=new Cookie("githubUser",gitHubUser.split());

//return gitHubUser;
//        return "githubRegister";
    }

    @RequestMapping("/registerGithub")
    public String githubRegister(HttpServletRequest request,Model model,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String githubName=null;
        if(cookies!=null)
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("githubName")){
                githubName=cookie.getValue();
            }
        }
        model.addAttribute("githubName",githubName);

        return "githubRegister";
    }



    @RequestMapping("/buyer/loginBefore")
    @ResponseBody
    public String loginBefore(HttpServletResponse response, HttpServletRequest request,Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){


        System.out.println("=============================");
        System.out.println("=============================");
        System.out.println("========20240902=====================");
        System.out.println("username=>"+username+",password==>"+password);
        Boolean isExistBuyer = restTemplate.getForObject(PREFIX+"/buyer/isExistBuyer?phone=" + username + "&password=" + password, Boolean.class);
        String id = restTemplate.getForObject(PREFIX + "/buyer/queryIdByPhone?phone=" + username, String.class);

        Cookie userPhone=new Cookie("userPhone",username);
        userPhone.setPath("/");
        userPhone.setMaxAge(60*60*24*1000);
        response.addCookie(userPhone);

        Cookie userID=new Cookie("userID",id);
        userID.setPath("/");
        userID.setMaxAge(60*60*24*1000);
        response.addCookie(userID);

//        String buyerID=restTemplate.getForObject("http://60.204.157.187:9001/buyer/personal/getBuerIDByuserPhone?userPhone="+username,String.class);
        String buyerID=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuerIDByuserPhone?userPhone="+username,String.class);


        //buyerID加入cookie中
        Cookie cookie=new Cookie("buyerID",buyerID);
        cookie.setMaxAge(24*3600*1000);
        cookie.setPath("/");
        response.addCookie(cookie);

        Map map=new HashMap();
        map.put("isExistBuyer",isExistBuyer);
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser!=null){
            map.put("loginUser",username);
            map.put("id",id);

            return JSONObject.toJSONString(map);
        }
        if(isExistBuyer){//是合法用户,放行
              /*
        2022/1/3新增代码，数据库表buyer添加字段t_buyer_status用于判断用户登录状态
         */
        //修改用户状态为1
          restTemplate.getForObject(PREFIX+"/buyer/setBuyerLogin?BuyerLoginStatus=1&phone="+username,Integer.class);
            map.put("status",1);

            map.put("loginUser",username);
            map.put("id",id);
            session.setAttribute("loginUser",username);
            model.addAttribute("loginUser",username);
        }else{
            map.put("msg","用户名或者密码错误");
        }

        return JSONObject.toJSONString(map);
    }



    @RequestMapping("/buyer/loginout")
    @ResponseBody
    public String logout(@RequestParam("phone")String phone, HttpSession session,HttpServletRequest request,HttpServletResponse response){

        session.removeAttribute("loginUser");
        restTemplate.getForObject(PREFIX+"/buyer/setBuyerLogin?BuyerLoginStatus=0&&phone="+phone,Integer.class);

        Cookie cookie=new Cookie("userPhone",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        Cookie userID=new Cookie("userID",null);
        userID.setPath("/");
        userID.setMaxAge(0);
        response.addCookie(userID);

        return "/index.html";
    }

    /*
    个人设置界面需要，
    通过usename获取tbuyer对象
     */
    @RequestMapping("/buyer/queryByBuyerName")
    @ResponseBody
    public String queryByBuyerName(@Param("name")String name,HttpServletResponse response) throws IOException {
        String t_buyer_id = restTemplate.getForObject(PREFIX + "/buyer/queryIdByPhone?phone=" + name, String.class);
        T_BUYER t_buyer = restTemplate.getForObject(PREFIX + "/buyer/personal/getBuyerByBuyerId?id=" + t_buyer_id, T_BUYER.class);

       return JSONObject.toJSONString(t_buyer);
    }


    /*
    用于前端页面中登陆状态的控制
     */
    @RequestMapping("/buyer/getUsername")
    public void accessLogin(HttpServletResponse response, HttpServletRequest request,Model model)throws Exception{

        List forObject = restTemplate.getForObject(PREFIX + "/buyer/product/allBooks", List.class);
        List<T_BOOK> books=new LinkedList<T_BOOK>();
        for (Object o : forObject) {
            T_BOOK t_book = JSON.parseObject(JSON.toJSONString(o), T_BOOK.class);
            books.add(t_book);
        }
        HttpSession session=request.getSession();
        session.setAttribute("books",books);

        String loginUser= null;

        Cookie[] cookies=request.getCookies();
        if(cookies!=null)
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userPhone")){
                loginUser=cookie.getValue();
            }
        }

        if(loginUser!=null&&loginUser!=""){

            String buyerID=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuerIDByuserPhone?userPhone="+loginUser,String.class);


            //buyerID加入cookie中
            Cookie cookie=new Cookie("buyerID",buyerID);
            cookie.setMaxAge(24*3600*1000);
            cookie.setPath("/");
            response.addCookie(cookie);
            //正常业务代码

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("loginUser",loginUser);
            response.getWriter().write(jsonObject.toJSONString());
            response.getWriter().close();
        }else{
            model.addAttribute("msg","未注册用户或为用户名/密码错误");
        }


    }


    /*
    判断是否为合法用户
     */
    @RequestMapping("/buyer/isRightUser")
    @ResponseBody
    public boolean isRegister(@Param("phone") String phone,@Param("password")String password){
        Boolean isUserBoolean = restTemplate.getForObject(PREFIX + "buyer/isExistBuyer?phone=" + phone + "&&password=" + password, Boolean.class);
        return isUserBoolean;
    }

    /*
    用于模块 个人订单修改密码部分
     */
    @RequestMapping("/buyer/savePass")
    @ResponseBody
    public String savePass(@Param("oldPass")String oldPass,@Param("phone")String phone,@Param("newPass")String newPass){
       JSONObject jsonObject=new JSONObject();
        Boolean isRightUser = restTemplate.getForObject(PREFIX + "/buyer/isExistBuyer?phone=" + phone + "&password=" + oldPass, Boolean.class);
        if(isRightUser==false){
            jsonObject.put("msg","原密码错误");
            return jsonObject.toJSONString();
        }
        String t_buyer_id=restTemplate.getForObject(PREFIX+"/buyer/queryIdByPhone?phone="+phone,String.class);
        T_BUYER t_buyer=restTemplate.getForObject(PREFIX+"/buyer/personal/getBuyerByBuyerId?id="+t_buyer_id,T_BUYER.class);
        t_buyer.setT_buyer_password(newPass);
        Boolean aBoolean = restTemplate.postForObject(PREFIX + "/buyer/personal/save", t_buyer, Boolean.class);
        jsonObject.put("msg","修改成功");
        return jsonObject.toJSONString();
    }



}
