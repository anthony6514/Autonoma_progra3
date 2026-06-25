package com.example.progra3.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.progra3.model.Author;
import com.example.progra3.repository.AuthorRepository;

@RestController
@RequestMapping("/api/autores")
public class ApiAuthorController {

    private final AuthorRepository authorRepository;

    public ApiAuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author saved = authorRepository.save(author);
        return ResponseEntity.ok(saved);
    }
}
