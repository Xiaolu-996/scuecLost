package net.xi.news.controller;


import net.xi.news.service.NewsService;
import net.xi.news.service.TagService;
import net.xi.news.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    //跳转到首页
    public String index(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model) {
        model.addAttribute("page",newsService.listNews(pageable));
        model.addAttribute("types",typeService.typeTop(5));
        model.addAttribute("tags",tagService.tagTop(5));
        model.addAttribute("newsrecommend",newsService.newsTop(5));
        System.out.println("index");
        return "index";
    }

    @PostMapping("/search")
    //跳转到搜索结果页
    public String Search(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                 Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page",newsService.listsearchNews("%"+query+"%",pageable));
        model.addAttribute("querys",query);
        return "search";

    }



    @GetMapping("/news/{id}")
    //跳转到loss详情页
    public String news(@PathVariable Long id,Model model){
        model.addAttribute("news",newsService.getConvert(id));
        return "news";
    }


    @GetMapping("/about")
    //跳转到关于页
    public String about(){
        return "about";
    }


}