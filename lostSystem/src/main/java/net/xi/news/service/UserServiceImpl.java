package net.xi.news.service;

import net.xi.news.dao.UserRespository;
import net.xi.news.pojo.User;
import net.xi.news.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRespository userRespository;

    @Override
    //查找用户实现匹配
    public User checkUser(String username, String password) {
        User user = userRespository.findByUsernameAndPassword(username,MD5Utils.code(password));
        return user;
    }
}
