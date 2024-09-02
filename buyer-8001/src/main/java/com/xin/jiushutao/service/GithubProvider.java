package com.xin.jiushutao.service;

import com.xin.jiushutao.pojo.AccessTokenDTO;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2022/2/11 17:09
 * @Version 1.0
 **/
public interface GithubProvider {
    public String getGitHubUser(String accessToken);
    public String getAccessToken(AccessTokenDTO accessTokenDTO);
}
