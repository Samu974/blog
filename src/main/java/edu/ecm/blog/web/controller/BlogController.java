package edu.ecm.blog.web.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ecm.blog.domain.Post;
import edu.ecm.blog.service.PostService;

@Controller
public class BlogController {

	@Inject
	private PostService postService;

	@PostConstruct
	public void bootstrap() {
		if (postService.count()==0){
				Post post1 = new Post();
				post1.setDate(new Date());
				post1.setTitle("Zee End");
				postService.save(post1);
				
				Post post2 = new Post();
				post2.setDate(new Date());
				post2.setTitle("Lost in Translation");
				postService.save(post2);
				
				Post post3 = new Post();
				post3.setDate(new Date());
				post3.setTitle("Start");
				postService.save(post3);

		}
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("posts", postService.find(0, 10));
	    return "index";
	}
	
	@RequestMapping("/billet/{slug}")
	public String post(@PathVariable String slug, Model model) {
	    model.addAttribute("post", postService.findBySlug(slug));

	    return "post";
	}
	

}