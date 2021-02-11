package com.newsum.giflib.service;

import com.newsum.giflib.model.Category;

import java.util.List;

public interface CategoryService
{
    public List<Category> findAll();

    public Category findById(Long id);

    public void save(Category category);

    public void delete(Category category);
}
