package by.me.bookStore.service;

import by.me.bookStore.model.Book;
import java.util.List;

public interface BookService {
    Book getBook(int id);

    Book addBook(Book book);

    Book updateBook(Book book, int id);

    void deleteBook(int id);

    List<Book> getAll();
}
