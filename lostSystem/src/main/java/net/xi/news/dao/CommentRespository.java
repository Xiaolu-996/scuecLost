package net.xi.news.dao;

import net.xi.news.pojo.Comment;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import javax.transaction.Transactional;
import java.util.List;

public interface CommentRespository extends JpaRepository<Comment,Long> {

    //根据ID获取失物的评论
    List<Comment>  findByNewsIdAndParentCommentNull(Long newsId, Sort sort);

    //根据id删除评论
    @Transactional
    @Modifying
    @Query("DELETE from Comment as c where c.news.id = ?1")
    int deleteCommentsByNewsId(Long newsId);
}

