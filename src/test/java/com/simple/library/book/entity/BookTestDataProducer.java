package com.simple.library.book.entity;

public class BookTestDataProducer {

    public static BookDTO createDto(Long id) {
        BookDTO bookDTO = createDto();
        bookDTO.setId(id);
        return bookDTO;
    }

    public static BookDTO createDto() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn("ISBN");
        bookDTO.setAuthor("Author");
        bookDTO.setTitle("Title");
        return bookDTO;
    }

    public static BookEntity createEntry(Long id) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(id);
        bookEntity.setAuthor("Author");
        bookEntity.setTitle("Title");
        bookEntity.setIsbn("Isbn");
        return bookEntity;
    }
}
