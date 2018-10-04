package me.mahfud.example.controller;

import me.mahfud.example.model.BusType;
import me.mahfud.example.repositories.BusTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusController {

    @Autowired
    private BusTypeRepository repository;

    @GetMapping("type")
    List<BusType> all() {
        return repository.findAll();
    }

    @GetMapping("/type/{id}")
    BusType getOne(@PathVariable(name = "id") String typeId) {

        BusType type = repository.findById(typeId).get();
        if (repository.findById(typeId).isPresent()) {
            return type;
        }
        return null;
    }
}
