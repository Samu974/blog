package edu.ecm.blog.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.ecm.blog.domain.Post;
import edu.ecm.blog.service.PostService;

@Controller
public class AdminController {
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new Date();

	@Inject
	private PostService postService;

	@RequestMapping("/admin/posts")
	public String index(Model model) {
		model.addAttribute("posts", postService.find(0, 10));
		return "admin/posts";
	}

	@RequestMapping(value = "/admin/post", method = RequestMethod.POST)
	public String post(@ModelAttribute("post") Post post,
			BindingResult bindingResult, Model model) {
		// on injecte un Post vierge dans le modèle
		model.addAttribute("post", new Post());

		if (StringUtils.isEmpty(post.getTitle())) {
			bindingResult.rejectValue("title", "field.empty",
					"Le titre est obligatoire");

			return "admin/post";
		}

		postService.save(post);

		return "redirect:/admin/posts";
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	    
	    
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping("/admin/post/{id}")
	public String post(@PathVariable Long id, Model model) {
	    // on injecte le post
	    model.addAttribute("post", postService.findById(id));

	    return "admin/post";
	}
}
