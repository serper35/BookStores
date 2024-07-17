package by.me.bookStore.service.impl;

import by.me.bookStore.exception.BookNotFoundException;
import by.me.bookStore.model.Book;
import by.me.bookStore.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBook() {
        int bookId = 1;
        Book book = new Book();
        book.setId(bookId);
        book.setName("Test Book");

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book result = bookService.getBook(bookId);
        Assertions.assertEquals(book, result);
    }

    @Test
    void testGetBook_NotFound() {
        int bookId = 1;
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.getBook(bookId));
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setName("Test Book");

        Mockito.when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.addBook(book);
        Assertions.assertEquals(book, result);
    }

    @Test
    void testUpdateBook() {
        int bookId = 1;
        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setName("Updated Book");

        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setName("Test Book");

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        Mockito.when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book result = bookService.updateBook(updatedBook, bookId);
        Assertions.assertEquals(updatedBook.getName(), result.getName());
    }

    @Test
    void testUpdateBook_NotFound() {
        int bookId = 1;
        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setName("Updated Book");

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.updateBook(updatedBook, bookId));
    }

    @Test
    void testDeleteBook() {
        int bookId = 1;
        Book book = new Book();
        book.setId(bookId);

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        Mockito.doNothing().when(bookRepository).deleteById(bookId);

        bookService.deleteBook(bookId);
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(bookId);
    }

    @Test
    void testDeleteBook_NotFound() {
        int bookId = 1;
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(bookId));
    }

    @Test
    void testGetAll() {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAll();
        Assertions.assertEquals(books.size(), result.size());
    }
}