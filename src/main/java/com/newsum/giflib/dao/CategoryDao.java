package com.newsum.giflib.dao;

import com.newsum.giflib.model.Category;

import java.util.List;

public interface CategoryDao
{
    public List<Category> findAll();

    public Category findById(long id);

    public void save(Category category);

    public void delete(Category category);
}
