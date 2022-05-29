package com.simple.library.book.service;

import com.simple.library.book.entity.BookDTO;
import com.simple.library.book.entity.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible for handling books.
 * Currently, contains methods for:
 * - getting all books from the database,
 * - persisting a new book entry to the database
 */
@Service
public class BookService {

    private static final String BOOK_DTO_MUST_NOT_BE_NULL = "Book object must not be null";
    private static final String NEW_BOOK_ENTRY_PERSISTED = "New book entry has been persisted to the database";

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Gets all books from database.
     *
     * @return {@link BookDTO} all book entries
     */
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Creates new book entry in the database.
     *
     * @param bookDTO Book data to create book from
     * @return {@link BookDTO} created book
     */
    public BookDTO addNewBook(BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new IllegalArgumentException(BOOK_DTO_MUST_NOT_BE_NULL);
        }
        BookEntity newBookEntity = new BookEntity(bookDTO);
        BookEntity persistedEntity = bookRepository.save(newBookEntity);
        LOGGER.info(NEW_BOOK_ENTRY_PERSISTED);
        return BookDTO.fromEntity(persistedEntity);
    }
}
