package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
   Page<Blog> findAll(Pageable pageable);

   Blog finById(Long id);

   void save (Blog blog);

   void remove(Long id);

   //Tra ve danh sach cac blog cua 1 Category
   Iterable<Blog> findAllByCategory(Category category);
}
