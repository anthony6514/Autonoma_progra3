package com.example.progra3.controller;

import com.example.progra3.model.Category;
import com.example.progra3.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorias/list";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "categorias/form";
    }

    @PostMapping("/guardar")
    public String save(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Category category = categoryRepository.findById(id).orElseThrow();
        model.addAttribute("category", category);
        return "categorias/form";
    }

    @PostMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categorias";
    }
}
