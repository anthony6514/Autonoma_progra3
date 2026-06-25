package com.example.progra3.controller;

import com.example.progra3.model.Author;
import com.example.progra3.model.Profile;
import com.example.progra3.repository.AuthorRepository;
import com.example.progra3.repository.ProfileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/autores")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final ProfileRepository profileRepository;

    public AuthorController(AuthorRepository authorRepository, ProfileRepository profileRepository) {
        this.authorRepository = authorRepository;
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "autores/list";
    }

    @GetMapping("/nuevo")
    public String createForm(Model model) {
        model.addAttribute("author", new Author());
        return "autores/form";
    }

    @PostMapping("/guardar")
    public String save(@ModelAttribute Author author,
                       @RequestParam Optional<String> bio,
                       @RequestParam Optional<String> website) {

        // Si ya existe, cargar el perfil existente para no perder la referencia
        if (author.getId() != null) {
            Author existing = authorRepository.findById(author.getId()).orElseThrow();
            existing.setName(author.getName());
            existing.setEmail(author.getEmail());

            String bioVal = bio.orElse("").trim();
            String webVal = website.orElse("").trim();

            if (!bioVal.isEmpty() || !webVal.isEmpty()) {
                Profile profile = existing.getProfile();
                if (profile == null) {
                    profile = new Profile();
                    existing.setProfile(profile);
                }
                profile.setBiography(bioVal);
                profile.setWebsite(webVal);
            } else {
                // Si ambos campos quedan vacíos al editar, eliminar perfil
                if (existing.getProfile() != null) {
                    Profile old = existing.getProfile();
                    existing.setProfile(null);
                    old.setAuthor(null);
                    profileRepository.delete(old);
                }
            }
            authorRepository.save(existing);
        } else {
            // Nuevo autor
            String bioVal = bio.orElse("").trim();
            String webVal = website.orElse("").trim();
            if (!bioVal.isEmpty() || !webVal.isEmpty()) {
                Profile profile = new Profile();
                profile.setBiography(bioVal);
                profile.setWebsite(webVal);
                author.setProfile(profile);
            }
            authorRepository.save(author);
        }

        return "redirect:/autores";
    }

    @GetMapping("/editar/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Author author = authorRepository.findById(id).orElseThrow();
        model.addAttribute("author", author);
        return "autores/form";
    }

    @PostMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return "redirect:/autores";
    }
}
