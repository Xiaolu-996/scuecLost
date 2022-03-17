package net.xi.news.controller.admin;


import net.xi.news.pojo.Type;
import net.xi.news.service.NewsService;
import net.xi.news.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

	@Autowired
	private TypeService typeService;
	@Autowired
	private NewsService newsService;

	@GetMapping("/types")
	//跳转到分类管理页
	public String types(@PageableDefault(size = 5,
			sort = {"id"},
			direction = Sort.Direction.DESC)
			                    Pageable pageable,
	                    Model model) {
		model.addAttribute("page", typeService.listType(pageable));
		return "admin/types";
	}

	@GetMapping("/types/input")
	//跳转到分类添加页
	public String input(Model model) {
		model.addAttribute("type", new Type());
		model.addAttribute("msg", "分类添加");
		return "admin/typesinput";

	}


	@GetMapping("/types/{id}/input")
	//跳转到分类修改页
	public String editInput(@PathVariable Long id, Model model) {
		model.addAttribute("type", typeService.getType(id));
		model.addAttribute("msg", "分类修改");
		return "admin/typesinput";
	}


	@PostMapping("/types")
	//提交分类添加
	public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
		Type t1 = typeService.getTypeByName(type.getName());
		if (t1 != null) {
			result.rejectValue("name", "nameError", "不能重复添加分类!");
		}


		if (result.hasErrors()) {
			return "admin/typesinput";
		}

		Type t2 = typeService.saveType(type);
		if (t2 == null) {
			attributes.addFlashAttribute("msg", "添加失败！");
		} else {
			attributes.addFlashAttribute("msg", "添加成功！");
		}
		return "redirect:/admin/types";
	}


	@PostMapping("/types/{id}")
	//提交分类修改
	public String editpost(@Valid Type type, BindingResult result,
	                       @PathVariable Long id, RedirectAttributes attributes) {
		Type t1 = typeService.getTypeByName(type.getName());
		if (t1 != null) {
			result.rejectValue("name", "nameError", "你还没有修改!");
		}


		if (result.hasErrors()) {
			return "admin/typesinput";
		}

		Type t2 = typeService.updateType(id, type);
		if (t2 == null) {
			attributes.addFlashAttribute("msg", "更改失败！");
		} else {
			attributes.addFlashAttribute("msg", "更改成功！");
		}
		return "redirect:/admin/types";
	}

	@GetMapping("/types/{id}/delete")
	//删除分类
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		newsService.deleteNewsByTypeId(id);
		typeService.deleteType(id);
		attributes.addFlashAttribute("msg", "删除成功！");
		return "redirect:/admin/types";
	}
}
