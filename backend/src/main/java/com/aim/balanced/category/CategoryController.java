package com.aim.balanced.category;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "")
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Category getCategory(@PathVariable(value = "id") Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.get();
    }

    @PostMapping(path = "/")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping(path = "/{id}")
    public Category updateCategory(@PathVariable(value = "id") Integer id, @RequestBody Category categoryDetails) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.get();

        BeanUtils.copyProperties(categoryDetails, category);
        category.setId(id);

        categoryRepository.save(category);
        return category;
    }

    // User will not be allowed to delete a category if it's being referenced in transactions
    @DeleteMapping(path = "/{id}")
    public String deleteCategory(@PathVariable(value = "id") Integer id) {
        categoryRepository.deleteById(id);
        return "Deleted";
    }
}
