package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.security.Auth;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.CategoryService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.service.PostService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}"})
	public String index(
		@PathVariable("id") String id,
		@PathVariable(value="categoryNo") Optional<Long> categoryNo,
		@PathVariable(value="postNo") Optional<Long> postNo,
		Model model) {
		List<CategoryVo> categoryList = categoryService.getCategoryList(id);
		if (categoryNo.isEmpty()) {
			categoryNo = Optional.of(categoryList.get(0).getNo());
		}
		List<PostVo> postList = postService.getPostList(categoryNo.get());
		if(postNo.isEmpty()) {
			postNo = Optional.of(postList.size() > 0 ? postList.get(0).getNo() : 1L);
		}
		
		model.addAttribute("blogVo", blogService.getById(id));
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		model.addAttribute("postVo", postService.getPostByNo(postNo.get()));
		return "blog/main";
	}
	
	@Auth
	@RequestMapping({"/admin","/admin/basic"})
	public String adminBasic(@PathVariable("id") String id, Model model) {
		model.addAttribute("blogVo", blogService.getById(id));
		return "blog/admin-basic";
	}
	
	@Auth
	@RequestMapping("/update-basic")
	public String updateBasic(@PathVariable("id") String id, BlogVo vo, 
			@RequestParam(value="logo-file") MultipartFile file) {
		String logo = fileUploadService.restore(id, file);
		if (logo != null) {
			vo.setLogo(logo);
		}
		vo.setId(id);
		blogService.update(vo);
		return "redirect:/"+ id;
	}

	@Auth
	@GetMapping({"/admin/category"})
	public String adminCategory(@PathVariable("id") String id, Model model) {
		model.addAttribute("blogVo", blogService.getById(id));
		List<CategoryVo> categoryList = categoryService.getCategoryList(id);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryListSize", categoryList.size());
		return "blog/admin-category";
	}
	
	@Auth
	@RequestMapping(value={"/admin/category"}, method=RequestMethod.POST)
	public String adminCategory(@PathVariable("id") String id, CategoryVo categoryVo) {
		categoryVo.setId(id);
		categoryService.add(categoryVo);
		return "redirect:/"+id+"/admin/category";
	}
	
	@Auth
	@RequestMapping({"/admin/category/delete"})
	public String adminDeleteCategory(@PathVariable("id") String id, Long no) {
		categoryService.delete(no, id);
		return "redirect:/"+id+"/admin/category";
	}
	
	@Auth
	@GetMapping({"/admin/write"})
	public String adminWrite(@PathVariable("id") String id, Model model) {
		model.addAttribute("blogVo", blogService.getById(id));
		model.addAttribute("categoryList", categoryService.getCategoryList(id));
		return "blog/admin-write";
	}
	
	@Auth
	@RequestMapping(value={"/admin/write"}, method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, PostVo postVo) {
		postService.write(postVo);
		return "redirect:/"+id;
	}

	@Auth
	@RequestMapping({"/admin/delete"})
	public String adminDelete(@PathVariable("id") String id, Long no) {
		postService.delete(no, id);
		return "redirect:/"+id;
	}

}
