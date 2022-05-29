package com.simple.library.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Class containing representation of BOOK table record.
 */
@Entity(name = "BOOK")
public class BookEntity {

    @Id
    @Column(name = "BOOK_ID")
    @SequenceGenerator(name = "SEQ_BOOK_ID", sequenceName = "SEQ_BOOK_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOOK_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "ISBN")
    private String isbn;

    /**
     * Default constructor
     */
    public BookEntity() {
    }

    /**
     * Parametrized constructor, creates {@link BookEntity} from {@link BookDTO}
     *
     * @param bookDTO the book data
     */
    public BookEntity(BookDTO bookDTO) {
        this.author = bookDTO.getAuthor();
        this.isbn = bookDTO.getIsbn();
        this.title = bookDTO.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
