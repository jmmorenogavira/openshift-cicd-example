package com.jmmoreno.openshift.cicd.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jmmoreno.openshift.cicd.model.Book;
import com.jmmoreno.openshift.cicd.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/v1/books")
@Slf4j
@RequiredArgsConstructor
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public ResponseEntity<List<Book>> findAll() {
		return ResponseEntity.ok(bookService.findAll());
	}

	@PostMapping
	public ResponseEntity create(@Valid @RequestBody Book book) {
		return ResponseEntity.ok(bookService.save(book));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		Optional<Book> book = bookService.findById(id);
		if (!book.isPresent()) {
			// log.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(book.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book book) {
		if (!bookService.findById(id).isPresent()) {
			// log.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(bookService.save(book));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		if (!bookService.findById(id).isPresent()) {
			// log.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}

		bookService.deleteById(id);

		return ResponseEntity.ok().build();
	}
	
}