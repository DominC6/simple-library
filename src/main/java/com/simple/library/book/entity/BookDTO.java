package com.simple.library.book.entity;

import java.util.Objects;

/**
 * Data transfer object of Book
 */
public class BookDTO {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    /**
     * Creates a data transfer object for book entity.
     *
     * @param bookEntity the book entity object
     * @return {@link BookDTO} data transfer object for book
     */
    public static BookDTO fromEntity(BookEntity bookEntity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(bookEntity.getAuthor());
        bookDTO.setId(bookEntity.getId());
        bookDTO.setIsbn(bookEntity.getIsbn());
        bookDTO.setTitle(bookEntity.getTitle());
        return bookDTO;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO entity = (BookDTO) o;
        return Objects.equals(this.title, entity.title) &&
                Objects.equals(this.author, entity.author) &&
                Objects.equals(this.isbn, entity.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, isbn);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "author = " + author + ", " +
                "isbn = " + isbn + ")";
    }
}
