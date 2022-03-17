package net.xi.news.controller.admin;


import net.xi.news.pojo.Tag;
import net.xi.news.pojo.Type;
import net.xi.news.service.TagService;
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
public class TagController {

	@Autowired
	private TagService tagService;

	@GetMapping("/tags")
	//跳转到标签管理页
	public String tags(@PageableDefault(size = 5,
			sort = {"id"},
			direction = Sort.Direction.DESC)
			                   Pageable pageable,
	                   Model model) {
		model.addAttribute("page", tagService.listTag(pageable));
		return "admin/tags";
	}


	@GetMapping("/tags/input")
	//跳转到标签输入页
	public String input(Model model) {
		model.addAttribute("tag", new Tag());
		model.addAttribute("msg", "地点添加");
		return "admin/tagsinput";
	}

	@GetMapping("/tags/{id}/input")
	//跳转到标签编辑页
	public String editinput(@PathVariable Long id, Model model) {
		model.addAttribute("tag", tagService.getTag(id));
		model.addAttribute("msg", "地点修改");
		return "admin/tagsinput";
	}


	@PostMapping("/tags")
	//提交标签新增
	public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
		Tag t1 = tagService.getTagByName(tag.getName());
		if (t1 != null) {
			result.rejectValue("name", "nameError", "不能重复添加分类!");
		}


		if (result.hasErrors()) {
			return "admin/tagsinput";
		}

		Tag t2 = tagService.saveTag(tag);
		if (t2 == null) {
			attributes.addFlashAttribute("msg", "添加失败！");
		} else {
			attributes.addFlashAttribute("msg", "添加成功！");
		}
		return "redirect:/admin/tags";
	}

	@PostMapping("/tags/{id}")
	//提交标签修改
	public String editpost(@Valid Tag tag, BindingResult result,
	                       @PathVariable Long id,
	                       RedirectAttributes attributes) {
		Tag t1 = tagService.getTagByName(tag.getName());
		if (t1 != null) {
			result.rejectValue("name", "nameError", "你还没修改呢！");
		}


		if (result.hasErrors()) {
			return "admin/tagsinput";
		}

		Tag t2 = tagService.updateTag(id, tag);
		if (t2 == null) {
			attributes.addFlashAttribute("msg", "更改失败！");
		} else {
			attributes.addFlashAttribute("msg", "更改成功！");
		}
		return "redirect:/admin/tags";
	}

	@GetMapping("tags/{id}/delete")
	//删除标签
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {

		tagService.deleteTag(id);
		attributes.addFlashAttribute("msg", "删除成功！");
		return "redirect:/admin/tags";
	}


}
