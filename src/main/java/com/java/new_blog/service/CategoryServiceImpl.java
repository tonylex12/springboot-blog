package com.java.new_blog.service;

import com.java.new_blog.entity.Category;
import com.java.new_blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public Category createCategory(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Optional<Category> getCategoryById(Long id) {
    return categoryRepository.findById(id);
  }

  @Override
  public Category updateCategory(Long id, Category category) {
    Optional<Category> categoryOptional = categoryRepository.findById(id);
    if (categoryOptional.isPresent()) {
      Category existingCategory = categoryOptional.get();
      existingCategory.setName(category.getName());
      return categoryRepository.save(existingCategory);
    }
    return null;
  }

  @Override
  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
