package by.me.bookStore.service.impl;

import by.me.bookStore.exception.BookStoreNotFoundException;
import by.me.bookStore.model.Book;
import by.me.bookStore.model.BookStore;
import by.me.bookStore.repository.BookRepository;
import by.me.bookStore.repository.BookStoreRepository;
import by.me.bookStore.service.BookStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final Logger logger = LoggerFactory.getLogger(BookStoreServiceImpl.class);
    private final BookStoreRepository bookStoreRepository;
    private final BookRepository bookRepository;
    private final String notFound = "BookStore not found";

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository, BookRepository bookRepository) {
        this.bookStoreRepository = bookStoreRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookStore getBookStore(int id) {
        logger.info("Getting bookStore with ID: {}", id);
        return bookStoreRepository.findById(id).orElseThrow(() -> new BookStoreNotFoundException(notFound));
    }

    @Override
    public BookStore addBookStore(BookStore bookStore) {
        logger.info("Adding bookStore with ID: {}", bookStore.getId());
        return bookStoreRepository.save(bookStore);
    }

    @Override
    public BookStore updateBookStore(BookStore bookStore, int id) {
        logger.info("Updating bookStore with ID: {}", id);
        BookStore saved = bookStoreRepository.findById(id).orElseThrow(() -> new BookStoreNotFoundException(notFound));
        saved.setAddress(bookStore.getAddress());
        saved.setName(bookStore.getName());

        return bookStoreRepository.save(saved);
    }

    @Override
    public void deleteBookStore(int id) {
        logger.info("Deleting bookStore with ID: {}", id);
        bookStoreRepository.findById(id).orElseThrow(() -> new BookStoreNotFoundException(notFound));
        bookStoreRepository.deleteById(id);
    }

    @Override
    public List<BookStore> getAll() {
        logger.info("Getting all bookStores");
        return bookStoreRepository.findAll();
    }

    @Override
    public List<Book> getBooksByStore(int id) {
        return bookRepository.getBooksByStoreId(id);
    }
}
