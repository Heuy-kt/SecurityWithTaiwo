package com.heuy.kt.controllers;

import com.heuy.kt.dto.BookDto;
import com.heuy.kt.dto.BookResponse;
import com.heuy.kt.dto.CustomerResponse;
import com.heuy.kt.services.BookService;
import com.heuy.kt.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ap1/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("books")
    public ResponseEntity<List<BookResponse>> getBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("books/{title}")
    public ResponseEntity<BookResponse> getBook(@PathVariable("title") String title){
        return ResponseEntity.ok(bookService.getBook(title));
    }

    @PostMapping("create")
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookService.createBook(bookDto));
    }

}
