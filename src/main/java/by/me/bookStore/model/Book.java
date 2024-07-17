package by.me.bookStore.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String name;
    private String genre;
    private int numberOfPages;
    @ManyToOne
    @JoinColumn(name = "bookStore_id")
    private BookStore bookStore;

    public Book(int id, String author, String name, String genre, int numberOfPages, BookStore bookStore) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.numberOfPages = numberOfPages;
        this.bookStore = bookStore;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public BookStore getBookStore() {
        return bookStore;
    }

    public void setBookStore(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return id == book.id && numberOfPages == book.numberOfPages && Objects.equals(author, book.author) && Objects.equals(name, book.name) && Objects.equals(genre, book.genre) && Objects.equals(bookStore, book.bookStore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, name, genre, numberOfPages, bookStore);
    }
}
