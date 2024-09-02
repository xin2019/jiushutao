package com.xin.jiushutao.service;

import com.alibaba.fastjson.JSON;
import com.xin.jiushutao.pojo.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2022/2/11 17:12
 * @Version 1.0
 **/
@Service
public class GithubProviderImpl implements GithubProvider {
    @Override
    public String getGitHubUser(String accessToken) {

        OkHttpClient client = new OkHttpClient();
        // 以拼接字符串的形式，把token作为参数去Get请求url
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
//                .url("https://api.github.com/user?access_token=" + accessToken)

                .build();
        try {
            // 封装返回的用户信息
            Response response = client.newCall(request).execute();
            String string = response.body().string();
//            GithubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        // json格式的AccessTokenDTO作为请求体
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            // 从返回结果中提取access_token
            String string = response.body().string();
            String AccessToken = string.split("&")[0].split("=")[1];
            return AccessToken;
        } catch (IOException e) {
        }
        return null;
    }
}
