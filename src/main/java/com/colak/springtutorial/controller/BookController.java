package com.colak.springtutorial.controller;

import com.colak.springtutorial.exception.BookNotFoundException;
import com.colak.springtutorial.exception.DuplicateIsbnException;
import com.colak.springtutorial.metedata.PublicationYear;
import com.colak.springtutorial.model.Book;
import com.colak.springtutorial.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Book createBook(@Valid @RequestBody BookDto book) throws DuplicateIsbnException {
        return bookService.create(BookDto.transform(book));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) throws BookNotFoundException {
        return bookService.getByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("The given isbn is invalid"));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/query")
    public List<Book> getBooksByAuthorAndTitle(@RequestParam(value = "title") String title, @RequestParam(value = "author") String author) {
        return bookService.findByTitleAndAuthor(title, author);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody BookDto book) throws BookNotFoundException {
        return bookService.update(id, BookDto.transform(book));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteById(id);
    }

    @Setter
    @Getter
    public static class BookDto {

        @NotBlank
        private String title;

        @Positive
        @PublicationYear
        private Integer publicationYear;

        @NotBlank
        private String authorName;

        @NotBlank
        private String isbn;

        static Book transform(BookDto bookDto) {
            Book book = new Book();
            book.setTitle(bookDto.title);
            book.setPublicationYear(bookDto.publicationYear);
            book.setAuthorName(bookDto.authorName);
            book.setIsbn(bookDto.isbn);
            return book;
        }

    }
}