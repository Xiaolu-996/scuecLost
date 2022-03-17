package net.xi.news.service;

import net.xi.news.pojo.Comment;

import java.util.List;

public interface CommentService {

    //根据ID获取失物的评论
    List<Comment> listCommentByNewsId(Long newsid);

    //保存评论
    Comment saveComment(Comment comment);

    //删除评论
    int deleteCommentsByNewsId(Long newsId);
}
