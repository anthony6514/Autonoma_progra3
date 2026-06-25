package com.example.progra3.controller;

import com.example.progra3.model.Author;
import com.example.progra3.model.Book;
import com.example.progra3.model.Category;
import com.example.progra3.repository.AuthorRepository;
import com.example.progra3.repository.BookRepository;
import com.example.progra3.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/libros")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "libros/list";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "libros/form";
    }

    @PostMapping("/guardar")
    public String save(@ModelAttribute Book book,
                       @RequestParam Optional<Long> authorId,
                       @RequestParam(required = false) List<Long> categoryIds) {

        if (book.getId() != null) {
            // Edición: cargar entidad existente para no perder relaciones no editadas
            Book existing = bookRepository.findById(book.getId()).orElseThrow();
            existing.setTitle(book.getTitle());
            existing.setIsbn(book.getIsbn());

            // Actualizar autor
            if (authorId.isPresent()) {
                authorRepository.findById(authorId.get()).ifPresent(existing::setAuthor);
            } else {
                existing.setAuthor(null);
            }

            // Limpiar categorías anteriores y asignar las nuevas
            existing.getCategories().clear();
            if (categoryIds != null) {
                for (Long categoryId : categoryIds) {
                    categoryRepository.findById(categoryId).ifPresent(existing::addCategory);
                }
            }
            bookRepository.save(existing);
        } else {
            // Creación: libro nuevo
            authorId.ifPresent(id -> authorRepository.findById(id).ifPresent(book::setAuthor));
            if (categoryIds != null) {
                for (Long categoryId : categoryIds) {
                    categoryRepository.findById(categoryId).ifPresent(book::addCategory);
                }
            }
            bookRepository.save(book);
        }

        return "redirect:/libros";
    }

    @GetMapping("/editar/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "libros/form";
    }

    @PostMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/libros";
    }
}
