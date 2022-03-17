package net.xi.news.controller;


import net.xi.news.pojo.NewsQuery;
import net.xi.news.pojo.Type;
import net.xi.news.service.NewsService;
import net.xi.news.service.TypeService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private NewsService newsService;

    @GetMapping("types/{id}")
    //跳转到分类页
    public String types(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    @PathVariable Long id,
                                    Pageable pageable, Model model) {
        List<Type> typeList = typeService.typeTop(1000);
        if (id==-1) {
            id = typeList.get(0).getId();
        }
        NewsQuery newsQuery = new NewsQuery();
        newsQuery.setTypeId(id);
        model.addAttribute("types",typeList);
        model.addAttribute("page",newsService.listNews(pageable,newsQuery));
        model.addAttribute("active",id);
        return "classify";
    }






}
