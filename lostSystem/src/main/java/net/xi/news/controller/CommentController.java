package net.xi.news.controller;


import net.xi.news.pojo.Comment;
import net.xi.news.service.CommentService;
import net.xi.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private NewsService newsService;

	@Value("${comment.avatar}")
	private String avatar;

	@RequestMapping("/comments/{newsId}")
	//显示相应新闻的相应评论
	public String comments(@PathVariable Long newsId, Model model) {
		System.out.println("新闻ID: " + newsId);
		model.addAttribute("comments", commentService.listCommentByNewsId(newsId));
		return "news::commentList";
	}

	@PostMapping("/comments")
	//发布评论
	public String post(Comment comment) {
		Long newsId = comment.getNews().getId();
		comment.setNews(newsService.getNews(newsId));
		comment.setAvatar(avatar);
		commentService.saveComment(comment);
		return "redirect:/news/" + newsId;
	}
}
