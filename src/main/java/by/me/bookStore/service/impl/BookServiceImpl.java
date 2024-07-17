package by.me.bookStore.service.impl;

import by.me.bookStore.exception.BookNotFoundException;
import by.me.bookStore.model.Book;
import by.me.bookStore.repository.BookRepository;
import by.me.bookStore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final String notFound = "Book not found";
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Book getBook(int id) {
        logger.info("Getting book with ID: {}", id);
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(notFound));
    }

    @Override
    public Book addBook(Book book) {
        logger.info("Adding book with ID: {}", book.getId());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book, int id) {
        logger.info("Updating book with ID: {}", id);
        Book saved = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(notFound));

        saved.setBookStore(book.getBookStore());
        saved.setAuthor(book.getAuthor());
        saved.setGenre(book.getGenre());
        saved.setName(book.getName());
        saved.setNumberOfPages(book.getNumberOfPages());

        return bookRepository.save(saved);
    }

    @Override
    public void deleteBook(int id) {
        logger.info("Deleting book with ID: {}", id);
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(notFound));

        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        logger.info("Getting all books");
        return bookRepository.findAll();
    }
}
