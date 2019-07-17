package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
     @Autowired
     private BlogService blogService;
    @GetMapping("/show-category")
    public ModelAndView showCategory() {
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("catagory", categories);
        return modelAndView;
    }


    @GetMapping("/show-createCategory")
    public String showCreate(Model model){
        model.addAttribute("categorys",new Category());
        return "/category/create";
    }
    @PostMapping("/create-category")
    public ModelAndView createCategory(@ModelAttribute Category category){
        categoryService.save(category);
        ModelAndView modelAndView=new ModelAndView("/category/create");
        modelAndView.addObject("categorys",new Category());
        modelAndView.addObject("message","Them thanh cong");
        return modelAndView;
    }

  @GetMapping("/show-editCategory/{id}")
    public ModelAndView showCategory(@PathVariable Long id){
        Category category=categoryService.findById(id);
        ModelAndView modelAndView=new ModelAndView("/category/edit");
        modelAndView.addObject("categorys",category);
        return modelAndView;
  }
    @PostMapping("/edit-category")
    public ModelAndView editCategory(@ModelAttribute Category category)
    {
            categoryService.save(category);
            ModelAndView modelAndView=new ModelAndView("/category/edit");
            modelAndView.addObject("categorys",category);
            modelAndView.addObject("message","Sua thanh cong");
            return modelAndView;

    }
    @GetMapping("/show-deleteCategory/{id}")
    public String showDelete(@PathVariable Long id,Model model)
    {
        Category category=categoryService.findById(id);
        model.addAttribute("categorys",category);
        return "/category/delete";
    }

   @PostMapping("/delete-category")
    public ModelAndView deleteCategory(@ModelAttribute Category category)
   {
       Long id=category.getId();
       categoryService.remove(id);
       ModelAndView modelAndView=new ModelAndView("/category/delete");
       modelAndView.addObject("category",category);
       modelAndView.addObject("message","Xoa thanh cong");
       return modelAndView;
   }

    @GetMapping("/show-viewCategory/{id}")
    public String viewCategory(@PathVariable Long id,Model model){
        Category category=categoryService.findById(id);
        Iterable<Blog> blogs=blogService.findAllByCategory(category);


        model.addAttribute("blog",blogs);
        model.addAttribute("category",category);
        return "/category/view";
    }

}
