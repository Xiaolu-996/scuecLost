package net.xi.news.controller;


import net.xi.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class holeController {

	@Autowired
	NewsService newsService;

	@GetMapping("/hole")
	//跳转到根据月份分类的全部新闻页
	public String hole(Model model) {
		model.addAttribute("holemap", newsService.holeNews());
		model.addAttribute("newscount", newsService.countNews());
		return "hole";
	}
}
