package by.me.bookStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bookStores")
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String address;
    @OneToMany(mappedBy = "bookStore", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    List<Book> books;

    public BookStore(int id, String name, String address, List<Book> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.books = books;
    }

    public BookStore() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookStore bookStore = (BookStore) object;
        return id == bookStore.id && Objects.equals(name, bookStore.name) && Objects.equals(address, bookStore.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
