package com.example.progra3.controller;

import com.example.progra3.repository.AuthorRepository;
import com.example.progra3.repository.BookRepository;
import com.example.progra3.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public HomeController(AuthorRepository authorRepository, BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("authorCount", authorRepository.count());
        model.addAttribute("bookCount", bookRepository.count());
        model.addAttribute("categoryCount", categoryRepository.count());
        return "index";
    }
}
