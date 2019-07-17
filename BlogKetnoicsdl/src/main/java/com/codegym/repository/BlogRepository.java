package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    //Tra ve danh sach Blog cua 1 CATEGORY
    Iterable<Blog> findAllByCategory(Category category);


}
