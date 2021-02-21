package com.aim.balanced.method;

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
@RequestMapping(path = "api/methods")
public class MethodController {

    @Autowired
    private MethodRepository methodRepository;

    @GetMapping(path = "")
    public Iterable<Method> getAllMethods() {
        return methodRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Method getMethod(@PathVariable(value = "id") Integer id) {
        Optional<Method> method = methodRepository.findById(id);
        return method.get();
    }

    @PostMapping(path = "/")
    public Method createMethod(@RequestBody Method method) {
        methodRepository.save(method);
        return method;
    }

    @PutMapping(path = "/{id}")
    public Method updateMethod(@PathVariable(value = "id") Integer id, @RequestBody Method methodDetails) {
        Optional<Method> optionalMethod = methodRepository.findById(id);
        Method method = optionalMethod.get();

        BeanUtils.copyProperties(methodDetails, method);
        method.setId(id);

        methodRepository.save(method);
        return method;
    }

    // User will not be allowed to delete a method 
    @DeleteMapping(path = "/{id}")
    public String deleteMethod(@PathVariable(value = "id") Integer id) {
        methodRepository.deleteById(id);
        return "Deleted";
    }
}
