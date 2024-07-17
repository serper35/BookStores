package by.me.bookStore.service.impl;

import by.me.bookStore.exception.BookStoreNotFoundException;
import by.me.bookStore.model.Book;
import by.me.bookStore.model.BookStore;
import by.me.bookStore.repository.BookRepository;
import by.me.bookStore.repository.BookStoreRepository;
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

class BookStoreServiceImplTest {
    @Mock
    private BookStoreRepository bookStoreRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookStoreServiceImpl bookStoreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookStore() {
        int bookStoreId = 1;
        BookStore bookStore = new BookStore();
        bookStore.setId(bookStoreId);
        bookStore.setName("Test Book Store");

        Mockito.when(bookStoreRepository.findById(bookStoreId)).thenReturn(Optional.of(bookStore));

        BookStore result = bookStoreService.getBookStore(bookStoreId);
        Assertions.assertEquals(bookStore, result);
    }

    @Test
    void testGetBookStore_NotFound() {
        int bookStoreId = 1;
        Mockito.when(bookStoreRepository.findById(bookStoreId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BookStoreNotFoundException.class, () -> bookStoreService.getBookStore(bookStoreId));
    }

    @Test
    void testAddBookStore() {
        BookStore bookStore = new BookStore();
        bookStore.setName("Test Book Store");

        Mockito.when(bookStoreRepository.save(bookStore)).thenReturn(bookStore);

        BookStore result = bookStoreService.addBookStore(bookStore);
        Assertions.assertEquals(bookStore, result);
    }

    @Test
    void testUpdateBookStore() {
        int bookStoreId = 1;
        BookStore updatedBookStore = new BookStore();
        updatedBookStore.setId(bookStoreId);
        updatedBookStore.setName("Updated Book Store");

        BookStore existingBookStore = new BookStore();
        existingBookStore.setId(bookStoreId);
        existingBookStore.setName("Test Book Store");

        Mockito.when(bookStoreRepository.findById(bookStoreId)).thenReturn(Optional.of(existingBookStore));
        Mockito.when(bookStoreRepository.save(existingBookStore)).thenReturn(existingBookStore);

        BookStore result = bookStoreService.updateBookStore(updatedBookStore, bookStoreId);
        Assertions.assertEquals(updatedBookStore.getName(), result.getName());
    }

    @Test
    void testUpdateBookStore_NotFound() {
        int bookStoreId = 1;
        BookStore updatedBookStore = new BookStore();
        updatedBookStore.setId(bookStoreId);
        updatedBookStore.setName("Updated Book Store");

        Mockito.when(bookStoreRepository.findById(bookStoreId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BookStoreNotFoundException.class, () -> bookStoreService.updateBookStore(updatedBookStore, bookStoreId));
    }

    @Test
    void testDeleteBookStore() {
        int bookStoreId = 1;
        BookStore bookStore = new BookStore();
        bookStore.setId(bookStoreId);
        Mockito.when(bookStoreRepository.findById(bookStoreId)).thenReturn(Optional.of(bookStore));
        Mockito.doNothing().when(bookStoreRepository).deleteById(bookStoreId);

        bookStoreService.deleteBookStore(bookStoreId);
        Mockito.verify(bookStoreRepository, Mockito.times(1)).deleteById(bookStoreId);
    }

    @Test
    void testDeleteBookStore_NotFound() {
        int bookStoreId = 1;
        Mockito.when(bookStoreRepository.findById(bookStoreId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BookStoreNotFoundException.class, () -> bookStoreService.deleteBookStore(bookStoreId));
    }

    @Test
    void testGetAll() {
        List<BookStore> bookStores = new ArrayList<>();
        bookStores.add(new BookStore());
        bookStores.add(new BookStore());

        Mockito.when(bookStoreRepository.findAll()).thenReturn(bookStores);

        List<BookStore> result = bookStoreService.getAll();
        Assertions.assertEquals(bookStores.size(), result.size());
    }

    @Test
    void testGetBooksByStore() {
        int bookStoreId = 1;
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());

        Mockito.when(bookRepository.getBooksByStoreId(bookStoreId)).thenReturn(books);

        List<Book> result = bookStoreService.getBooksByStore(bookStoreId);
        Assertions.assertEquals(books.size(), result.size());
    }
}