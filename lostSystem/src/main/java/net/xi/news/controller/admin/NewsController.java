package net.xi.news.controller.admin;


import net.xi.news.pojo.News;
import net.xi.news.pojo.NewsQuery;
import net.xi.news.pojo.User;
import net.xi.news.service.CommentService;
import net.xi.news.service.NewsService;
import net.xi.news.service.TagService;
import net.xi.news.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class NewsController {
	private static final String INPUT = "admin/newsinput";
	private static final String LIST = "admin/newsmanage";
	private static final String REDIRECT_LIST = "redirect:/admin/news";


	@Autowired
	private NewsService newsService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private TagService tagService;
	@Autowired
	private CommentService commentService;


	@GetMapping("/news")
	//跳转到新闻管理页
	private String news(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Sort.Direction.DESC)
			                    Pageable pageable, NewsQuery news, Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("page", newsService.listNews(pageable, news));
		return LIST;
	}

	@PostMapping("/news/search")
	//搜索结果页面（在新闻管理页显示）
	private String search(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Sort.Direction.DESC)
			                      Pageable pageable, NewsQuery news, Model model) {
		model.addAttribute("page", newsService.listNews(pageable, news));
		return "admin/newsmanage :: newsList";
	}

	//给前端设置分类和标签
	private void setTypeAndTag(Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("tags", tagService.getAllTag());
	}

	@GetMapping("/news/input")
	//跳转到新闻新增页
	public String input(Model model) {
		setTypeAndTag(model);
		model.addAttribute("news", new News());
		return INPUT;
	}

	@GetMapping("/news/{id}/input")
	//跳转到新闻编辑页
	public String editinput(@PathVariable Long id, Model model) {

		News news = newsService.getNews(id);
		news.init();
		setTypeAndTag(model);
		model.addAttribute("news", newsService.getNews(id));
		return INPUT;
	}

	@PostMapping("/news")
	//发布新闻
	public String post(News news, RedirectAttributes attributes, HttpSession session) {

		news.setUser((User) session.getAttribute("user"));
		news.setType(typeService.getType(news.getType().getId()));
		news.setTags(tagService.ListTag(news.getTagIds()));
		News n;
		if (news.getId() == null) {
			n = newsService.saveNews(news);
		} else {
			n = newsService.updateNews(news.getId(), news);
		}

		if (n == null) {
			attributes.addFlashAttribute("msg", "操作失败！");
		} else {
			attributes.addFlashAttribute("msg", "操作成功！");
		}

		return REDIRECT_LIST;
	}

	@GetMapping("news/{id}/delete")
	//删除新闻
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		commentService.deleteCommentsByNewsId(id);
		newsService.deleteNews(id);
		attributes.addFlashAttribute("msg", "删除成功");
		return REDIRECT_LIST;

	}


}
