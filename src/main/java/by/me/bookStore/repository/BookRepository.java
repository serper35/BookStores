package by.me.bookStore.repository;

import by.me.bookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT * FROM books WHERE book_store_id = :bookStoreId", nativeQuery = true)
    List<Book> getBooksByStoreId(@Param("bookStoreId") int bookStoreId);
}
