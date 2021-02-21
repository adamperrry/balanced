package com.aim.balanced.subcategory;

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
@RequestMapping(path = "api/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @GetMapping(path = "")
    public Iterable<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Subcategory getSubcategory(@PathVariable(value = "id") Integer id) {
        Optional<Subcategory> subcategory = subcategoryRepository.findById(id);
        return subcategory.get();
    }

    @PostMapping(path = "/")
    public Subcategory createSubcategory(@RequestBody Subcategory subcategory) {
        subcategoryRepository.save(subcategory);
        return subcategory;
    }

    @PutMapping(path = "/{id}")
    public Subcategory updateSubcategory(@PathVariable(value = "id") Integer id, @RequestBody Subcategory subcategoryDetails) {
        Optional<Subcategory> optionalSubcategory = subcategoryRepository.findById(id);
        Subcategory subcategory = optionalSubcategory.get();

        BeanUtils.copyProperties(subcategoryDetails, subcategory);
        subcategory.setId(id);

        subcategoryRepository.save(subcategory);
        return subcategory;
    }

    // User will not be allowed to delete a subcategory if it's being referenced in transactions
    @DeleteMapping(path = "/{id}")
    public String deleteSubcategory(@PathVariable(value = "id") Integer id) {
        subcategoryRepository.deleteById(id);
        return "Deleted";
    }
}
