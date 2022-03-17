package net.xi.news.dao;

import net.xi.news.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
