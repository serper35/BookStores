package by.me.bookStore.controller;

import by.me.bookStore.model.Book;
import by.me.bookStore.model.BookStore;
import by.me.bookStore.service.BookStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookStoreController {
    private BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookStore> get(@PathVariable int id) {
        return ResponseEntity.ok(bookStoreService.getBookStore(id));

    }

    @PostMapping
    public ResponseEntity<BookStore> add(@RequestBody BookStore bookStore) {
        return ResponseEntity.ok(bookStoreService.addBookStore(bookStore));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookStore> update(@RequestBody BookStore bookStore,
                                            @PathVariable int id) {
        return ResponseEntity.ok(bookStoreService.updateBookStore(bookStore, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        bookStoreService.deleteBookStore(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<BookStore>> getAll() {
        return ResponseEntity.ok(bookStoreService.getAll());
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getBooksByStoreId(@PathVariable int id) {
        return ResponseEntity.ok(bookStoreService.getBooksByStore(id));
    }
}
