package com.simple.library.book.service;

import com.simple.library.book.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for books,
 * provides methods to get, save, update and delete records from the database.
 */
@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {

    List<BookEntity> findAll();
}
