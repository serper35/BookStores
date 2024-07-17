package by.me.bookStore.exception;

public class BookStoreNotFoundException extends RuntimeException {
    public BookStoreNotFoundException(String message) {
        super(message);
    }
}
