package com.newsum.giflib.data;

import com.newsum.giflib.model.Category;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryRepository
{
    private static final List<Category> ALL_CATEGORIES = Arrays.asList(
            new Category(1,"Technology"),
            new Category(2,"Engineering"),
            new Category(3,"People")
    );

    public Category findById(int id)
    {
        for (Category c : ALL_CATEGORIES)
        {
            if (c.getId() == id)
            {
                return c;
            }
        }
        return null;
    }

    public List<Category> getAllCategories()
    {
        return ALL_CATEGORIES;
    }
}
