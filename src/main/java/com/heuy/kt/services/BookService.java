package com.heuy.kt.services;

import com.heuy.kt.dto.BookDto;
import com.heuy.kt.dto.BookResponse;
import com.heuy.kt.dto.CustomerResponse;
import com.heuy.kt.exception.AlreadyExistException;
import com.heuy.kt.exception.NotFoundException;
import com.heuy.kt.models.Book;
import com.heuy.kt.models.Customer;
import com.heuy.kt.repo.BookRepo;
import com.heuy.kt.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    public BookResponse getBook(String title) throws NotFoundException{
        Book book = bookRepo.findByTitle(title)
                .orElseThrow(
                        () -> new NotFoundException("Structure not recognised", 404)
                );
        return BookResponse
                .builder()
                .title(book.getTitle())
                .description(book.getDescription())
                .author(book.getAuthor())
                .plan(book.getPlan().toString())
                .build();
    }

    public List<BookResponse> getAllBooks(){
        return bookRepo
                .findAll()
                .stream()
                .map(book -> BookResponse
                        .builder()
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .author(book.getAuthor())
                        .plan(book.getPlan().toString())
                        .build())
                .toList();
    }

    public String createBook(BookDto bookDto){
        if(bookRepo.findByTitle(bookDto.title()).isPresent()){
            throw new AlreadyExistException("Book already present", 409);
        }
        Book book = Book
                .builder()
                .title(bookDto.title())
                .description(bookDto.description())
                .author(bookDto.author())
                .plan(bookDto.plan())
                .build();
        bookRepo.save(book);
        return book.getTitle();
    }

}
