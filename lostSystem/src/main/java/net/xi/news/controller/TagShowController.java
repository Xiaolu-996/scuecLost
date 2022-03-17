package net.xi.news.controller;


import net.xi.news.pojo.Tag;
import net.xi.news.service.NewsService;
import net.xi.news.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

	@Autowired
	private TagService tagService;
	@Autowired
	private NewsService newsService;

	@GetMapping("tags/{id}")
	//跳转到地点页
	public String tags(@PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC)
	                   @PathVariable Long id,
	                   Pageable pageable, Model model) {
		List<Tag> tagList = tagService.tagTop(1000);
		if (id == -1) {
			id = tagList.get(0).getId();
		}
		model.addAttribute("tags", tagList);
		model.addAttribute("page", newsService.listNewsByTag(id, pageable));
		model.addAttribute("active", id);
		return "tags";
	}
}
