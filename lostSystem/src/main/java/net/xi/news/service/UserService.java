package net.xi.news.service;

import net.xi.news.pojo.User;

public interface UserService {

    //查找用户实现匹配
    User checkUser(String username,String password);


}
