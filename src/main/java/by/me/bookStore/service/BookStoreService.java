package by.me.bookStore.service;

import by.me.bookStore.model.Book;
import by.me.bookStore.model.BookStore;

import java.util.List;

public interface BookStoreService {
    BookStore getBookStore(int id);

    BookStore addBookStore(BookStore bookStore);

    BookStore updateBookStore(BookStore bookStore, int id);

    void deleteBookStore(int id);

    List<BookStore> getAll();
    List<Book> getBooksByStore(int id);
}
