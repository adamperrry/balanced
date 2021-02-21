package com.aim.balanced.type;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/types")
public class TypeController {

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping(path = "")
    public Iterable<Type> getAllMethods() {
        return typeRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Type getMethod(@PathVariable(value = "id") Integer id) {
        Optional<Type> method = typeRepository.findById(id);
        return method.get();
    }
}
