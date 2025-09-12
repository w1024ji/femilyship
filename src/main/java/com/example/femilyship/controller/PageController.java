package com.example.femilyship.controller;

import com.example.femilyship.entity.Page;
import com.example.femilyship.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing book pages.
 * This controller handles all the CRUD (Create, Read, Update, Delete) operations.
 */
@RestController
@RequestMapping("/api/pages") // All endpoints in this controller will start with /api/pages
public class PageController {

    private final PageRepository pageRepository;

    @Autowired
    public PageController(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    // GET: Retrieve all pages
    @GetMapping
    public ResponseEntity<List<Page>> getAllPages() {
        List<Page> pages = pageRepository.findAll();
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    // GET: Retrieve a single page by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Page> getPageById(@PathVariable Long id) {
        Optional<Page> pageOptional = pageRepository.findById(id);
        // If the page is found, return it with a 200 OK status.
        // Otherwise, return a 404 Not Found status.
        return pageOptional.map(page -> new ResponseEntity<>(page, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST: Create a new page
    @PostMapping
    public ResponseEntity<Page> createPage(@RequestBody Page newPage) {
        // The @RequestBody annotation tells Spring to convert the incoming JSON into a Page object.
        Page savedPage = pageRepository.save(newPage);
        return new ResponseEntity<>(savedPage, HttpStatus.CREATED); // Return 201 Created status
    }

    // PUT: Update an existing page
    @PutMapping("/{id}")
    public ResponseEntity<Page> updatePage(@PathVariable Long id, @RequestBody Page updatedPage) {
        return pageRepository.findById(id)
                .map(existingPage -> {
                    // Update the fields of the existing page
                    existingPage.setTitle(updatedPage.getTitle());
                    existingPage.setContent(updatedPage.getContent());
                    existingPage.setAuthor(updatedPage.getAuthor());
                    // Note: You might need to add a setter for page_number in your Page entity
                    // if Lombok doesn't generate it for snake_case.
                    // If you change to pageNumber, a setPageNumber method will be generated.

                    Page savedPage = pageRepository.save(existingPage);
                    return new ResponseEntity<>(savedPage, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: Delete a page by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePage(@PathVariable Long id) {
        try {
            if (!pageRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            pageRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 No Content on successful deletion
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
