package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.BlogRepository;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import com.sun.org.apache.regexp.internal.RE;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;
import javax.persistence.GeneratedValue;

import java.lang.ref.PhantomReference;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    //Lay ve danh sach ca category
    //Select,option
    @ModelAttribute("categorys")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }



    //search
    @GetMapping("/search-blog")
    public ModelAndView searchBlog(@RequestParam String name){
        Category category=categoryService.findByName(name);
        Iterable<Blog> blogs=blogService.findAllByCategory(category);
        ModelAndView modelAndView=new ModelAndView("/blog/search");
        modelAndView.addObject("category",category);
        modelAndView.addObject("blog",blogs);
        return modelAndView;
    }





    @RequestMapping("/show-blog")
    public ModelAndView showBlog(Pageable pageable){
        Page<Blog> blogs=blogService.findAll( pageable);
       ModelAndView modelAndView=new ModelAndView("/blog/list");
       modelAndView.addObject("blog",blogs);
       return modelAndView;
    }

    //phan trang
    @GetMapping("/page-customer")
    public ModelAndView pageCustomer( Pageable pageable){
        Page<Blog> blogs=blogService.findAll(pageable);
        ModelAndView modelAndView=new ModelAndView("/blog/list");
        modelAndView.addObject("blog",blogs);
        return modelAndView;
    }

    @GetMapping("/show-create")
    public String showCreate(Model model){
        model.addAttribute("blog",new Blog());
        return "/blog/create";
    }


    @PostMapping("/create-blog")
    public ModelAndView createBlog(@ModelAttribute Blog blog)
    {
        blogService.save(blog);
        ModelAndView modelAndView=new ModelAndView("/blog/create");
        modelAndView.addObject("blogs",new Blog());
        modelAndView.addObject("message","Add blog successfully");
        return modelAndView;
    }
    @GetMapping("/show-view/{id}")
    public ModelAndView showView(@PathVariable Long id){
        Blog blog= (Blog) blogService.finById(id);
        ModelAndView modelAndView=new ModelAndView("/blog/view");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
    @GetMapping("/show-delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
        Blog blog= (Blog) blogService.finById(id);
        ModelAndView modelAndView=new ModelAndView("/blog/delete");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
    @PostMapping("/delete-blog")
    public ModelAndView deleteBlog(@ModelAttribute Blog blog){
        Long id=blog.getId();
        if (id!=null){
            blogService.remove(id);
            ModelAndView modelAndView=new ModelAndView("/blog/delete");
            modelAndView.addObject("message","Delete blog successfully");
            return modelAndView;
        }else {
            ModelAndView modelAndView=new ModelAndView("/blog/delete");
            modelAndView.addObject("message1","Delete faild");
            return modelAndView;
        }
    }

    @GetMapping("/show-edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Blog blog= (Blog) blogService.finById(id);
        ModelAndView modelAndView=new ModelAndView("/blog/edit");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
    @PostMapping("/edit-blog")
    public ModelAndView editBlog(@ModelAttribute Blog blog)
    {
        if (blog!=null){
            blogService.save(blog);
            ModelAndView modelAndView=new ModelAndView("/blog/edit");
            modelAndView.addObject("blog",blog);
            modelAndView.addObject("message","Edit successfully");
            return modelAndView;
        }else {
            ModelAndView modelAndView=new ModelAndView("/blog/edit");
            modelAndView.addObject("message1","edit faild");
            return modelAndView;
        }
    }
}
