package by.me.bookStore.controller;

import by.me.bookStore.model.Book;
import by.me.bookStore.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable int id) {
        return ResponseEntity.ok(bookService.getBook(id));

    }

    @PostMapping
    public ResponseEntity<Book> add(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book,
                                       @PathVariable int id) {
        return ResponseEntity.ok(bookService.updateBook(book, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }
}
